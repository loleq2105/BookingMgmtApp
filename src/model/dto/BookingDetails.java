package model.dto;

import model.entities.Booking;
import model.entities.Guest;

public class BookingDetails {

    private Booking booking;
    private Guest guest;
    private RoomDetails roomDetails;

    public BookingDetails(Booking booking, Guest guest, RoomDetails roomDetails) {
        this.booking = booking;
        this.guest = guest;
        this.roomDetails = roomDetails;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    public Guest getGuest() {
        return guest;
    }

    public Booking getBooking() {
        return booking;
    }

    @Override
    public String toString() {
        return String.format("BookingDetails [booking=%s, guest=%s, roomDetails=%s]", booking, guest, roomDetails);
    }
}
