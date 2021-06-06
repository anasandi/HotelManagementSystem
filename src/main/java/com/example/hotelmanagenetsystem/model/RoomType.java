package com.example.hotelmanagenetsystem.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public enum  RoomType {
	
	delux,
	Luxury,
	Economy
	
}

    /*delux("3 person",200000),
    Luxury("3 person",300000),
    Economy("3 person",80000),
    ExtraBed("1 person",30000)
    ;

    RoomType(String name,double price){
        this.name= name;
        this.price=price;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }



    String name;
    double price;
}*/
