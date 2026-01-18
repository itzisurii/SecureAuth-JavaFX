package edu.iCET.repository.impl;

import edu.iCET.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRepositoryImpl {
    public boolean emailExists(String email) {
        String SQL = "SELECT * FROM users WHERE email = ?";
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            boolean exists = rs.next();
            rs.close();
            pst.close();
            return exists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveUser(String email, String hashedPassword) {
        String SQL = "INSERT INTO users(email, password_hash) VALUES (?, ?)";
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, email);
            pst.setString(2, hashedPassword);
            boolean success = pst.executeUpdate() > 0;
            pst.close();
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
