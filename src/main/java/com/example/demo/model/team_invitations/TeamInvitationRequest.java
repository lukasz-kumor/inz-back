package com.example.demo.model.team_invitations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamInvitationRequest {

    public String userEmail;
    public String teamName;
    public TeamInvitationRequest(){}
    public TeamInvitationRequest( String teamName,String userEmail){
        this.userEmail=userEmail;
        this.teamName=teamName;
    }
}
