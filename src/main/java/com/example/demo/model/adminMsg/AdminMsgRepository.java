package com.example.demo.model.adminMsg;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminMsgRepository extends CrudRepository<AdminMsgDTO, Integer> {

        AdminMsgDTO findById(int id);
        AdminMsgDTO findBy_user_Id(int id);
        List<AdminMsgDTO> findAll();

}
