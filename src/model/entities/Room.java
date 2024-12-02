package model.entities;

public class Room {

    private int id;
    private String number;
    private int capacity;
    private int typeId;
    private int price;

    public Room() {
    }

    public Room(String number, int capacity, int typeId, int price) {
        this.number = number;
        this.capacity = capacity;
        this.typeId = typeId;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Room [id=%s, number=%s, capacity=%s, typeId=%s, price=%s]", id, number, capacity, typeId, price);
    }
}
