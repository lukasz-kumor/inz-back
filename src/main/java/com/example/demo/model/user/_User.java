package com.example.demo.model.user;


import com.example.demo.model.team.TeamDAO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Getter
@Setter
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
    @NotNull
    private boolean isActivated=false;
    @NotNull
    private boolean isBanned=false;

    @ManyToOne
    @JoinColumn(name = "teamDAO",
            referencedColumnName = "id")
    private TeamDAO teamDAO;

    private Date dateOfEdit=null;
    private Date dateOfPasswordRetrieve=null;
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

    public _User(String email, String lastname, String name,  String phone, int year) {
        this.email= email;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.year = year;
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
                "}";
    }
}


