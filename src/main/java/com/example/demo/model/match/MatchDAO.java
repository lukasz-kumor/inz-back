package com.example.demo.model.match;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class MatchDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "match_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @OneToOne
    @JoinColumn(name = "hallDAO",
            referencedColumnName = "id")
    private HallDAO hall;

    @OneToMany
    @JoinColumn(name = "teamDAO",
            referencedColumnName = "id")
    private List<TeamDAO> teamList;

    private Date beginDate;
//
//    private _User refree;
//
//    private boolean isActive=false;




}
