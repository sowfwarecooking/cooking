package ProductionCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Customer {
    private String username;
    private boolean loggedIn;
    private String dietaryPreferences;
    private String allergies;
    private List<String> orderHistory;  // Ensure this is initialized

    // Constructor that sets all fields
    public Customer(String username, String dietaryPreferences, String allergies) {
        this.username = username;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.loggedIn = true; // Simulate userLogin
        this.orderHistory = new ArrayList<>();  // Initialize to avoid null
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    /**
     * This method searches for a customer's order history based on the provided name.
     * It reads a file containing customer data in the format: name, order1, order2, ..., orderN,
     * and returns the customer's order history as a comma-separated string.
     *
     * @author Mohammed Saeed
     * @date 3/4/2025
     * @version 2.0
     * @param name the name of the targeted customer whose history is to be viewed
     * @return a string representing the customer's order history, or an empty string if no history is found
     */
    public String viewOrderHistory(String name) {
        orderHistory.clear();
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("data/order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] terms = line.split(",");
                if (terms.length > 1 && terms[0].equalsIgnoreCase(name)) {
                    orderHistory = Arrays.asList(Arrays.copyOfRange(terms, 1, terms.length));
                    result.append(String.join(",", orderHistory));
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading order file: " + e.getMessage());
        }
        return result.toString().trim();
    }

    public List<String> getOrderHistory() {
        if (orderHistory == null) {
            orderHistory = new ArrayList<>();  // Ensure it's initialized
        }
        return new ArrayList<>(orderHistory);  // Return a copy to prevent modification
    }

    public boolean canReorder(Meal meal) {
        return true;
    }


}