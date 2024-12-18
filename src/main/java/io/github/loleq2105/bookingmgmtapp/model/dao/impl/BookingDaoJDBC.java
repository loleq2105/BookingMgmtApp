package io.github.loleq2105.bookingmgmtapp.model.dao.impl;

import io.github.loleq2105.bookingmgmtapp.db.Database;
import io.github.loleq2105.bookingmgmtapp.db.DbException;
import io.github.loleq2105.bookingmgmtapp.model.dao.BookingDao;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoJDBC implements BookingDao {

    private Connection connection;

    public BookingDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Booking booking) {
        String sql = "INSERT INTO booking (guest_id, room_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, booking.getGuestId());
            statement.setInt(2, booking.getRoomId());
            statement.setObject(3, booking.getStartDate());
            statement.setObject(4, booking.getEndDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    booking.setId(id);
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
        String sql = "DELETE FROM booking WHERE id = ?";
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
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, guest_id, room_id, start_date, end_date FROM booking";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setGuestId(resultSet.getInt("guest_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT id, guest_id, room_id, start_date, end_date FROM booking WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setGuestId(resultSet.getInt("guest_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
                return booking;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Booking> findByGuest(int guestId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, guest_id, room_id, start_date, end_date FROM booking WHERE guest_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, guestId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setGuestId(resultSet.getInt("guest_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Booking> findByRoom(int roomId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, guest_id, room_id, start_date, end_date FROM booking WHERE room_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setId(resultSet.getInt("id"));
                booking.setGuestId(resultSet.getInt("guest_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setEndDate(resultSet.getDate("end_date").toLocalDate());
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }


}
