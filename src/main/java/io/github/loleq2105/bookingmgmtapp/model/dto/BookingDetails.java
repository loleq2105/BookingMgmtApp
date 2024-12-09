package io.github.loleq2105.bookingmgmtapp.model.dto;

import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;
import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;

/**
 * Data Transfer Object (DTO) for booking details.
 */
public class BookingDetails {

    private Booking booking;
    private Guest guest;
    private RoomDetails roomDetails;

    /**
     * Constructs a new BookingDetails instance with the specified booking, guest, and room details.
     *
     * @param booking the booking entity
     * @param guest the guest entity
     * @param roomDetails the room details
     */
    public BookingDetails(Booking booking, Guest guest, RoomDetails roomDetails) {
        this.booking = booking;
        this.guest = guest;
        this.roomDetails = roomDetails;
    }

    /**
     * Gets the room details.
     *
     * @return the room details
     */
    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    /**
     * Gets the guest details.
     *
     * @return the guest details
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Gets the booking details.
     *
     * @return the booking details
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Returns a string representation of the booking details.
     *
     * @return a string representation of the booking details
     */
    @Override
    public String toString() {
        return String.format("BookingDetails [booking=%s, guest=%s, roomDetails=%s]", booking, guest, roomDetails);
    }

}