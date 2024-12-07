package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;

import java.util.List;

public interface BookingDao {

    void insert(Booking booking);

    void deleteById(int id);

    List<Booking> findAll();

    Booking findById(int id);

    List<Booking> findByGuest(int guestId);

    List<Booking> findByRoom(int roomId);
}
