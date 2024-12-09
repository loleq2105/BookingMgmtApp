package io.github.loleq2105.bookingmgmtapp.model.dto;

import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;
import io.github.loleq2105.bookingmgmtapp.model.entities.Room;

import java.util.List;

/**
 * Data Transfer Object (DTO) for room with bookings details.
 */
public class RoomWithBookings {

    private Room room;
    private List<Booking> bookings;

    /**
     * Constructs a new RoomWithBookings instance with the specified room and bookings.
     *
     * @param room the room entity
     * @param bookings the list of bookings
     */
    public RoomWithBookings(Room room, List<Booking> bookings) {
        this.room = room;
        this.bookings = bookings;
    }

    /**
     * Gets the room details.
     *
     * @return the room details
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Gets the list of bookings.
     *
     * @return the list of bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }
}