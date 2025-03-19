package ProductionCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userDatabase = new HashMap<>();
    private String loginMessage;
    private static final String FILE_PATH = "data/users.txt"; // File storing user credentials

    public UserService() {
        loadUsersFromFile(); // Load users on startup for fast access
    }

    // Load users from file into HashMap
    private void loadUsersFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("User data file not found. Starting with an empty database.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2); // Expecting format: username,password
                if (parts.length == 2) {
                    userDatabase.put(parts[0], new User(parts[0], parts[1]));
                }
            }
            System.out.println("User data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
    }

    // Register user and save to file
    public boolean registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            loginMessage = "User already exists";
            return false; // User already exists, can't register
        }

        // Create new user and add to both HashMap and file
        userDatabase.put(username, new User(username, password));
        saveUserToFile(username, password);
        loginMessage = "User registered successfully";
        return true;
    }

    // Save the new user to the file
    private void saveUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing user to file: " + e.getMessage());
        }
    }

    // Authenticate user
    public boolean login(String username, String password) {
        User user = userDatabase.get(username);
        if (user == null) {
            loginMessage = "User not found";
            return false;
        }
        if (!user.validatePassword(password)) {
            loginMessage = "Invalid credentials";
            return false;
        }
        loginMessage = "Login successful";
        return true;
    }

    public String getLoginMessage() {
        return loginMessage;
    }
}
