package com.example.demo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class _UserResponse {

    private int id;

    private String name;
    private String lastname;
    private int year;
    private String email;
    private String phone;
    private String role;


    public _UserResponse(){

    }
    public _UserResponse(_User user){
        this.id=user.getId();
        this.name=user.getName();
        this.lastname=user.getLastname();
        this.year=user.getYear();
        this.email=user.getEmail();
        this.phone=user.getPhone();
        this.role=user.getRole();
    }


    @Override
    public String toString(){
        return "UserResponse{" +
                "id="+id +
                "name="+name +
                ",lastname="+lastname +
                ",year="+year +
                ",email="+email +
                ",phone="+phone +
                ",role="+role+
                "}";
    }
}
