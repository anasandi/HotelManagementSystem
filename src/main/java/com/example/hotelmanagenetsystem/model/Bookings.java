package com.example.hotelmanagenetsystem.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity

public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingNumber;
    private int numberOfAdults;
    private int numberOfChildren;
    private String fromStart;
    private String toEnd;
    private boolean bookingCancelled;
    private double inAdvance;
    private double fullCharges;


    @ManyToOne
    private UserProfile userProfile;

    @ManyToOne
    private Rooms rooms;


    public Bookings() {
    }

    public Bookings(String bookingNumber, int numberOfAdults, int numberOfChildren, String fromStart, String toEnd, boolean bookingCancelled, double inAdvance, double fullCharges,UserProfile userProfile,Rooms rooms) {
        this.bookingNumber = bookingNumber;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.fromStart = fromStart;
        this.toEnd = toEnd;
        this.bookingCancelled = bookingCancelled;
        this.inAdvance = inAdvance;
        this.fullCharges = fullCharges;
        this.userProfile = userProfile;
        this.rooms=rooms;   
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    
    /*public LocalDate getFrom() {
        return fromStart;
    }

    public LocalDate getFromStart() {
        return fromStart;
    }

    public LocalDate getToEnd() {
        return toEnd;
    }

    public void setFrom(LocalDate from) {
        this.fromStart = from;
    }

    public LocalDate getTo() {
        return toEnd;
    }

    public void setTo(LocalDate to) {
        this.toEnd = to;
    }*/

    public String getFromStart() {
		return fromStart;
	}

	public void setFromStart(String fromStart) {
		this.fromStart = fromStart;
	}

	public String getToEnd() {
		return toEnd;
	}

	public void setToEnd(String toEnd) {
		this.toEnd = toEnd;
	}

	public boolean isBookingCancelled() {
        return bookingCancelled;
    }

    public void setBookingCancelled(boolean bookingCancelled) {
        this.bookingCancelled = bookingCancelled;
    }

    public double getInAdvance() {
        return inAdvance;
    }

    public void setInAdvance(double inAdvance) {
        this.inAdvance = inAdvance;
    }

    public double getFullCharges() {
        return fullCharges;
    }

    public void setFullCharges(double fullCharges) {
        this.fullCharges = fullCharges;
    }

   public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

	public Rooms getRooms() {
		return rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}


}
