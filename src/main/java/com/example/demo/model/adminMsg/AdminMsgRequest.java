package com.example.demo.model.adminMsg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMsgRequest {

   private int id;
   private String msg;



   public AdminMsgRequest(){}
   public AdminMsgRequest(int id, String msg){
       this.id=id;
       this.msg=msg;
   }
}
