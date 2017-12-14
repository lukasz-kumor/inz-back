package com.example.demo.model.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateRequest {

private String dateFrom;
private String dateTo;
private String city;

public DateRequest(){}
public DateRequest(String dateFrom, String dateTo,String city){
    this.dateFrom = dateFrom;
    this.dateTo=dateTo;
    this.city=city;
}
}
