package com.example.demo.model.match;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class MatchResponse {

private HallDAO hall;
private TeamDAO teamA;
private TeamDAO teamB;
private _User ref;
private String date;
private int salary;
private int id;
public MatchResponse(){ }
public MatchResponse(int id,HallDAO hall, TeamDAO teamA, TeamDAO teamB, _User ref, String date,int salary){
    this.hall=hall;
    this.teamA=teamA;
    this.teamB=teamB;
    this.ref=ref;
    this.date=date;
    this.salary=salary;
    this.id=id;
}
    @Override
    public String toString(){
        return "MatchResponse{" +
                "hall=" + hall+
                "id=" + id +
                ",teamA="+teamA +
                ",teamB="+teamB +
                ",ref="+ref +
                ",date="+date +
                ",salary"+salary+
                "}";
    }

}
