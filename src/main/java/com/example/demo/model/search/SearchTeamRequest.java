package com.example.demo.model.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchTeamRequest {

    private String name;
    private String city;
public SearchTeamRequest(){}
    public SearchTeamRequest(String name, String city){
        this.name=name;
        this.city=city;
    }

}
