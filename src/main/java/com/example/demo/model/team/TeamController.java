package com.example.demo.model.team;

import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {


    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private _UserRepository _userRepository;

    @PostMapping(value = "/create/team/{id}")
    public ResponseEntity<?> registerUser(@PathVariable Integer id, @RequestBody TeamDTO newTeam) {

        _User checkUser = _userRepository.findById(id);
        if(checkUser.getTeamDTO()!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        if(newTeam.getDescription().length()>60) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        teamRepository.save(newTeam);
        checkUser.setTeamDTO(newTeam);
        _userRepository.save(checkUser);

        return new ResponseEntity<>(checkUser, HttpStatus.OK);
    }
}
