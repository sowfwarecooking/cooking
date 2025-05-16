package ProductionCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class userLogin {

    private String username;
    private String password;
    public boolean accessGranted;
    private String FileName;
    public static final Map<String, String> users = new HashMap<>();

    public userLogin() {
        loadUsers();
    }

    /**
     * Loads user credentials from a file and stores them in a HashMap for faster access.
     * The file should be formatted as "username,password,allergy,diet" per line.
     * Only the first two fields are used for login validation.
     *
     * @throws RuntimeException if an error occurs while reading the file
     */
    public void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            users.clear(); // clear old users before loading
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) { // make sure there's at least username and password
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    users.put(username, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while loading users from file: " + e.getMessage());
            throw new RuntimeException("Failed to load users from file", e);
        }
    }

    private static String getFileName() {
        return "data/users.txt";
    }

    public String attemptLogin(String username, String password) {
        this.username = username;
        this.password = password;

        if (!users.containsKey(username)) {
            accessGranted = false;
            return "User not registered";
        }

        if (users.get(username).equals(password)) {
            accessGranted = true;
            return "Access granted";
        } else {
            accessGranted = false;
            return "Incorrect password";
        }
    }

    // Getters and Setters
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void setFileName(String fileName) { this.FileName = fileName; }
}
