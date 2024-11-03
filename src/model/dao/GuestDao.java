package model.dao;

import model.entities.Guest;

import java.util.List;

public interface GuestDao {

    void insert(Guest guest);

    void deleteById(int id);

    List<Guest> findAll();

    Guest findById(int id);

}
