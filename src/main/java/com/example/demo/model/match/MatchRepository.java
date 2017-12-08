package com.example.demo.model.match;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends CrudRepository<MatchDAO, Integer> {

MatchDAO findByBeginDateAndTeamAidAndTeamBid(Date date, int teamAid, int teamBid);
List<MatchDAO> findAllByTeamBidAndActiveAndTeamBaccepted(int id,boolean inv,boolean teamBaccepted);
List<MatchDAO> findAllByRefIdAndRefAccepted(int id,boolean refAccepted);
List<MatchDAO> findAllByFinished(boolean finished);
MatchDAO findById(int id);
List<MatchDAO> findAllByActive(boolean active);
List<MatchDAO> findAllByTeamAidOrTeamBidAndFinishedAndActive(int teamAid, int teamBid,boolean finished,boolean active);
List<MatchDAO> findAllByRefIdAndActive(int refId, boolean active);
}
