package com.example.demo.model.user;

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

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return "PlayerResponse{" +
                "id="+id +
                ",name="+name +
                ",lastname="+lastname +
                ",year="+year +
                ",email="+email +
                ",phone="+phone +
                "}";
    }
}
