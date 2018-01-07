package com.example.demo.model.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserRequest {
    private String name;
    private String email;
    private String role;
    private String lastname;

    public SearchUserRequest(){}
    public SearchUserRequest(String name, String email, String role,String lastname){
        this.name = name;
        this.email = email;
        this.role = role;
        this.lastname=lastname;
    }
}
