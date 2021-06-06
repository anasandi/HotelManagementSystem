package com.example.hotelmanagenetsystem.exception;

public class RoomNotFoundException extends RuntimeException {

    public  RoomNotFoundException(String name){
        super(name);
    }
    public RoomNotFoundException(){

    }


}
