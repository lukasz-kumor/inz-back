package com.example.demo.model.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface _UserRepository extends CrudRepository<_User, Integer> {


    _User findById(Integer id );
    _User findByEmail(String email);
    List<_User> findAll();
    _User findByCode(String code);

}
