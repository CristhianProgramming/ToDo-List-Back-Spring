package com.crist.proyect.toDoList.services.impl;

import com.crist.proyect.toDoList.services.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class IJwtServiceImpl implements IJwtService {


    private static final  String SECRET_KEY = "3R6BBTezk8tFhBBNOv4p+489dxFdz2UWOy5Ff/8snkHRuVWfMTgQryHNFN3SGy6Fz1WwIksnQiloAOYPYEwfh5eCBTFErOok9WuDblENTje5TsrNUEmrXXbee/Eg3fEcWAaNlxDcRUdIH/9cmEHUSaEd08hV+8Jq4gWYUEcnw+D2chv2Jp/H9XaAAKA97PlzJ37Jn4nm1/JsVmIU5sTGFQitkEq9qjMAdrN4vf/aQlLkfHG9WoEOaGh5lVJLvXlmbqs3mkE+biocOWpuCJjQhMBSNdNbnhY1tNY9RTw5dMH5lpGd+I";

    @Override
    public String extractUsername(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    @Override
    public <T> T extractClaims(String token, Function<Claims,T> resume){
        final Claims claims = extractAllClaims(token);
        return resume.apply(claims);
    }
    @Override
    public String generatedToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    @Override
    public String generateToken(   Map<String, Object> extraClaims ,
                                   UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    @Override
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
