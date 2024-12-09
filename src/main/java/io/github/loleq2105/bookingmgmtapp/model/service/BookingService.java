package io.github.loleq2105.bookingmgmtapp.model.service;

import io.github.loleq2105.bookingmgmtapp.model.dao.BookingDao;
import io.github.loleq2105.bookingmgmtapp.model.dao.GuestDao;
import io.github.loleq2105.bookingmgmtapp.model.dao.RoomDao;
import io.github.loleq2105.bookingmgmtapp.model.dao.RoomTypeDao;
import io.github.loleq2105.bookingmgmtapp.model.dto.BookingDetails;
import io.github.loleq2105.bookingmgmtapp.model.dto.RoomDetails;
import io.github.loleq2105.bookingmgmtapp.model.dto.RoomWithBookings;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;
import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;
import io.github.loleq2105.bookingmgmtapp.model.entities.Room;
import io.github.loleq2105.bookingmgmtapp.model.entities.RoomType;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {

    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private GuestDao guestDao;
    private BookingDao bookingDao;

    public BookingService(RoomDao roomDao, RoomTypeDao roomTypeDao, GuestDao guestDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.roomTypeDao = roomTypeDao;
        this.guestDao = guestDao;
        this.bookingDao = bookingDao;
    }

    public List<RoomDetails> findAllRooms(){
        List<Room> rooms = roomDao.findAll();
        List<RoomDetails> roomDetailsList = new ArrayList<>();

        for(Room room : rooms){
            roomDetailsList.add(assembleRoomDetails(room));
        }

        return roomDetailsList;
    }

    public RoomDetails findRoom(int roomId){
        Room room = roomDao.findById(roomId);
        RoomDetails roomDetails = assembleRoomDetails(room);

        return roomDetails;
    }

    public void insertRoom(Room room){
        roomDao.insert(room);
    }

    public void deleteRoom(int roomId){
        roomDao.deleteById(roomId);
    }

    public List<BookingDetails> findAllBookings(){
        List<Booking> bookings = bookingDao.findAll();
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        for(Booking booking : bookings){
            bookingDetailsList.add(assembleBookingDetails(booking));
        }

        return bookingDetailsList;
    }

    public List<Guest> findAllGuests(){
        return guestDao.findAll();
    }

    public Guest findGuest(int guestId){
        return guestDao.findById(guestId);
    }

    public ServiceResponse insertGuest(Guest guest){
        //TODO: Add validity check
        guestDao.insert(guest);
        return new ServiceResponse(true, new ArrayList<>());
    }

    public void deleteGuest(int guestId){
        guestDao.deleteById(guestId);
    }

    public BookingDetails findBooking(int bookingId){
        Booking booking = bookingDao.findById(bookingId);
        BookingDetails bookingDetails = assembleBookingDetails(booking);

        return bookingDetails;

    }

    public ServiceResponse insertBooking(Booking booking){
        //Perform validity check, e.g. if room is free in the given time range. If not, return a ServiceResponse with success=false and messages for the user.
        //If everything is OK, insert the booking and return a ServiceResponse with success=true and an empty list of messages.
        List<String> messages = new ArrayList<>(); //List of messages to be displayed to the user

        if(!roomIsFreeInRange(roomDao.findById(booking.getRoomId()), booking.getStartDate(), booking.getEndDate())) {
            messages.add("Pokój jest zajęty w podanym terminie");
            return new ServiceResponse(false, messages);
        }

        bookingDao.insert(booking);
        return new ServiceResponse(true, messages);
    }

    public void deleteBooking(int bookingId){
        bookingDao.deleteById(bookingId);
    }

    public List<RoomWithBookings> findAllRoomsWithBookings(){
        List<Room> rooms = roomDao.findAll();
        List<RoomWithBookings> roomsWithBookingsList = new ArrayList<>();

        for(Room room : rooms){
            List<Booking> bookings = bookingDao.findByRoom(room.getId());
            roomsWithBookingsList.add(new RoomWithBookings(room, bookings));
        }

        return roomsWithBookingsList;
    }

    public List<RoomDetails> findRoomsFreeInRange(LocalDate start, LocalDate end){

        ArrayList<Room> rooms = (ArrayList<Room>) roomDao.findAll();
        ArrayList<RoomDetails> roomDetailsList = new ArrayList<>();

        for(Room room : rooms) {
            if (roomIsFreeInRange(room, start, end)) {
                roomDetailsList.add(assembleRoomDetails(room));
            }
        }

        return roomDetailsList;

    }

    public boolean roomIsFreeInRange(Room room, LocalDate start, LocalDate end){
        List<Booking> bookings = bookingDao.findByRoom(room.getId());

        for(Booking booking : bookings){
            if(doDateRangesOverlap(start, end, booking.getStartDate(), booking.getEndDate())){
                return false;
            }
        }

        return true;
    }

    public RoomDetails assembleRoomDetails(Room room){
        RoomType roomType = roomTypeDao.findById(room.getTypeId());
        return new RoomDetails(room, roomType);
    }

    public BookingDetails assembleBookingDetails(Booking booking){
        Guest guest = guestDao.findById(booking.getGuestId());
        RoomDetails roomDetails = findRoom(booking.getRoomId());
        return new BookingDetails(booking, guest, roomDetails);
    }

    private boolean doDateRangesOverlap(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        // Check if the start of range A is before or on the end of range B
        // and the start of range B is before or on the end of range A
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }


}
