package com.example.demo.model.adminMsg;

import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
public class AdminMsgDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "msg_seq", initialValue = 1, allocationSize = 1)
    private int id;
    @NotNull
    private String msg;
    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private _User _user;


    AdminMsgDAO(){}

    AdminMsgDAO(String msg, _User _user){
        this.msg = msg;
        this._user= _user;
    }



}
