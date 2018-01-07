package com.example.demo.model.match;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class MatchRequest{


   private String hour;
   private Date date;
   private int refId;
   private int hallId;
   private int teamA_id;
   private int teamB_id;
   private int salary;

    public MatchRequest(){}
    public MatchRequest(int salary,String hour, Date date, int refId, int hallId, int teamA_id, int teamB_id){
        this.salary=salary;
        this.hour=hour;
        this.date=date;
        this.refId=refId;
        this.hallId=hallId;
        this.teamA_id=teamA_id;
        this.teamB_id=teamB_id;
    }
    public MatchRequest(int teamA_id, int teamB_id, int refId, Date date){
        this.teamB_id=teamB_id;
        this.teamA_id=teamA_id;
        this.refId=refId;
        this.date=date;
    }
}



