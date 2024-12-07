package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.db.Database;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.BookingDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.GuestDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.RoomDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.RoomTypeDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;

public class DaoFactory {

    public static GuestDao createGuestDao() {
        return new GuestDaoJDBC(Database.getConnection());
    }

    public static RoomDao createRoomDao() { return new RoomDaoJDBC(Database.getConnection()); }

    public static RoomTypeDao createRoomTypeDao() { return new RoomTypeDaoJDBC(Database.getConnection()); }

    public static BookingDao createBookingDao() { return new BookingDaoJDBC(Database.getConnection()); }

}