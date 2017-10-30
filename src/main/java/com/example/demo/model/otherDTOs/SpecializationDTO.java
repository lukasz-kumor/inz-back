package com.example.demo.model.otherDTOs;
import com.example.demo.model.user._User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class SpecializationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "specialization_seq", initialValue = 1, allocationSize = 1)
    private int id;


    @ManyToOne
    @JoinColumn(name = "userId",
            referencedColumnName = "ID")
    private _User user;


    @NotNull
    private int team_id=0;

    public SpecializationDTO(){}


    public SpecializationDTO(_User user){

        this.user = user;
    }
    public SpecializationDTO(int team_id, _User user){

        this.team_id = team_id;
        this.user = user;
    }


    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public _User getUser() {
        return user;
    }

    public void setUser(_User user) {
        this.user = user;
    }


}
