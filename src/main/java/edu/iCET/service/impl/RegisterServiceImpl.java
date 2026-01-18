package edu.iCET.service.impl;

import edu.iCET.database.PasswordHasher;
import edu.iCET.repository.impl.RegisterRepositoryImpl;

public class RegisterServiceImpl {

    PasswordHasher passwordHasher = new PasswordHasher();

    RegisterRepositoryImpl registerRepository = new RegisterRepositoryImpl();

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
        return registerRepository.emailExists(email);
    }

    public boolean registerUser(String email, String password) {
        String hashedPassword = passwordHasher.hashPassword(password);
        return registerRepository.saveUser(email, hashedPassword);
    }
}
