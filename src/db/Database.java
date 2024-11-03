package db;

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

public class Database {

    private static Connection conn = null;

    private static boolean wasInitialized = false;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);

                if(!wasInitialized){
                    ScriptRunner scriptRunner = new ScriptRunner(conn, false, false);
                    try{
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

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if(st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}