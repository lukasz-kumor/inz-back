package com.example.demo.security.service;

import com.example.demo.model.user._UserRepository;
import com.example.demo.model.user._User;

import com.example.demo.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private _UserRepository userRepository;


    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        _User _user = userRepository.findByEmail(username);


        if(_user == null )
            throw new UsernameNotFoundException("No user found with username: " + username);



        return new JwtUser(_user);
    }
}
