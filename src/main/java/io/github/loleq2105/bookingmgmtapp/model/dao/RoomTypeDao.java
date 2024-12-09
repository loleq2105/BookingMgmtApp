package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.RoomType;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing RoomType entities.
 */
public interface RoomTypeDao {

    /**
     * Retrieves all room types from the database.
     *
     * @return a list of all room types
     */
    List<RoomType> findAll();

    /**
     * Finds a room type in the database by its ID.
     *
     * @param id the ID of the room type to be found
     * @return the room type with the specified ID, or null if not found
     */
    RoomType findById(int id);

}