package com.crist.proyect.toDoList.models.auth;

import lombok.Builder;

@Builder
public class ResponseAuth {

    private String token;

    public ResponseAuth() {

    }
    public ResponseAuth(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
