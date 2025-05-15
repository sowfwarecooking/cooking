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
    private String FileName ;
    public static final Map<String, String> users = new HashMap<>();

    public userLogin() {
        loadUsers();
    }
    /**
     * Loads user credentials from a file and stores them in a HashMap for faster access.
     * The file should be formatted as "username,password" per line.
     * This method improves efficiency by storing data in memory instead of reading from the file every time.
     *
     * @author Mohammed Saeed
     * @version 1.0
     * @since 27/2/2025
     * @throws IOException if an error occurs while reading the file
     */
    public void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(getFileName()))) {
            String line;
            users.clear(); // clear old users before loading
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            System.err.println("Error while loading users from file: " + e.getMessage());
            throw new RuntimeException("Failed to load users from file", e);
        }
    }

    private static String getFileName() {
        return "data/users.txt";
    }

    /**
     * Attempts to log in a user by checking the provided username and password
     * against stored credentials in the HashMap.
     *
     * @author Mohammed Saeed
     * @version 1.0
     * @since 27/2/2025
     * @param username the username entered by the user
     * @param password the password associated with the username
     * @return "Access granted" if the credentials are correct, otherwise "Access denied"
     */
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






    public void setFileName(String fileName) {
        this.FileName = fileName;
    }
}