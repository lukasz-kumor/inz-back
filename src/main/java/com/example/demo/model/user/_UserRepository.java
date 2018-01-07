package com.example.demo.model.user;

import com.example.demo.model.team.TeamDAO;

import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface _UserRepository extends CrudRepository<_User, Integer> {

    _User findById(Integer id );
    _User findByEmail(String email);
    List<_User> findAll();
    _User findByCode(String code);
    List<_User> findAllByTeamDAO(TeamDAO team);
    List<_User> findByAndTeamDAO_IdOrderByRole(int id);

}
