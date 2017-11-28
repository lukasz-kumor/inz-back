package com.example.demo.model.user;


import com.example.demo.model.team.TeamDTO;
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
    private int salary;
    private TeamDTO team;

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
//        this.salary=user.getSalary();
        this.team=user.getTeamDTO();

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
                ",salary="+salary+
                ",team+"+team+
                "}";
    }
}
