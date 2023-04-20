package com.crist.proyect.toDoList.services;

import com.crist.proyect.toDoList.models.auth.RequestAuth;
import com.crist.proyect.toDoList.models.auth.RegisterRequest;
import com.crist.proyect.toDoList.models.auth.ResponseAuth;

public interface IAuthenticationService {

    ResponseAuth register(RegisterRequest request);

    ResponseAuth authentication(RequestAuth request);
}
