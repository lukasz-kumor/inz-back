package com.example.demo.model.adminMsg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminAnswer {
    private String email;
    private String msgTo;
    private String msgFrom;
    public AdminAnswer(){
    }
    public AdminAnswer(String email,String msgTo){
        this.email=email;
        this.msgTo=msgTo;
    }
    public AdminAnswer(String email, String msgTo,String msgFrom){
        this.email=email;
        this.msgFrom=msgFrom;
        this.msgTo=msgTo;
    }

}
