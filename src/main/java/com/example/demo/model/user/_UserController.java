package com.example.demo.model.user;

import com.example.demo.model.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


@RestController
public class _UserController {



    @Autowired
    public MailSenderService mailSenderService;
    @Autowired
    private _UserRepository userRepository;



    @PostMapping(value = "/user")
    public ResponseEntity<_UserResponse> registerUser(@RequestBody _User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String code = generateCode();
        newUser.setCode(code);

        String from = "basketblast1234@gmail.com";
        String to = newUser.getEmail();
        String subject = "Aktywacja konta";
        String body = "Link aktywacyjny do serwisu BasketBlast: "+"http://localhost:8080/user/activate/"+
        code+"\nLogin: " + newUser.getEmail() + "\nHaslo: " + newUser.getPassword();
        mailSenderService.sendEmail(from,to,subject,body);

        userRepository.save(newUser);
        _UserResponse response = new _UserResponse(newUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/edit/user")
    public ResponseEntity<_UserResponse> editUser(@RequestBody _User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        _User _user = userRepository.findByEmail(newUser.getEmail());

        if(userRepository.findByEmail(newUser.getEmail()).getDateOfEdit()!=null){
            Date date = _user.getDateOfEdit();
            Calendar dateOfChanges = Calendar.getInstance();
            dateOfChanges.setTime(date);
            Date date2 = new Date();

            if(dateOfChanges.getTime().compareTo(date2) < 7){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        if(userRepository.findByEmail(newUser.getEmail()).getDateOfEdit()==null){
            Date date = new Date();
            _user.setDateOfEdit(date);
        }
        _user.setName(newUser.getName());
        _user.setLastname(newUser.getLastname());
        _user.setYear(newUser.getYear());
        _user.setPhone(newUser.getPhone());
        userRepository.save(_user);
        _UserResponse response = new _UserResponse(_user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(value = "/user/activate/{code}")
    public StringBuilder activateUser(@PathVariable String code) {
        StringBuilder result = new StringBuilder();

        if(userRepository.findByCode(code).getEmail()==null){
            result.append("Aktywacja nieudana!");
        }
        else {
            _User newUser = userRepository.findByCode(code);
            newUser.setActivated(true);
            userRepository.save(newUser);
            result.append("Pomyslnie aktywowales konto: ");
            result.append(newUser.getEmail());
        }
        return result;
    }


    @PostMapping(value = "/user/activate")
    public ResponseEntity<?> sendActivationEmail(@RequestBody ActivateEmailRequest activateEmailRequest) {

        if(userRepository.findByEmail(activateEmailRequest.getEmail())==null) { return new ResponseEntity(activateEmailRequest,HttpStatus.BAD_REQUEST); }
        String from = "basketblast1234@gmail.com";
        String to = activateEmailRequest.getEmail();
        String subject = "Aktywacja konta";
        String body = "Link aktywacyjny do serwisu BasketBlast: " + "http://localhost:8080/user/activate/"+userRepository.findByEmail(activateEmailRequest.getEmail()).getCode();
        mailSenderService.sendEmail(from,to,subject,body);

        return new ResponseEntity(activateEmailRequest,HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<_UserResponse> getUser(@PathVariable Integer id){
        _User _user = userRepository.findById(id);
       if(_user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       _UserResponse response = new _UserResponse(_user);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String generateCode(){
     String code;
     char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
     StringBuilder sb = new StringBuilder();
     Random random = new Random();
     for (int i = 0; i < 10; i++) {
         char c = chars[random.nextInt(chars.length)];
         sb.append(c);
     }
     code = sb.toString();
     return code;
    }


}