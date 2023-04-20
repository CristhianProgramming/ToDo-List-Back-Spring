package com.crist.proyect.toDoList.services;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;



public interface IJwtService {

    String extractUsername(String token);

    <T> T extractClaims(String token, Function<Claims,T> resume);

    String generatedToken(UserDetails userDetails);

    String generateToken(Map<String,Object> extraClaims,UserDetails userDetails);

    boolean isTokenValid(String token,UserDetails userDetails);


}
