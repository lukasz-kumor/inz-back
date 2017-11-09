package com.example.demo.model.adminMsg;

import com.example.demo.model.user._User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminMsgResponse {

    private int id;
    private String msg;
    private _User _user;

    public AdminMsgResponse(AdminMsgDTO adminMsgDTO){
        this.id=adminMsgDTO.getId();
        this.msg=adminMsgDTO.getMsg();
        this._user=adminMsgDTO.get_user();

    }
    public String toString(){
        return "MsgResponse{" +
                "id="+id +
                "msg="+msg+
                "user="+_user+
                "}";
    }

}
