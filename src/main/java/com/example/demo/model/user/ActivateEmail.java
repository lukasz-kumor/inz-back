package com.example.demo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateEmail {
    private String email;
    public ActivateEmail(){}
    public ActivateEmail(String email){
        this.email = email;
    }
}
