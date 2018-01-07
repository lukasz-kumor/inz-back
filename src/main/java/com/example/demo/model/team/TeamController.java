package com.example.demo.model.team;

import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import com.example.demo.model.user._UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TeamController {


    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private _UserRepository _userRepository;

    @PostMapping(value = "/create/team/{id}")
    public ResponseEntity<?> createTeam(@PathVariable Integer id, @RequestBody TeamDAO newTeam) {

        if(newTeam.getCity().length()<3 || newTeam.getName().length()<3) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        _User checkUser = _userRepository.findById(id);
        if(checkUser.getTeamDAO()!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        if(newTeam.getDescription().length()>60 || newTeam.getCity().length()>30 || newTeam.getName().length()>30) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

        teamRepository.save(newTeam);
        checkUser.setTeamDAO(newTeam);
        _userRepository.save(checkUser);
        _UserResponse response = new _UserResponse(checkUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value ="/team/remove/player/{id}")
    public ResponseEntity<?> removePlayerFromTeam(@PathVariable Integer id){

        _userRepository.findById(id).setTeamDAO(null);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @PostMapping(value = "/edit/team/{id}")
    public ResponseEntity<?> editTeam(@PathVariable Integer id, @RequestBody TeamDAO editedTeam){

        TeamDAO team = teamRepository.findById(id);
        if(teamRepository.findById(id).getDateOfEdit()!=null) {
            Date date = teamRepository.findById(id).getDateOfEdit();
            Calendar dateOfChanges = Calendar.getInstance();
            dateOfChanges.setTime(date);
            Date dateToday = new Date();

            if (dateOfChanges.getTime().compareTo(dateToday) < 7) {
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
        }
       else {
            if (editedTeam.getName().length() > 3 && editedTeam.getCity().length() > 3 && editedTeam.getName().length()<30 && editedTeam.getCity().length()<30) {
                    team.setCity(editedTeam.getCity());
                    team.setName(editedTeam.getName());
                    team.setDateOfEdit(new Date());
                if(editedTeam.getDescription()!=null){
                if (editedTeam.getDescription().length()<60){
                    team.setDescription(editedTeam.getDescription());
                }
                else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
                }
            } else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        teamRepository.save(team);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
