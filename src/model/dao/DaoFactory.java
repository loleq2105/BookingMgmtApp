package model.dao;

import db.Database;
import model.dao.impl.GuestDaoJDBC;

public class DaoFactory {

    public static GuestDao createGuestDao() {
        return new GuestDaoJDBC(Database.getConnection());
    }
}