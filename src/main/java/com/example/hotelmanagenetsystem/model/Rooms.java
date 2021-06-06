package com.example.hotelmanagenetsystem.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity

public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Please Enter hotel room number")
    private String roomsNumber;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;
    //@NotNull(message = "Please Enter room price")
    private int RoomPrice;
    @Column
    private String Photos;


    public Rooms() {
    }

    public Rooms(String roomsNumber, RoomType roomType, RoomStatus roomStatus,int RoomPrice) {
        this.roomsNumber = roomsNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.RoomPrice=RoomPrice;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(String roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
    

	public int getRoomPrice() {
		return RoomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		RoomPrice = roomPrice;
	}

	public String getPhotos() {
		return Photos;
	}

	public void setPhotos(String photos) {
		Photos = photos;
	}

	@Transient
	public String getPhotosImagePath() {
		if (Photos == null || id == null) {
			return null;
		}
		else {
		return "/hotel-photos/" + id + "/" + Photos;
		}
	}

}
