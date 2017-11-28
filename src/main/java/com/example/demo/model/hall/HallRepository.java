package com.example.demo.model.hall;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HallRepository extends CrudRepository<HallDTO, Integer> {

        HallDTO findById(Integer id );
        HallDTO findByAdress(String adress);
        List<HallDTO> findAll();
        HallDTO findByCityAndAdress(String city, String adress);
        List<HallDTO> findAllByAndActivated(boolean activated);

}
