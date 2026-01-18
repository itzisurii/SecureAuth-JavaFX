package edu.iCET.repository.impl;

import edu.iCET.database.PasswordHasher;
import edu.iCET.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.iCET.database.PasswordHasher.hashPassword;

public class LoginRepositoryImpl {
    public boolean searchUser(String email, String hashPassword) {

        try {

            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM users WHERE email = ? AND password_hash = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,hashPassword);

            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean emailExists(String email) {
        String SQL = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(SQL)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
