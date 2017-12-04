package com.example.demo.model.adminMsg;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminMsgRepository extends CrudRepository<AdminMsgDAO, Integer> {

        AdminMsgDAO findById(int id);
        AdminMsgDAO findBy_user_Id(int id);
        List<AdminMsgDAO> findAll();

}
