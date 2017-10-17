package com.example.demo.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationRequest implements Serializable {


    private static final long serialVersionUID = -7054223136875688742L;
    private String email;
    private String password;

    public JwtAuthenticationRequest(){}

    public JwtAuthenticationRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
