package com.example.demo.model.user;

import com.example.demo.model.team.TeamDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface _UserRepository extends CrudRepository<_User, Integer> {


    _User findById(Integer id );
    _User findByEmail(String email);
    List<_User> findAll();
    _User findByCode(String code);


    List<_User> findByAndTeamDTO_IdOrderByRole(int id);

}
