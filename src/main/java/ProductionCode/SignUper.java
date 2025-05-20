package ProductionCode;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SignUper {
    private final Set<String> takenUsernames = new HashSet<>();
    private  String filePath = "data/users.txt";

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Load taken usernames from the file
    public void loadTakenUsernames() {
        takenUsernames.clear();  // Ensure you're not accumulating duplicates
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    takenUsernames.add(parts[0].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading taken usernames: " + e.getMessage());
        }
    }

    // Sign up logic
    public String signUp(String username, String password) {
        loadTakenUsernames();  // Reload each time

        if (takenUsernames.contains(username)) {
            return "Username already taken";
        }



        if (isWeakPassword(password)){
            return "Weak password";
        }

        // Save new user to file
        addUserToFile(username, password);
        return "Account created successfully";
    }

    public boolean isWeakPassword(String password) {
        if (password == null || password.length() < 8) {
            return true;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;


        }


        return !(hasUpper && hasLower && hasDigit && hasSpecial);
    }


    public void addUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username + "," + password + ",none,none");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    // Check if a user exists in the file
    public boolean isAdded(String username) {
        loadTakenUsernames();  // Reload to get the latest data
        return takenUsernames.contains(username);
    }


}
