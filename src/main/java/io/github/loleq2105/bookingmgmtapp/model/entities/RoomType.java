package io.github.loleq2105.bookingmgmtapp.model.entities;

/**
 * Represents a room type entity.
 */
public class RoomType {

    private int id;
    private String name;

    /**
     * Default constructor.
     */
    public RoomType() {
    }

    /**
     * Constructs a new RoomType with the specified ID and name.
     *
     * @param id the ID of the room type
     * @param name the name of the room type
     */
    public RoomType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructs a new RoomType with the specified name.
     *
     * @param name the name of the room type
     */
    public RoomType(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the room type.
     *
     * @return the room type ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the room type.
     *
     * @param id the room type ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the room type.
     *
     * @return the room type name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the room type.
     *
     * @param name the room type name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the room type.
     *
     * @return a string representation of the room type
     */
    @Override
    public String toString() {
        return String.format("RoomType [id=%s, name=%s]", id, name);
    }
}