package model.dao;

import model.entities.RoomType;

import java.util.List;

public interface RoomTypeDao {

    List<RoomType> findAll();

    RoomType findById(int id);

}
