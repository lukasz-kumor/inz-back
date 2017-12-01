package com.example.demo.team_invitations;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamInvitationRepository extends CrudRepository<TeamInvitationDAO, Integer> {

    TeamInvitationDAO findByTeamDAO_IdAndUserId(int teamId, int userId);
    List<TeamInvitationDAO> findAllByUserId(int id);
    void deleteAllByUserId(int userid);
}
