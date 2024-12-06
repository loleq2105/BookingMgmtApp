package model.dao;

import db.Database;
import model.dao.impl.BookingDaoJDBC;
import model.dao.impl.GuestDaoJDBC;
import model.dao.impl.RoomDaoJDBC;
import model.dao.impl.RoomTypeDaoJDBC;
import model.entities.Booking;

public class DaoFactory {

    public static GuestDao createGuestDao() {
        return new GuestDaoJDBC(Database.getConnection());
    }

    public static RoomDao createRoomDao() { return new RoomDaoJDBC(Database.getConnection()); }

    public static RoomTypeDao createRoomTypeDao() { return new RoomTypeDaoJDBC(Database.getConnection()); }

    public static BookingDao createBookingDao() { return new BookingDaoJDBC(Database.getConnection()); }

}