package com.example.demo.model.match;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MatchDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "hallDAO",
            referencedColumnName = "id")
    private HallDAO hall;
    @NotNull
    private int teamAid;
    @NotNull
    private int teamBid;
    @NotNull
    private Date beginDate;
    @NotNull
    private int refId;
    @NotNull
    private int salary;

    private boolean teamBaccepted=false;
    private boolean refAccepted=false;
    private boolean invitation=true;
    private boolean finished=false;
    private boolean active=false;

    public MatchDAO(){}

    public MatchDAO(HallDAO hall, int teamAid,int teamBid, Date beginDate, int refId,int salary){
        this.hall = hall;
        this.teamAid=teamAid;
        this.teamBid=teamBid;
        this.beginDate=beginDate;
        this.refId=refId;
        this.salary=salary;
    }



}
