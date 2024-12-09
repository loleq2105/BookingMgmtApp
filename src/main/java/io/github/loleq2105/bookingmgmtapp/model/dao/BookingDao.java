package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Booking entities.
 */
public interface BookingDao {

    /**
     * Inserts a new booking into the database.
     *
     * @param booking the booking to be inserted
     */
    void insert(Booking booking);

    /**
     * Deletes a booking from the database by its ID.
     *
     * @param id the ID of the booking to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves all bookings from the database.
     *
     * @return a list of all bookings
     */
    List<Booking> findAll();

    /**
     * Finds a booking in the database by its ID.
     *
     * @param id the ID of the booking to be found
     * @return the booking with the specified ID, or null if not found
     */
    Booking findById(int id);

    /**
     * Finds bookings in the database by the guest's ID.
     *
     * @param guestId the ID of the guest
     * @return a list of bookings for the specified guest
     */
    List<Booking> findByGuest(int guestId);

    /**
     * Finds bookings in the database by the room's ID.
     *
     * @param roomId the ID of the room
     * @return a list of bookings for the specified room
     */
    List<Booking> findByRoom(int roomId);
}