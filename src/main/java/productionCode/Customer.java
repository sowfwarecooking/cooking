package productionCode;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer {
    private String username;
    private boolean loggedIn;
    private String dietaryPreferences;
    private String allergies;
    private List<String> orderHistory;
    private menuItems items;
    private float charge = 0.0f;
    private String order ;
    private Order currentOrder;
    private KitchenManager myOrderManager;

    // Constructor that sets all fields
    public Customer(String username, String dietaryPreferences, String allergies) {
        this.username = username;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.loggedIn = true; // Simulate userLogin
        this.orderHistory = new ArrayList<>();
        this.items = new menuItems();  // Initialize to avoid null
        this.charge = 0.0f;
        this.currentOrder = new Order();
    }

    public Customer(String username, String dietaryPreferences, String allergies, KitchenManager m) {
        this.username = username;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
        this.loggedIn = true; // Simulate userLogin
        this.orderHistory = new ArrayList<>();
        this.items = new menuItems();  // Initialize to avoid null
        this.charge = 0.0f;
        this.currentOrder = new Order();
        this.myOrderManager = m;
    }



    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getDietaryPreferences() {

        return dietaryPreferences;
    }

    public void setDietaryPreferences(
            String dietaryPreferences) {
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
    public void placeOrder(String order) throws IOException {
        this.charge += currentOrder.getCost(order);
        this.orderHistory.add(order);
        currentOrder.updateQuantities(currentOrder.getIngredientsFromFile(order));
        currentOrder.submitOrderWithDietaryPreferences();
        addOrderToHistory(order, this.username);
        if(myOrderManager!=null){
            myOrderManager.setOrderMessage(order);
        }

    }
    public void addOrderToHistory(String order, String username) {
        File file = new File("data/order.txt");
        List<String> lines = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    String[] parts = line.split(",", 2); // Split into username and order history
                    String currentOrders = parts.length > 1 ? parts[1] : "";

                    // Create a set to prevent duplicates
                    Set<String> orderSet = new LinkedHashSet<>(Arrays.asList(currentOrders.split(",")));
                    orderSet.addAll(Arrays.asList(order.split(","))); // Add new order(s)

                    String updatedLine = username + "," + String.join(",", orderSet);
                    lines.add(updatedLine);
                    userFound = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the order file", e);
        }

        // If the user has no previous orders, add a new line
        if (!userFound) {
            lines.add(username + "," + order);
        }

        // Write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the order file", e);
        }


        // Write the updated lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing the order file", e);
        }
    }


    public void selectOrder(String selected) {
        this.order= selected;
    }



    public String getSected() {
        return this.order;
    }
    public String invoice() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return  date + "Invoice: " + this.charge;
    }

    public float getCharge() {
        return charge;
    }

}