package com.example.hotelmanagenetsystem.aspect;

import com.example.hotelmanagenetsystem.exception.RoomNotFoundException;
import com.example.hotelmanagenetsystem.model.Rooms;
import com.example.hotelmanagenetsystem.repository.RoomRepostiory;
import com.example.hotelmanagenetsystem.service.RoomService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Aspect
@Component
public class HotelAspect {
    private Logger logger=(Logger)LoggerFactory.getLogger(this.getClass());
    private final RoomService roomService;
    private final RoomRepostiory roomRepostiory;

    public HotelAspect(RoomService roomService, RoomRepostiory roomRepostiory) {
        this.roomService = roomService;
        this.roomRepostiory = roomRepostiory;
    }

    @Before("execution(* *.ProcessRoom(..))")
    public void roomsNumberAspect(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        boolean roomNumberSame=false;
        Rooms room=(Rooms)args[0];
        logger.info("Room number"+room.getRoomsNumber());
       /*Optional<Rooms> room=Optional.of((Rooms)args[0]);
       room.orElseThrow(()->new IllegalArgumentException("Room not found"));*/
       // if (args[0] instanceof Rooms){
             //room=(Rooms)args[0];
           for (Rooms rooms:roomService.findAll()){
                if (rooms.getRoomsNumber().equals(room.getRoomsNumber())){
                    roomNumberSame=true;
                }
            }
            if(roomNumberSame){
                throw new IllegalArgumentException(room.getRoomsNumber()+"Rooms are already exist");
            }
           //roomService.findAll().stream().filter(r->!(r.getRoomsNumber().equals(room.get().getId())))
           //.findAny()
           //.orElseThrow(()->new IllegalArgumentException(room.get().getId()+"room already exists"));

        }
        @Before("execution(* *.showRoomdetail(..))")
        public void roomsNotfoundaspect(JoinPoint joinPoint){
            Object[] args=joinPoint.getArgs();
            Rooms rooms=roomService.findById((long)args[1]);
            if(rooms==null){
                throw new EntityNotFoundException((long)args[1]+"not found");
            }
        }
        @Before("execution(* *.findByRoomNumber(..))")
        public void roomNotFoundFindAspect(JoinPoint joinPoint){
             Object[]args=joinPoint.getArgs();
             Rooms rooms=roomRepostiory.findByRoomsNumber(String.valueOf(args[0])).orElseThrow(()->new RoomNotFoundException(args[0]+"not found"));

        }
    }

