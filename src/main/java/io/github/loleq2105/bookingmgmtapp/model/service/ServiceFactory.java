package io.github.loleq2105.bookingmgmtapp.model.service;

import io.github.loleq2105.bookingmgmtapp.model.dao.DaoFactory;

public class ServiceFactory {

    public static BookingService createBookingService(){
        return new BookingService(
                DaoFactory.createRoomDao(),
                DaoFactory.createRoomTypeDao(),
                DaoFactory.createGuestDao(),
                DaoFactory.createBookingDao()
        );
    }

}
