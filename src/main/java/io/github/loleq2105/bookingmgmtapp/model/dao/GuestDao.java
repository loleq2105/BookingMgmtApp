package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Guest entities.
 */
public interface GuestDao {

    /**
     * Inserts a new guest into the database.
     *
     * @param guest the guest to be inserted
     */
    void insert(Guest guest);

    /**
     * Deletes a guest from the database by its ID.
     *
     * @param id the ID of the guest to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves all guests from the database.
     *
     * @return a list of all guests
     */
    List<Guest> findAll();

    /**
     * Finds a guest in the database by its ID.
     *
     * @param id the ID of the guest to be found
     * @return the guest with the specified ID, or null if not found
     */
    Guest findById(int id);
}