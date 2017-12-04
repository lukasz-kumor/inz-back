package com.example.demo.model.hall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HallResponse {
    private int id;
    private String description;
    private String price;
    private String adress;
    private String city;
    private String number;

    public HallResponse(){

    }
    public HallResponse(HallDAO hall){
        this.id=hall.getId();
        this.description=hall.getDescription();
        this.price=hall.getPrice();
        this.adress=hall.getAdress();
        this.city=hall.getCity();
        this.number=hall.getNumber();

    }


    @Override
    public String toString(){
        return "HallResponse{" +
                "description="+description +
                ",price="+price +
                ",adress="+adress +
                ",city="+city +
                ",number="+number +
                "}";
    }
}
