package com.example.demo.team_invitations;

import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.team.TeamRepository;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class TeamInvitationController {

    @Autowired
    public _UserRepository userRepository;
    @Autowired
    public TeamRepository teamRepository;
    @Autowired
    public TeamInvitationRepository teamInvitationRepository;
@PostMapping(value = "/invite/team")
public ResponseEntity<?>  inviteUser(@RequestBody TeamInvitationRequest request) {

    _User user = userRepository.findByEmail(request.getUserEmail());
    TeamDAO team = teamRepository.findByName(request.getTeamName());
    if(user.getTeamDAO()!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
   if(teamInvitationRepository.findByTeamDAO_IdAndUserId(team.getId(),user.getId())!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    TeamInvitationDAO teamInvitation = new TeamInvitationDAO(team,user.getId());
    teamInvitationRepository.save(teamInvitation);

    return new ResponseEntity<>(request, HttpStatus.OK);
}

@GetMapping(value = "/invitation/player/{id}")
public ResponseEntity<?> getInvitations(@PathVariable Integer id){
    List<TeamInvitationDAO> foundTeams = teamInvitationRepository.findAllByUserId(id);


    return new ResponseEntity<>(foundTeams, HttpStatus.OK);
}

@Transactional
@GetMapping(value = "/invitation/accept/{idPlayer}/{idTeam}")
public ResponseEntity<?> acceptInvitation(@PathVariable Integer idPlayer, @PathVariable Integer idTeam) {

        userRepository.findById(idPlayer).setTeamDAO(teamRepository.findById(idTeam));
        teamInvitationRepository.deleteAllByUserId(idPlayer);

        return new ResponseEntity<>(idPlayer,HttpStatus.OK);

}

    @GetMapping(value = "/invitation/deny/{idPlayer}/{idTeam}")
    public ResponseEntity<?> denyInvitation(@PathVariable Integer idPlayer, @PathVariable Integer idTeam) {
        TeamInvitationDAO invitation = teamInvitationRepository.findByTeamDAO_IdAndUserId(idTeam,idPlayer);
        teamInvitationRepository.delete(invitation.getId());

        return new ResponseEntity<>(idPlayer,HttpStatus.OK);

    }

}
