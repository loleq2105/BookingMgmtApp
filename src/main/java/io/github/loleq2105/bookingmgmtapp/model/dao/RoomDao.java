package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.Room;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Room entities.
 */
public interface RoomDao {

    /**
     * Inserts a new room into the database.
     *
     * @param room the room to be inserted
     */
    void insert(Room room);

    /**
     * Deletes a room from the database by its ID.
     *
     * @param id the ID of the room to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves all rooms from the database.
     *
     * @return a list of all rooms
     */
    List<Room> findAll();

    /**
     * Finds a room in the database by its ID.
     *
     * @param id the ID of the room to be found
     * @return the room with the specified ID, or null if not found
     */
    Room findById(int id);

}