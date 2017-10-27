package com.example.demo.model.user;

import com.example.demo.model.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
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
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        String code = generateCode();
        newUser.setCode(code);

        String from = "basketblast1234@gmail.com";
        String to = newUser.getEmail();
        String subject = "Aktywacja konta";
        String body = "Link aktywacyjny do serwisu BasketBlast: " + "http://localhost:8080/user/activate/"+code + "\nLogin: " + newUser.getEmail() + "\nHaslo: " + newUser.getPassword();
        mailSenderService.sendEmail(from,to,subject,body);

        userRepository.save(newUser);
        _UserResponse response = new _UserResponse(newUser);
        return new ResponseEntity<_UserResponse>(response, HttpStatus.OK);
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
    public ResponseEntity<?> sendActivationEmail(@RequestBody ActivateEmail activateEmail) {
        if(userRepository.findByEmail(activateEmail.getEmail())==null) { return new ResponseEntity(activateEmail,HttpStatus.BAD_REQUEST); }
        String from = "basketblast1234@gmail.com";
        String to = activateEmail.getEmail();
        String subject = "Aktywacja konta";
        String body = "Link aktywacyjny do serwisu BasketBlast: " + "http://localhost:8080/user/activate/"+userRepository.findByEmail(activateEmail.getEmail()).getCode();
        mailSenderService.sendEmail(from,to,subject,body);

        return new ResponseEntity(activateEmail,HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<_UserResponse> getUser(@PathVariable Integer id){
       _User _user = userRepository.findById(id);

       if(_user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

       _UserResponse response = new _UserResponse(_user);

       return new ResponseEntity<>(response, HttpStatus.OK);


    }

 public String generateCode(){
     char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
     StringBuilder sb = new StringBuilder();
     Random random = new Random();
     for (int i = 0; i < 10; i++) {
         char c = chars[random.nextInt(chars.length)];
         sb.append(c);
     }
     String code = sb.toString();
     return code;

 }

}