package com.example.demo.team_invitations;

import com.example.demo.model.team.TeamDAO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class TeamInvitationDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "teaminv_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private boolean isAccepted=false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "teaminv",
            referencedColumnName = "id")
    private TeamDAO teamDAO;

    private int userId;

    public TeamInvitationDAO(){}
    public TeamInvitationDAO(TeamDAO teamDAO, int userId){
        this.teamDAO=teamDAO;
        this.userId=userId;
    }

}
