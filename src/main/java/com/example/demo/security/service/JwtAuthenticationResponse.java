package com.example.demo.security.service;

import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class JwtAuthenticationResponse implements Serializable {

    private String token;
    private String name;
    private String lastname;
    private String email;
    private String usertype;
    private int id;

    private _User _user;

    public JwtAuthenticationResponse(String token,String email, String usertype, String name, String lastname, int id){
        this.token = token;
        this.usertype = usertype;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

}
