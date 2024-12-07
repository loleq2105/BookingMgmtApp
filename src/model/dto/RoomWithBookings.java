package model.dto;

import model.entities.Booking;
import model.entities.Room;

import java.util.List;

public class RoomWithBookings {

    private Room room;
    private List<Booking> bookings;

    public RoomWithBookings(Room room, List<Booking> bookings) {
        this.room = room;
        this.bookings = bookings;
    }

    public Room getRoom() {
        return room;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
