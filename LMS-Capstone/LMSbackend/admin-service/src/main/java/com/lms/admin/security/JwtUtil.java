//package com.lms.admin.security;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import java.security.Key;
//import java.util.Date;
//@Component
//public class JwtUtil {
//    @Value("${jwt.secret}") private String secret;
//    @Value("${jwt.expiration}") private long expirationMs;
//    private Key key(){ return Keys.hmacShaKeyFor(secret.getBytes()); }
//    public String generate(String subject){
//        long now = System.currentTimeMillis();
//        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + expirationMs)).signWith(key(), SignatureAlgorithm.HS256).compact();
//    }
//    public Jws<Claims> validate(String token){ return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token); }
//}//