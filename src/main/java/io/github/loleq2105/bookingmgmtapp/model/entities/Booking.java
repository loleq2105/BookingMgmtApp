package io.github.loleq2105.bookingmgmtapp.model.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 * Represents a booking entity.
 */
public class Booking {

    private int id;
    private int guestId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Default constructor.
     */
    public Booking() {
    }

    /**
     * Constructs a new Booking with the specified details.
     *
     * @param endDate the end date of the booking
     * @param startDate the start date of the booking
     * @param roomId the ID of the room being booked
     * @param guestId the ID of the guest making the booking
     */
    public Booking(LocalDate endDate, LocalDate startDate, int roomId, int guestId) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.roomId = roomId;
        this.guestId = guestId;
    }

    /**
     * Gets the ID of the booking.
     *
     * @return the booking ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the booking.
     *
     * @param id the booking ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the guest making the booking.
     *
     * @return the guest ID
     */
    public int getGuestId() {
        return guestId;
    }

    /**
     * Sets the ID of the guest making the booking.
     *
     * @param guestId the guest ID
     */
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    /**
     * Gets the ID of the room being booked.
     *
     * @return the room ID
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Sets the ID of the room being booked.
     *
     * @param roomId the room ID
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets the start date of the booking.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the booking.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the booking.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the booking.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the booking.
     *
     * @return a string representation of the booking
     */
    @Override
    public String toString() {
        return String.format("Booking [id=%s, guestId=%s, roomId=%s, startDate=%s, endDate=%s]", id, guestId, roomId, startDate, endDate);
    }
}