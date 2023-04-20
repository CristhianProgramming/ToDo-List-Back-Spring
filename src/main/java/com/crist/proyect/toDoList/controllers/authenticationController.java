package com.crist.proyect.toDoList.controllers;

import com.crist.proyect.toDoList.models.auth.RegisterRequest;
import com.crist.proyect.toDoList.models.auth.RequestAuth;
import com.crist.proyect.toDoList.models.auth.ResponseAuth;
import com.crist.proyect.toDoList.services.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class authenticationController {

    @Autowired
    private IAuthenticationService serviceAuth;

    @PostMapping("register")
    public ResponseEntity<ResponseAuth> register(@RequestBody RegisterRequest payload){
        return ResponseEntity.ok(serviceAuth.register(payload));
    }

    @PostMapping("login")
    public ResponseEntity<ResponseAuth> login(@RequestBody RequestAuth payload){
        return ResponseEntity.ok(serviceAuth.authentication(payload));
    }

}
