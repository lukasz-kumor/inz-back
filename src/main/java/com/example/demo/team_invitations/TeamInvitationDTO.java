package com.example.demo.team_invitations;

import com.example.demo.model.team.TeamDTO;
import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class TeamInvitationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "teaminv_seq", initialValue = 1, allocationSize = 1)
    private int id;

    @NotNull
    private boolean isAccepted=false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "teaminv",
            referencedColumnName = "id")
    private TeamDTO teamDTO;

    private int userId;

    public TeamInvitationDTO(){}
    public TeamInvitationDTO(TeamDTO teamDTO,int userId){
        this.teamDTO=teamDTO;
        this.userId=userId;
    }

}
