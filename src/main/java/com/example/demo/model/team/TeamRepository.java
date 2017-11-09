package com.example.demo.model.team;

import com.example.demo.model.user._User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamDTO, Integer> {

    TeamDTO findById(int id);
    List<TeamDTO> findAllById(int id);

}
