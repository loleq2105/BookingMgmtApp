package model.dto;

import model.entities.Room;
import model.entities.RoomType;

public class RoomDetails {

    private Room room;
    private RoomType roomType;

    public RoomDetails(Room room, RoomType roomType) {
        this.room = room;
        this.roomType = roomType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return String.format("RoomDetails [room=%s, roomType=%s]", room, roomType);
    }
}
