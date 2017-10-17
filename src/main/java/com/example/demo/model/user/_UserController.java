package com.example.demo.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;


@RestController
public class _UserController {

    @Autowired
    private _UserRepository userRepository;


  //  @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/user")
    public ResponseEntity<_UserResponse> registerUser(@RequestBody _User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        // Integer age = Calendar.getInstance().get(Calendar.YEAR) - newUser.getYear();
        // newUser.setCategory(calculateCategory(age));
        userRepository.save(newUser);
        _UserResponse response = new _UserResponse(newUser);

        return new ResponseEntity<_UserResponse>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<_UserResponse> getUser(@PathVariable Integer id){
       _User _user = userRepository.findById(id);
       System.out.println(id);
       System.out.println(_user);
       if(_user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

       _UserResponse response = new _UserResponse(_user);

       return new ResponseEntity<>(response, HttpStatus.OK);


    }


    public String calculateCategory(Integer age) {
        if (age < 14) return "Młodzik";
        else if (age < 16) return "Kadet";
        else if (age < 19) return "Junior";
        else return "Senior";
    }

}