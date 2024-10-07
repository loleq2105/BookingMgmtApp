import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookingmgmt";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    // Singleton dla połączania z bazą danych
    private static Connection connection;

    public Database() {
        // Initialize the database connection
        if (connection == null) {
            connection = connect();
        }
    }

    // Establishes a connection to the database
    private Connection connect() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT id, name, phone, email FROM guests";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guest.setPhone(resultSet.getString("phone"));
                guest.setEmail(resultSet.getString("email"));
                guests.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }

    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guests (name, phone, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getPhone());
            ps.setString(3, guest.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeGuestById(int id) {
        String sql = "DELETE FROM guests WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Guest getGuestById(int id) {
        String sql = "SELECT id, name, phone, email FROM guests WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("id"));
                guest.setName(resultSet.getString("name"));
                guest.setEmail(resultSet.getString("email"));
                guest.setPhone(resultSet.getString("phone"));
                return guest;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
