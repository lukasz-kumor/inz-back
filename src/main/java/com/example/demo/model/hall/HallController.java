package com.example.demo.model.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class HallController {

    @Autowired
    private HallRepository hallRepository;

    @PostMapping(value = "/add/hall")
    public ResponseEntity<?> addHall(@RequestBody HallDTO newHall){
    if(hallRepository.findByCityAndAdress(newHall.getCity(), newHall.getAdress()) !=null) {
        System.out.println("cosnietak");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    hallRepository.save(newHall);
    return new ResponseEntity(newHall,HttpStatus.OK);
    }

}
