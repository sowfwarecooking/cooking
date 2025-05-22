package productionCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomerProfile {
    private String customerName;
    private List<String> dietaryPreferences;
    private List<String> allergies;

    public CustomerProfile(String customerName, List<String> dietaryPreferences, List<String> allergies) {
        this.customerName = customerName;
        this.dietaryPreferences = dietaryPreferences;
        this.allergies = allergies;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getDietaryPreferences() {
        return dietaryPreferences;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void updatePreferences(List<String> newPreferences) {
        this.dietaryPreferences = newPreferences;
    }

    public void updateAllergies(List<String> newAllergies) {
        this.allergies = newAllergies;
    }

    public void addCustomerProfile(String name, List<String> preferences, List<String> allergies) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/customer_profiles.txt", true))) {
            writer.write(name + ",");
            writer.write(String.join("", preferences) + ",");
            writer.write(String.join(",", allergies));
            writer.newLine();
            System.out.println("Customer profile added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }


}
