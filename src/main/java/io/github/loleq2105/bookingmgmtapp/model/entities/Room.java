package io.github.loleq2105.bookingmgmtapp.model.entities;

/**
 * Represents a room entity.
 */
public class Room {

    private int id;
    private String number;
    private int capacity;
    private int typeId;
    private int price;

    /**
     * Default constructor.
     */
    public Room() {
    }

    /**
     * Constructs a new Room with the specified details.
     *
     * @param number the room number
     * @param capacity the capacity of the room
     * @param typeId the ID of the room type
     * @param price the price of the room
     */
    public Room(String number, int capacity, int typeId, int price) {
        this.number = number;
        this.capacity = capacity;
        this.typeId = typeId;
        this.price = price;
    }

    /**
     * Gets the price of the room.
     *
     * @return the room price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the room.
     *
     * @param price the room price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the ID of the room type.
     *
     * @return the room type ID
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets the ID of the room type.
     *
     * @param typeId the room type ID
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * Gets the capacity of the room.
     *
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the room.
     *
     * @param capacity the room capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets the room number.
     *
     * @return the room number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the room number.
     *
     * @param number the room number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the ID of the room.
     *
     * @return the room ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the room.
     *
     * @param id the room ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the room.
     *
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return String.format("Room [id=%s, number=%s, capacity=%s, typeId=%s, price=%s]", id, number, capacity, typeId, price);
    }
}