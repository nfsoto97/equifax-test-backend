package com.equifax.demo.place.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    // Constructores, getters y setters

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}