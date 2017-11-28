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
    public ResponseEntity<?> createTeam(@PathVariable Integer id, @RequestBody TeamDTO newTeam) {


        if(newTeam.getCity().length()<3 || newTeam.getName().length()<3) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        _User checkUser = _userRepository.findById(id);
        if(checkUser.getTeamDTO()!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        if(newTeam.getDescription().length()>60 || newTeam.getCity().length()>30 || newTeam.getName().length()>30) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

        teamRepository.save(newTeam);
        checkUser.setTeamDTO(newTeam);
        _userRepository.save(checkUser);
        _UserResponse response = new _UserResponse(checkUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value ="/team/remove/player/{id}")
    public ResponseEntity<?> removePlayerFromTeam(@PathVariable Integer id){
        _userRepository.findById(id).setTeamDTO(null);

    return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "team/remove/{id}")
    public ResponseEntity<?> removeTeam(@PathVariable Integer id){
      System.out.println(teamRepository.findById(id));
        teamRepository.delete(id);

        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/edit/team/{id}")
    public ResponseEntity<?> editTeam(@PathVariable Integer id, @RequestBody TeamDTO editedTeam){
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
            if (editedTeam.getName().length() > 3 && editedTeam.getCity().length() > 3 && editedTeam.getName().length()<30 && editedTeam.getCity().length()<30 && editedTeam.getDescription().length()<60) {
                teamRepository.findById(id).setCity(editedTeam.getCity());
                teamRepository.findById(id).setName(editedTeam.getName());
                teamRepository.findById(id).setDateOfEdit(new Date());
                if (!editedTeam.getDescription().isEmpty())
                    teamRepository.findById(id).setDescription(editedTeam.getDescription());
            } else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
}
