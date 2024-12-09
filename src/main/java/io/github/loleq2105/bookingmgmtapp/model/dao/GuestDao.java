package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;

import java.util.List;

public interface GuestDao {

    void insert(Guest guest);

    void deleteById(int id);

    List<Guest> findAll();

    Guest findById(int id);


}
