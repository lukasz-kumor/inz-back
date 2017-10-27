package com.example.demo.model.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class _User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
    private int id;
    @NotNull
    private String email;
    @NotNull
    private String lastname;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String phone;
    @NotNull
    private String role;
    @NotNull
    private int year;
    private boolean isActivated=false;
    private String code;
    public _User(){}

    public _User(String email, String lastname, String name, String password, String phone, int year, String role) {
        this.email= email;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.year = year;
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString(){
        return "User{" +
                "id="+id +
                ",name="+name +
                ",lastname="+lastname +
                ",year="+year +
                ",email="+email +
                ",phone="+phone +
                ",password="+password +
                ",role=" +role+
                ",code=" + code+
                "}";
    }
}


