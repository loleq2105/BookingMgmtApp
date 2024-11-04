package model.dao.impl;

import model.dao.RoomDao;

import java.sql.Connection;

public class RoomDaoJDBC implements RoomDao {

    private Connection connection;

    public RoomDaoJDBC(Connection connection) {
        this.connection = connection;
    }
}
