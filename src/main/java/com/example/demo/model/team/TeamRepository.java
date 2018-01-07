package com.example.demo.model.team;

import com.example.demo.model.user._User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamDAO, Integer> {

    TeamDAO findById(int id);
    List<TeamDAO> findAllById(int id);
    TeamDAO findByName(String name);
}
