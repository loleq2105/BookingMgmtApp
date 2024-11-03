package model.entities;

import java.util.Date;

public class Booking {

    private int id;
    private int guestId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    public Booking() {
    }

    public Booking(Date endDate, Date startDate, int roomId, int guestId) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
