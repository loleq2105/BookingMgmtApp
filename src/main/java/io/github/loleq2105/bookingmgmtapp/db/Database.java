package io.github.loleq2105.bookingmgmtapp.db;

import util.ScriptRunner;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * This class is responsible for managing the connection to the database.
 * It also initializes the database with the init.sql and seed.sql scripts.
 */
public class Database {

    private static Connection conn = null;
    private static boolean wasInitialized = false;

    /**
     * Gets the connection to the database. If the connection is not already established,
     * it initializes the connection using the properties from the db.properties file.
     * It also runs the init.sql and seed.sql scripts if the database was not initialized.
     *
     * @return the connection to the database
     * @throws DbException if a database access error occurs or the scripts cannot be run
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);

                if (!wasInitialized) {
                    ScriptRunner scriptRunner = new ScriptRunner(conn, false, false);
                    try {
                        scriptRunner.runScript(new FileReader("init.sql"));
                        scriptRunner.runScript(new FileReader("seed.sql"));
                        wasInitialized = true;
                    } catch (IOException e) {
                        throw new DbException(e.getMessage());
                    }
                }

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * Closes the connection to the database if it is open.
     *
     * @throws DbException if a database access error occurs
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Loads the database properties from the db.properties file.
     *
     * @return the properties loaded from the file
     * @throws DbException if an I/O error occurs
     */
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Closes the given statement if it is not null.
     *
     * @param st the statement to be closed
     * @throws DbException if a database access error occurs
     */
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Closes the given result set if it is not null.
     *
     * @param rs the result set to be closed
     * @throws DbException if a database access error occurs
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}