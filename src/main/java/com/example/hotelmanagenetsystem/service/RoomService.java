package com.example.hotelmanagenetsystem.service;

import com.example.hotelmanagenetsystem.model.Rooms;

import java.util.List;

public interface RoomService {

    Rooms create(Rooms rooms);
    Rooms findById(Long id);
    List<Rooms> findAll();
    void delete(Long id);
    Rooms update(Long id,Rooms rooms);
    Rooms findByRoomNumber(String roomNumber);
}
