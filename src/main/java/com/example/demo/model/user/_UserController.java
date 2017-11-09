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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/edit/user")
    public ResponseEntity<_UserResponse> editUser(@RequestBody _User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        _User _user = userRepository.findByEmail(newUser.getEmail());

        if(userRepository.findByEmail(newUser.getEmail()).getDateOfEdit()!=null){
            Date date = _user.getDateOfEdit();
            Calendar dateOfChanges = Calendar.getInstance();
            dateOfChanges.setTime(date);

            Date date2 = new Date();

            if(dateOfChanges.getTime().compareTo(date2)<7){
                System.out.println("nie mozesz zmienic !");
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
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

        return new ResponseEntity<_UserResponse>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/retrieve/{id}")
    public ResponseEntity<?> getPassword(@PathVariable Integer id) {

        if(userRepository.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        _User _user = userRepository.findById(id);

        if(userRepository.findById(id).getDateOfPasswordRetrieve()!=null) {
            Date date = _user.getDateOfPasswordRetrieve();
            Date dateNow = new Date();
            long diff = dateNow.getTime() - date.getTime();
            long diffHours = diff / (60 * 60 * 1000);

            if (diffHours < 1) {
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
        }
        if(userRepository.findById(id).getDateOfPasswordRetrieve()==null){
            Date date3 = new Date();
            System.out.println(date3);
            _user.setDateOfPasswordRetrieve(date3);
            userRepository.save(_user);
        }

        String from = "basketblast1234@gmail.com";
        String to = _user.getEmail();
        String subject = "Przypomnienie hasła";
        String body = "Twoje hasło do serwisu BasketBlast to: " + "\n" + _user.getPassword();
        mailSenderService.sendEmail(from,to,subject,body);

        return new ResponseEntity<>(id,HttpStatus.OK);

    }

    @GetMapping(value = "/user/salary/{id}/{salary}")
    public ResponseEntity<?> editSalary(@PathVariable Integer id, @PathVariable Integer salary) {

        if(userRepository.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        _User _user = userRepository.findById(id);

        if(userRepository.findById(id).getDateOfPasswordRetrieve()!=null) {
            Date date = _user.getDateOfSalaryEdit();
            Date dateNow = new Date();
            long diff = dateNow.getTime() - date.getTime();
            long diffHours = diff / (60 * 60 * 1000);

            if (diffHours < 48) {
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
        }
        if(userRepository.findById(id).getDateOfSalaryEdit()==null){
            Date date3 = new Date();
            System.out.println(date3);
            _user.setDateOfPasswordRetrieve(date3);
            userRepository.save(_user);
        }
        _user.setSalary(salary);
        userRepository.save(_user);

        return new ResponseEntity<>(id,HttpStatus.OK);

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