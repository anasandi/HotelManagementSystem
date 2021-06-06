package com.example.hotelmanagenetsystem.service;

import com.example.hotelmanagenetsystem.model.Bookings;

import java.util.List;

public interface BookingService {

    Bookings create(Bookings bookings);
    List<Bookings> findAll();
}

