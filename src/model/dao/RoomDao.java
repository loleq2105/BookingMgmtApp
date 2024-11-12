package model.dao;

import model.entities.Room;

import java.util.List;

public interface RoomDao {

    void insert(Room room);

    void deleteById(int id);

    List<Room> findAll();

    Room findById(int id);

}
