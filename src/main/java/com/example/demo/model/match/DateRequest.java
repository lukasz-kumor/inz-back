package com.example.demo.model.match;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateRequest {

private String dateFrom;
private String dateTo;

public DateRequest(){}
public DateRequest(String dateFrom, String dateTo){
    this.dateFrom = dateFrom;
    this.dateTo=dateTo;
}
}
