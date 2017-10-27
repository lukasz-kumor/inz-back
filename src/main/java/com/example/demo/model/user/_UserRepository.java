package com.example.demo.model.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface _UserRepository extends CrudRepository<_User, Integer> {


    _User findById(Integer id );
    _User findByEmail(String email);

    _User findByCode(String code);

}
