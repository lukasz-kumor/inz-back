package com.example.demo.security.controller;


import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import com.example.demo.security.JwtAuthenticationRequest;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.security.JwtUser;

import com.example.demo.security.service.JwtAuthenticationResponse;
import com.example.demo.security.service.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private _UserRepository userRepository;

    @PostMapping("/auth")
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtAuthenticationRequest.getEmail(),
                            jwtAuthenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(AuthenticationException e) {
        }


        JwtUser user =  jwtUserDetailsService.loadUserByUsername(jwtAuthenticationRequest.getEmail());
        String token = jwtTokenUtil.generateToken(user);


        if(!user.getPassword().equals(jwtAuthenticationRequest.getPassword()))
            return new ResponseEntity<JwtAuthenticationResponse>(HttpStatus.BAD_REQUEST);


        _User _user = userRepository.findById(user.getId());
        String name = _user.getName();
        String lastname = _user.getLastname();
        int id = _user.getId();
        String email = _user.getEmail();
        String usertype = _user.getRole();

//logback na pełnej

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token,email,usertype,name,lastname,id);
        return ResponseEntity.ok(response);
    }
}
