package com.example.demo.model.hall;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class HallDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "public", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
    private int id;

    private String description;
    @NotNull
    private String price;
    @NotNull
    private String adress;
    @NotNull
    private String city;
    @NotNull
    private String number;
    @NotNull
    private boolean activated=false;

    public HallDTO(){}

    public HallDTO(String description, String price, String adress,String city, String number) {
        this.description = description;
        this.price = price;
        this.adress = adress;
        this.number=number;
        this.city=city;
    }
    public HallDTO(String price, String adress,String city, String number) {
        this.price = price;
        this.adress = adress;
        this.number=number;
        this.city=city;
    }
    @Override
    public String toString(){
        return "Hall{" +
                "id="+id +
                ",description="+description +
                ",price="+price +
                ",adress="+adress +
                ",city="+city+
                ",number="+number+
                ",activated="+activated+
                "}";
    }

}
