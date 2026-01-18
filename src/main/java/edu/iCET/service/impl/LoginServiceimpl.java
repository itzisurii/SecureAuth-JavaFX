package edu.iCET.service.impl;

import edu.iCET.database.PasswordHasher;
import edu.iCET.db.DBConnection;
import edu.iCET.repository.impl.LoginRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceimpl {
    private LoginRepositoryImpl loginRepository = new LoginRepositoryImpl();
    private PasswordHasher passwordHasher = new PasswordHasher();

    public boolean searchUser(String email, String password) {
        String hashedPassword = passwordHasher.hashPassword(password);
        return loginRepository.searchUser(email, hashedPassword);
    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()){
            return false;
        }
        return email.endsWith("@gmail.com");
    }

    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()){
            return false;
        }else {
            boolean lengthOK = password.length() >= 8;
            boolean hasUpper = password.matches(".*[A-Z].*");
            boolean hasLower = password.matches(".*[a-z].*");
            boolean hasSymbol = password.matches(".*[!@#$%^&*()_+=/';{}][.,><:~].");

            return lengthOK && hasUpper && hasLower && hasSymbol;
        }
    }

    public boolean emailExists(String email) {
        return loginRepository.emailExists(email);
    }

    public boolean needRegistration(String email, String password) {
        if (!emailExists(email)) {
            return true;
        }
        return false;
    }
}
