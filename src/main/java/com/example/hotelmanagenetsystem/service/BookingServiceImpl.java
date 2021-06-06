package com.example.hotelmanagenetsystem.service;

import com.example.hotelmanagenetsystem.model.Bookings;
import com.example.hotelmanagenetsystem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Bookings create(Bookings bookings) {
        return this.bookingRepository.save(bookings);
    }

    @Override
    public List<Bookings> findAll() {
        return bookingRepository.findAll();
    }
}
