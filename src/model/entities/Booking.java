package model.entities;

import java.time.LocalDate;
import java.util.Date;

public class Booking {

    private int id;
    private int guestId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking() {
    }

    public Booking(LocalDate endDate, LocalDate startDate, int roomId, int guestId) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.roomId = roomId;
        this.guestId = guestId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("Booking [id=%s, guestId=%s, roomId=%s, startDate=%s, endDate=%s]", id, guestId, roomId, startDate, endDate);
    }
}
