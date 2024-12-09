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

/**
 * Service class for managing bookings, guests, and rooms.
 */
public class BookingService {

    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private GuestDao guestDao;
    private BookingDao bookingDao;

    /**
     * Constructs a new BookingService with the specified DAOs.
     *
     * @param roomDao the DAO for room entities
     * @param roomTypeDao the DAO for room type entities
     * @param guestDao the DAO for guest entities
     * @param bookingDao the DAO for booking entities
     */
    public BookingService(RoomDao roomDao, RoomTypeDao roomTypeDao, GuestDao guestDao, BookingDao bookingDao) {
        this.roomDao = roomDao;
        this.roomTypeDao = roomTypeDao;
        this.guestDao = guestDao;
        this.bookingDao = bookingDao;
    }

    /**
     * Finds all rooms and returns their details.
     *
     * @return a list of room details
     */
    public List<RoomDetails> findAllRooms(){
        List<Room> rooms = roomDao.findAll();
        List<RoomDetails> roomDetailsList = new ArrayList<>();

        for(Room room : rooms){
            roomDetailsList.add(assembleRoomDetails(room));
        }

        return roomDetailsList;
    }

    /**
     * Finds a room by its ID and returns its details.
     *
     * @param roomId the ID of the room
     * @return the details of the room
     */
    public RoomDetails findRoom(int roomId){
        Room room = roomDao.findById(roomId);
        RoomDetails roomDetails = assembleRoomDetails(room);

        return roomDetails;
    }

    /**
     * Inserts a new room.
     *
     * @param room the room entity to insert
     */
    public void insertRoom(Room room){
        roomDao.insert(room);
    }

    /**
     * Deletes a room by its ID.
     *
     * @param roomId the ID of the room to delete
     */
    public void deleteRoom(int roomId){
        roomDao.deleteById(roomId);
    }

    /**
     * Finds all bookings and returns their details.
     *
     * @return a list of booking details
     */
    public List<BookingDetails> findAllBookings(){
        List<Booking> bookings = bookingDao.findAll();
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        for(Booking booking : bookings){
            bookingDetailsList.add(assembleBookingDetails(booking));
        }

        return bookingDetailsList;
    }

    /**
     * Finds all guests.
     *
     * @return a list of guests
     */
    public List<Guest> findAllGuests(){
        return guestDao.findAll();
    }

    /**
     * Finds a guest by their ID.
     *
     * @param guestId the ID of the guest
     * @return the guest entity
     */
    public Guest findGuest(int guestId){
        return guestDao.findById(guestId);
    }

    /**
     * Inserts a new guest and returns a service response.
     *
     * @param guest the guest entity to insert
     * @return the service response
     */
    public ServiceResponse insertGuest(Guest guest){
        //TODO: Add validity check
        guestDao.insert(guest);
        return new ServiceResponse(true, new ArrayList<>());
    }

    /**
     * Deletes a guest by their ID.
     *
     * @param guestId the ID of the guest to delete
     */
    public void deleteGuest(int guestId){
        guestDao.deleteById(guestId);
    }

    /**
     * Finds a booking by its ID and returns its details.
     *
     * @param bookingId the ID of the booking
     * @return the details of the booking
     */
    public BookingDetails findBooking(int bookingId){
        Booking booking = bookingDao.findById(bookingId);
        BookingDetails bookingDetails = assembleBookingDetails(booking);

        return bookingDetails;
    }

    /**
     * Inserts a new booking and returns a service response.
     *
     * @param booking the booking entity to insert
     * @return the service response
     */
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

    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId the ID of the booking to delete
     */
    public void deleteBooking(int bookingId){
        bookingDao.deleteById(bookingId);
    }

    /**
     * Finds all rooms with their bookings.
     *
     * @return a list of rooms with bookings
     */
    public List<RoomWithBookings> findAllRoomsWithBookings(){
        List<Room> rooms = roomDao.findAll();
        List<RoomWithBookings> roomsWithBookingsList = new ArrayList<>();

        for(Room room : rooms){
            List<Booking> bookings = bookingDao.findByRoom(room.getId());
            roomsWithBookingsList.add(new RoomWithBookings(room, bookings));
        }

        return roomsWithBookingsList;
    }

    /**
     * Finds all rooms that are free in the given date range.
     *
     * @param start the start date
     * @param end the end date
     * @return a list of room details
     */
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

    /**
     * Checks if a room is free in the given date range.
     *
     * @param room the room entity
     * @param start the start date
     * @param end the end date
     * @return true if the room is free, false otherwise
     */
    public boolean roomIsFreeInRange(Room room, LocalDate start, LocalDate end){
        List<Booking> bookings = bookingDao.findByRoom(room.getId());

        for(Booking booking : bookings){
            if(doDateRangesOverlap(start, end, booking.getStartDate(), booking.getEndDate())){
                return false;
            }
        }

        return true;
    }

    /**
     * Assembles the details of a room.
     *
     * @param room the room entity
     * @return the room details
     */
    public RoomDetails assembleRoomDetails(Room room){
        RoomType roomType = roomTypeDao.findById(room.getTypeId());
        return new RoomDetails(room, roomType);
    }

    /**
     * Assembles the details of a booking.
     *
     * @param booking the booking entity
     * @return the booking details
     */
    public BookingDetails assembleBookingDetails(Booking booking){
        Guest guest = guestDao.findById(booking.getGuestId());
        RoomDetails roomDetails = findRoom(booking.getRoomId());
        return new BookingDetails(booking, guest, roomDetails);
    }

    /**
     * Checks if two date ranges overlap.
     *
     * @param startA the start date of range A
     * @param endA the end date of range A
     * @param startB the start date of range B
     * @param endB the end date of range B
     * @return true if the date ranges overlap, false otherwise
     */
    private boolean doDateRangesOverlap(LocalDate startA, LocalDate endA, LocalDate startB, LocalDate endB) {
        // Check if the start of range A is before or on the end of range B
        // and the start of range B is before or on the end of range A
        return !startA.isAfter(endB) && !startB.isAfter(endA);
    }
}