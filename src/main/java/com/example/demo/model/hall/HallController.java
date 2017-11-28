package com.example.demo.model.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value ="/halls/inactive")
    public ResponseEntity<?> getInactiveHalls(){
        List<HallDTO> foundHalls = hallRepository.findAllByAndActivated(false);

        if(foundHalls.size()==0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity(foundHalls,HttpStatus.OK);
    }
    @GetMapping(value ="/halls/activate/{id}")
    public ResponseEntity<?> getInactiveHalls(@PathVariable int id){
        if(hallRepository.findById(id) ==null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        HallDTO hall = hallRepository.findById(id);
        hall.setActivated(true);
        hallRepository.save(hall);
        System.out.println(hallRepository.findById(id));
        return new ResponseEntity(id,HttpStatus.OK);
    }

    @GetMapping(value ="/halls/delete/{id}")
    public ResponseEntity<?> deleteHalls(@PathVariable int id){
        if(hallRepository.findById(id) ==null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        HallDTO hall = hallRepository.findById(id);
        hallRepository.delete(hall);
        return new ResponseEntity(id,HttpStatus.OK);
    }
}
