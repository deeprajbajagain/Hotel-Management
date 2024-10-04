package com.hotelmanagement.util;

import com.hotelmanagement.data.Guest;
import com.hotelmanagement.data.GuestRepository;
import com.hotelmanagement.data.Room;
import com.hotelmanagement.data.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public AppStartupEvent(GuestRepository guestRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Guest> guests = this.guestRepository.findAll();
        Iterable<Room> rooms = this.roomRepository.findAll();

        guests.forEach(System.out::println);
        rooms.forEach(System.out::println);
    }
}
