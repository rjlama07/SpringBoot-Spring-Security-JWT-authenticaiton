package com.rjlama.springsecurity.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretKey="";

    public JwtService() {
        try {
            KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
            SecretKey key=keyGen.generateKey();
         secretKey=   Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
         
            e.printStackTrace();
        }
        
    }
    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder().claims().add(claims).subject(userName).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+1000*60*60*10)).and().signWith(getKey()).compact();

    }    

    private SecretKey getKey(){
        byte[] keyBytes=Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    } 
    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

   
    private<T> T extractClaims(String token,Function<Claims,T> claimsResolver){
         Claims clains=extractAllClaims(token);
            return claimsResolver.apply(clains);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getKey()).build()
        .parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token,UserDetails userDetails){
       final String userName=extractUsername(token);
         return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

}
