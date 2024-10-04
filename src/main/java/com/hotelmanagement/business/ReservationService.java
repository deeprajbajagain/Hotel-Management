package com.hotelmanagement.business;

import com.hotelmanagement.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    // Repositories for accessing room, guest, and reservation data
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    // Constructor to inject dependencies (repositories)
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    // method to fetch room reservations for specific date.
    public List<RoomReservation> getRoomReservationsForDate(Date date){
        // fetch all rooms from the repository
        Iterable<Room> rooms = this.roomRepository.findAll();

        // create a map to associate room IDs with RoomReservation objects
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        // iterate through all rooms and initialize RoomReservation objects

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(),roomReservation);
        });
        // fetch reservations for the given date
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()) );

        // for each reservation, update the corresponding Room Reservation object with guest details
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);

            Guest guest = this.guestRepository.findById(reservation.getGuestId()).orElse(null);

            if(guest != null){
                roomReservation.setGuestId(guest.getGuestId());
                roomReservation.setFirstName(guest.getFirstName());
                roomReservation.setLastName(guest.getFirstName());
            }
        });

        // convert the map of RoomReservation objects to a list
        return getRoomReservations(roomReservationMap);
    }

    private static List<RoomReservation> getRoomReservations(Map<Long, RoomReservation> roomReservationMap) {
        List<RoomReservation> roomReservations = new ArrayList<>(roomReservationMap.values());

        // sort the room reservations by room name, then by room number

        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if(o1.getRoomName().equals(o2.getRoomName())){
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    // method to fetch all guests in the hotel and sorts the guests by last name, then by first name.
    public List<Guest> getHotelGuests(){
        // fetch all guests from the repository
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();

        // add each guest to the list
        guests.forEach(guestList::add);

        // sort guests by last name, they by first name
        guestList.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                if(o1.getLastName().equals(o2.getLastName())){
                    return o1.getFirstName().compareTo(o2.getFirstName());
                }
                return  o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return guestList;
    }
     /*
    method to fetch all rooms in the hotel.
    sorts rooms by their room number.
    */

    public List<Room> getRooms(){
        // fetch all rooms from the repository
        Iterable<Room> rooms = this.roomRepository.findAll();

        List<Room> roomList = new ArrayList<>();

        // add each room to the list
        rooms.forEach(roomList::add);

        roomList.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
        });
        return roomList;
    }
}

