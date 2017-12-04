package com.example.demo.model.team;

import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class TeamDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "team_seq", initialValue = 0, allocationSize = 1)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String city;

    private Date dateOfEdit;

    public TeamDAO(){}


    public TeamDAO(String name, String description, String city){
        this.name=name;
        this.description=description;
        this.city=city;
    }

    @Override
    public String toString(){
        return "Team{" +
                "id="+id+
                ",name="+name +
                ",description="+description+
                ",city="+city+
                "}";
    }
}
