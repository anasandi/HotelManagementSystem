package com.example.hotelmanagenetsystem.repository;

import com.example.hotelmanagenetsystem.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {
}
