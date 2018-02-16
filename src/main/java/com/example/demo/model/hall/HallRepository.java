package com.example.demo.model.hall;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HallRepository extends CrudRepository<HallDAO, Integer> {

        HallDAO findById(Integer id );
        List<HallDAO> findAll();
        HallDAO findByCityAndAdress(String city, String adress);
        List<HallDAO> findAllByAndActivated(boolean activated);

}
