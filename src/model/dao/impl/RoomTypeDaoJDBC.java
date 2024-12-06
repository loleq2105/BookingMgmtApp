package model.dao.impl;

import db.Database;
import db.DbException;
import model.dao.RoomTypeDao;
import model.entities.Room;
import model.entities.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDaoJDBC implements RoomTypeDao {

    private Connection connection;

    public RoomTypeDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<RoomType> findAll() {
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT id, name FROM room_types";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RoomType roomType = new RoomType();
                roomType.setId(resultSet.getInt("id"));
                roomType.setName(resultSet.getString("name"));
                roomTypes.add(roomType);
            }
            return roomTypes;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }

    @Override
    public RoomType findById(int id) {
        String sql = "SELECT id, name FROM room_types WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RoomType roomType = new RoomType();
                roomType.setId(resultSet.getInt("id"));
                roomType.setName(resultSet.getString("name"));
                return roomType;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }



}
