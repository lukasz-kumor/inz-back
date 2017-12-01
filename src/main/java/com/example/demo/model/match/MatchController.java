package com.example.demo.model.match;

import com.example.demo.model.hall.HallRepository;
import com.example.demo.model.team.TeamRepository;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private _UserRepository userRepository;
    @Autowired
    private HallRepository hallRepository;



}
