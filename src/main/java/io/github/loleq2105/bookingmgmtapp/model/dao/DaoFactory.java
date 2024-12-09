package io.github.loleq2105.bookingmgmtapp.model.dao;

import io.github.loleq2105.bookingmgmtapp.db.Database;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.BookingDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.GuestDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.RoomDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.dao.impl.RoomTypeDaoJDBC;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;

/**
 * Factory class for creating DAO instances.
 */
public class DaoFactory {

    /**
     * Creates a new instance of GuestDao.
     *
     * @return a new GuestDao instance
     */
    public static GuestDao createGuestDao() {
        return new GuestDaoJDBC(Database.getConnection());
    }

    /**
     * Creates a new instance of RoomDao.
     *
     * @return a new RoomDao instance
     */
    public static RoomDao createRoomDao() {
        return new RoomDaoJDBC(Database.getConnection());
    }

    /**
     * Creates a new instance of RoomTypeDao.
     *
     * @return a new RoomTypeDao instance
     */
    public static RoomTypeDao createRoomTypeDao() {
        return new RoomTypeDaoJDBC(Database.getConnection());
    }

    /**
     * Creates a new instance of BookingDao.
     *
     * @return a new BookingDao instance
     */
    public static BookingDao createBookingDao() {
        return new BookingDaoJDBC(Database.getConnection());
    }

}