package model.dao.impl;

import db.Database;
import db.DbException;
import model.dao.GuestDao;
import model.entities.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoJDBC implements GuestDao {

    private Connection connection;

    public GuestDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Guest guest) {
        String sql = "INSERT INTO guests (name, phone, email) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getPhone());
            statement.setString(3, guest.getEmail());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    guest.setId(id);
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
        String sql = "DELETE FROM guests WHERE id = ?";
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
    public List<Guest> findAll() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT id, name, phone, email FROM guests";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guest.setPhone(resultSet.getString("phone"));
                guest.setEmail(resultSet.getString("email"));
                guests.add(guest);
            }
            return guests;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public Guest findById(int id) {
        String sql = "SELECT id, name, phone, email FROM guests WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guest.setEmail(resultSet.getString("email"));
                guest.setPhone(resultSet.getString("phone"));
                return guest;
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
