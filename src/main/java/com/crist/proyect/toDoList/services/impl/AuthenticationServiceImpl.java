package com.crist.proyect.toDoList.services.impl;

import com.crist.proyect.toDoList.dto.UsersDto;
import com.crist.proyect.toDoList.models.Role;
import com.crist.proyect.toDoList.models.User;
import com.crist.proyect.toDoList.models.auth.RegisterRequest;
import com.crist.proyect.toDoList.models.auth.RequestAuth;
import com.crist.proyect.toDoList.models.auth.ResponseAuth;
import com.crist.proyect.toDoList.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UsersDto repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  IJwtServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseAuth register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Role.USER)
                .build();
        repository.save(user);
        var jwtToken =jwtService.generatedToken(user);
        return  ResponseAuth.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public ResponseAuth authentication(RequestAuth request) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken =jwtService.generatedToken(user);
        return new ResponseAuth(jwtToken);
    }
}
