package com.example.demo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateEmailRequest {
    private String email;
    public ActivateEmailRequest(){}
    public ActivateEmailRequest(String email){
        this.email = email;
    }
}
