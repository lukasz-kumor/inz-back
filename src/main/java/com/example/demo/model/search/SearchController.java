package com.example.demo.model.search;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.hall.HallRepository;
import com.example.demo.model.hall.HallResponse;
import com.example.demo.model.match.DateRequest;
import com.example.demo.model.match.MatchDAO;
import com.example.demo.model.match.MatchRepository;
import com.example.demo.model.match.MatchResponse;
import com.example.demo.model.services.SearchQueryCreator;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.team.TeamRepository;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;

import com.example.demo.model.user._UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private _UserRepository _userRepository;
    @Autowired
    private SearchQueryCreator SQC;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;
///user search
    @PostMapping(value = "/search/users")
    public ResponseEntity<List<_UserResponse>> searchForUsers(@RequestBody SearchUserRequest request){

        List<_User> foundUsers = SQC.buildAndExecuteUserQuery(request);
        if(foundUsers.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<_UserResponse> response = generateUserResponse(foundUsers);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Integer total = foundUsers.size();
        headers.add("total", total.toString());
        System.out.println(headers);
         return new ResponseEntity<List<_UserResponse>>(response, headers, HttpStatus.OK);

    }

private List<_UserResponse> generateUserResponse(List<_User> _users){
        List<_UserResponse> response = new ArrayList<>();

        for(_User _user : _users){
            response.add(new _UserResponse(_user));
        }
        return response;
}

///hall search

    @PostMapping(value = "/search/halls")
    public ResponseEntity<List<HallResponse>> searchForUsers(@RequestBody HallDAO request){

        List<HallDAO> foundHalls = SQC.buildAndExecuteHallQuery(request);
        if(foundHalls.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<HallResponse> response = generateHallResponse(foundHalls);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Integer total = foundHalls.size();
        headers.add("total", total.toString());

        return new ResponseEntity<List<HallResponse>>(response, headers, HttpStatus.OK);

    }

    private List<HallResponse> generateHallResponse(List<HallDAO> halls){
        List<HallResponse> response = new ArrayList<>();

        for(HallDAO hall : halls){
            response.add(new HallResponse(hall));
        }
        return response;
    }

///team search
@PostMapping(value = "/search/teams")
public ResponseEntity<List<TeamDAO>> searchForTeams(@RequestBody SearchTeamRequest teamRequest){

        List<TeamDAO> foundTeams = SQC.buildAndExecuteTeamQuery(teamRequest);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Integer total = foundTeams.size();
        headers.add("total", total.toString());

    return new ResponseEntity<>(foundTeams, headers, HttpStatus.OK);

}

    @GetMapping(value = "/search/teams/{id}")
    public ResponseEntity<?> getDetailedTeam(@PathVariable Integer id) {
        List<_User> foundUsers = _userRepository.findByAndTeamDAO_IdOrderByRole(id);

        return new ResponseEntity<>(foundUsers,HttpStatus.OK) ;

    }
///match search
@GetMapping(value = "/matches/search")
public ResponseEntity<List<MatchResponse>> searchForMatches(){

    List<MatchDAO> foundMatches = matchRepository.findAllByActive(true);
    List<MatchResponse> response = generateMatchResponse(foundMatches);

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    Integer total = response.size();
    headers.add("total", total.toString());


    return new ResponseEntity<List<MatchResponse>>(response, headers, HttpStatus.OK);

}
@PostMapping(value = "/matches/search/bydate")
public ResponseEntity<List<MatchResponse>> searchForMatchesByDate(@RequestBody DateRequest dates){

    List<MatchDAO> foundMatches = matchRepository.findAllByActive(true);
    List<MatchDAO> filteredMatches = filterMatchesByDates(foundMatches,dates);
    List<MatchResponse> response = generateMatchResponse(filteredMatches);

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    Integer total = response.size();
    headers.add("total", total.toString());

    return new ResponseEntity<List<MatchResponse>>(response, headers, HttpStatus.OK);
}

public List<MatchDAO> filterMatchesByDates(List<MatchDAO> foundMatches, DateRequest dates){
    if(dates.getDateFrom()==null || dates.getDateFrom()=="" ) dates.setDateFrom("2000-01-01");
    if(dates.getDateTo()==null || dates.getDateTo()=="") dates.setDateTo("2030-01-01");
    List<MatchDAO> filteredMatches = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date  startDate = new Date();
    Date endDate = new Date();
    try {
        startDate = df.parse(dates.getDateFrom());
        endDate = df.parse(dates.getDateTo());
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, 1);
        endDate = c.getTime();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    if(dates.getCity()==null || dates.getCity()==""){
        for(int i=0;i<foundMatches.size();i++){
             if(foundMatches.get(i).getBeginDate().after(startDate) && foundMatches.get(i).getBeginDate().before(endDate)) filteredMatches.add(foundMatches.get(i));
    }}
    else
    {
        for(int i=0;i<foundMatches.size();i++){
            if(foundMatches.get(i).getBeginDate().after(startDate) && foundMatches.get(i).getBeginDate().before(endDate) && dates.getCity().equalsIgnoreCase(foundMatches.get(i).getHall().getCity())) filteredMatches.add(foundMatches.get(i));

        }
    }

    return filteredMatches;


}

@GetMapping(value = "/matches/search/{id}")
public ResponseEntity<List<MatchResponse>> searchForTeamMatches(@PathVariable Integer id){
    List<MatchDAO> foundMatches = matchRepository.findAllByTeamAidOrTeamBidAndFinishedAndActive(id,id,false,true);
    List<MatchResponse> response = generateMatchResponse(foundMatches);
    if(response.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    return new ResponseEntity<List<MatchResponse>>(response, HttpStatus.OK);

}

    @GetMapping(value = "/matches/ref/search/{id}")
    public ResponseEntity<List<MatchResponse>> searchForRefMatches(@PathVariable Integer id){
        List<MatchDAO> foundMatches = matchRepository.findAllByRefIdAndActive(id,true);
        List<MatchResponse> response = generateMatchResponse(foundMatches);

        return new ResponseEntity<List<MatchResponse>>(response, HttpStatus.OK);

    }

    public List<MatchResponse> generateMatchResponse(List<MatchDAO> matchList){

        List<MatchResponse> responseList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        for(int i=0; i<matchList.size();i++){
            MatchResponse response = new MatchResponse();
            response.setDate(df.format(matchList.get(i).getBeginDate()));
            response.setTeamA(teamRepository.findById(matchList.get(i).getTeamAid()));
            response.setTeamB(teamRepository.findById(matchList.get(i).getTeamBid()));
            response.setId(matchList.get(i).getId());
            response.setHall(matchList.get(i).getHall());
            response.setRef(_userRepository.findById(matchList.get(i).getRefId()));
            response.setSalary(matchList.get(i).getSalary());
            responseList.add(response);
        }
        return responseList;
    }


}
