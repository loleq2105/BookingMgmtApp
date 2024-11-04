package model.dao.impl;

import model.dao.BookingDao;

import java.sql.Connection;

public class BookingDaoJDBC implements BookingDao {

    private Connection connection;

    public BookingDaoJDBC(Connection connection) {
        this.connection = connection;
    }
}
