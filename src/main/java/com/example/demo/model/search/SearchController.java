package com.example.demo.model.search;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.hall.HallRepository;
import com.example.demo.model.hall.HallResponse;
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

import java.util.ArrayList;
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

}
