package model.service;

import model.dao.DaoFactory;

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
