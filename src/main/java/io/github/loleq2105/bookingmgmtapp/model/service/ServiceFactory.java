package io.github.loleq2105.bookingmgmtapp.model.service;

import io.github.loleq2105.bookingmgmtapp.model.dao.DaoFactory;

/**
 * Factory class for creating service instances.
 */
public class ServiceFactory {

    /**
     * Creates a new instance of BookingService.
     *
     * @return a new BookingService instance
     */
    public static BookingService createBookingService(){
        return new BookingService(
                DaoFactory.createRoomDao(),
                DaoFactory.createRoomTypeDao(),
                DaoFactory.createGuestDao(),
                DaoFactory.createBookingDao()
        );
    }

}