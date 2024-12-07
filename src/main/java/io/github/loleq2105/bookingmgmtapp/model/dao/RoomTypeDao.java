package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.model.entities.RoomType;

import java.util.List;

public interface RoomTypeDao {

    List<RoomType> findAll();

    RoomType findById(int id);

}
