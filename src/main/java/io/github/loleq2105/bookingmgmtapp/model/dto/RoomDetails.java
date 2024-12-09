package io.github.loleq2105.bookingmgmtapp.model.dto;

import io.github.loleq2105.bookingmgmtapp.model.entities.Room;
import io.github.loleq2105.bookingmgmtapp.model.entities.RoomType;

/**
 * Data Transfer Object (DTO) for room details.
 */
public class RoomDetails {

    private Room room;
    private RoomType roomType;

    /**
     * Constructs a new RoomDetails instance with the specified room and room type.
     *
     * @param room the room entity
     * @param roomType the room type entity
     */
    public RoomDetails(Room room, RoomType roomType) {
        this.room = room;
        this.roomType = roomType;
    }

    /**
     * Gets the room type details.
     *
     * @return the room type details
     */
    public RoomType getRoomType() {
        return roomType;
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
     * Returns a string representation of the room details.
     *
     * @return a string representation of the room details
     */
    @Override
    public String toString() {
        return String.format("RoomDetails [room=%s, roomType=%s]", room, roomType);
    }
}