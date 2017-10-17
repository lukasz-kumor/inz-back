package com.example.demo.security;
import com.example.demo.model.user._User;




public class JwtUserFactory {

    private JwtUserFactory(){};

    public static JwtUser createJwtUser(_User _user){
        return new JwtUser(_user);
    }


}
