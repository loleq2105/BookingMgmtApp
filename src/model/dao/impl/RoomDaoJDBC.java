package model.dao.impl;

import db.Database;
import db.DbException;
import model.dao.RoomDao;
import model.entities.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoJDBC implements RoomDao {

    private Connection connection;

    public RoomDaoJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Room room) {
        String sql = "INSERT INTO rooms (number, capacity, type_id, price) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, room.getNumber());
            statement.setInt(2, room.getCapacity());
            statement.setInt(3, room.getTypeId());
            statement.setInt(4, room.getPrice());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    room.setId(id);
                }
                Database.closeResultSet(result);
            } else {
                throw new DbException("No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
        }
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, number, capacity, type_id, price FROM rooms";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setTypeId(resultSet.getInt("type_id"));
                room.setPrice(resultSet.getInt("price"));
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public Room findById(int id) {
        String sql = "SELECT id, number, capacity, type_id, price FROM rooms WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setNumber(resultSet.getString("number"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setTypeId(resultSet.getInt("type_id"));
                room.setPrice(resultSet.getInt("price"));
                return room;
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
