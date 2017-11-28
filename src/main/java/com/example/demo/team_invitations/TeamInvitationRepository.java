package com.example.demo.team_invitations;

import com.example.demo.model.user._User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamInvitationRepository extends CrudRepository<TeamInvitationDTO, Integer> {

    TeamInvitationDTO findByTeamDTO_IdAndUserId(int teamId,int userId);
    List<TeamInvitationDTO> findAllByUserId(int id);
    void deleteAllByUserId(int userid);
}
