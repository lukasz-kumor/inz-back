package com.example.demo.model.match;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.hall.HallRepository;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.team.TeamRepository;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private _UserRepository userRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private MatchRepository matchRepository;

    private static final Logger log = LoggerFactory.getLogger(MatchController.class);

    @Scheduled(fixedRate = 36000)
    public void checkFinishedMatches() {
    List<MatchDAO> finishedMatches = matchRepository.findAllByFinished(true);
        Calendar finishDate = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        finishDate.setTime(new Date());
        now.setTime(new Date());

    for(int i=0;i<finishedMatches.size();i++){
        finishDate.setTime(finishedMatches.get(i).getBeginDate());
        finishDate.add(Calendar.HOUR_OF_DAY, 3);
       if(finishDate.before(now)) matchRepository.findById((finishedMatches.get(i).getId()));
        log.info("Finished match : " + matchRepository.findById((finishedMatches.get(i).getId())));
    }

    }

@PostMapping(value = "/match/invite")
    public ResponseEntity<?> inviteToMatch(@RequestBody MatchRequest matchInv){
    Date matchDate = setHourToDate(matchInv.getDate(),matchInv.getHour());
    if(matchRepository.findByBeginDateAndTeamAidAndTeamBid(matchDate,matchInv.getTeamA_id(),matchInv.getTeamB_id())!=null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if(!checkDates(matchInv.getDate()))  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    System.out.println("------REFSALARY" + matchInv.getSalary());
    MatchDAO match = new MatchDAO(hallRepository.findById(matchInv.getHallId()),matchInv.getTeamA_id(),matchInv.getTeamB_id(),matchDate,matchInv.getRefId(),matchInv.getSalary());
    matchRepository.save(match);

    return new ResponseEntity<>(matchInv, HttpStatus.OK);

}

@GetMapping(value = "/match/invites/{id}")
    public ResponseEntity<List<MatchResponse>> getTeamInvites(@PathVariable Integer id){

    List<MatchDAO> invitesList= matchRepository.findAllByTeamBidAndActiveAndTeamBaccepted(id,false,false);
    List<MatchResponse> responseList = new ArrayList<>();
    responseList = generateInvResponse(invitesList);

        return new ResponseEntity<List<MatchResponse>>(responseList, HttpStatus.OK);

}
    @GetMapping(value = "/match/ref/accept/{id}")
    public ResponseEntity<?> refAcceptMatch(@PathVariable Integer id){
        MatchDAO match = matchRepository.findById(id);
        match.setRefAccepted(true);
        if(match.isTeamBaccepted()) match.setActive(true);
        matchRepository.save(match);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/match/team/accept/{id}")
    public ResponseEntity<?> teamAcceptMatch(@PathVariable Integer id){
        MatchDAO match = matchRepository.findById(id);
        match.setTeamBaccepted(true);
        if(match.isRefAccepted()) match.setActive(true);
        matchRepository.save(match);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/match/deny/{id}")
    public ResponseEntity<?> teamDenyMatch(@PathVariable Integer id){
        MatchDAO match = matchRepository.findById(id);
        matchRepository.delete(match);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @GetMapping(value = "/match/invites/ref/{id}")
    public ResponseEntity<List<MatchResponse>> getRefInvites(@PathVariable Integer id){
        List<MatchDAO> invitesList = matchRepository.findAllByRefIdAndRefAccepted(id,false);
        List<MatchResponse> responseList = new ArrayList<>();
        responseList = generateInvResponse(invitesList);

        return new ResponseEntity<List<MatchResponse>>(responseList, HttpStatus.OK);

    }

    public List<MatchResponse> generateInvResponse(List<MatchDAO> invitesList){

        List<MatchResponse> responseList = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        for(int i=0; i<invitesList.size();i++){
            MatchResponse response = new MatchResponse();
            response.setDate(df.format(invitesList.get(i).getBeginDate()));
            response.setTeamA(teamRepository.findById(invitesList.get(i).getTeamAid()));
            response.setTeamB(teamRepository.findById(invitesList.get(i).getTeamBid()));
            response.setHall(invitesList.get(i).getHall());
            response.setRef(userRepository.findById(invitesList.get(i).getRefId()));
            response.setSalary(invitesList.get(i).getSalary());
            response.setId(invitesList.get(i).getId());
            responseList.add(response);

        }
        return responseList;
    }

    public Date setHourToDate(Date date, String hour){
        String newHour = hour.substring(0,2);
        int intHour = Integer.parseInt(newHour);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY,intHour);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date d = cal.getTime();

        return d;
    }
    public boolean checkDates(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.add(Calendar.DATE, 2);
        Calendar matchDate = Calendar.getInstance();
        matchDate.setTime(date);
        if(matchDate.before(now)) return false;
        return true;
    }
}
