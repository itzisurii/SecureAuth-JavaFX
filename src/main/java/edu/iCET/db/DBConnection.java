package edu.iCET.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("DB CONNECTED ✅");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static DBConnection instance;

    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/secureauth", "root", "1234");
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
