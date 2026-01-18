package edu.iCET.database;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    private static final Map<String, String> user = new HashMap<>();

    public static boolean userExists(String email){
        return user.containsKey(email);
    }

    public static void saveUser(String email, String passwordHash){
        user.put(email, passwordHash);
    }

    public static String getPasswordHash(String email){
        return user.get(email);
    }
}
