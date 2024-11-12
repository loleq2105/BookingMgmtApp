package model.dao;

import model.entities.Booking;

import java.util.List;

public interface BookingDao {

    void insert(Booking booking);

    void deleteById(int id);

    List<Booking> findAll();

    Booking findById(int id);

    List<Booking> findByGuest(int guestId);

    List<Booking> findByRoom(int roomId);
}
