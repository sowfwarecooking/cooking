package ProductionCode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class finance {
    private static float budget;
    Suppliers suppliers;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Fixed format
    HashMap<String, List<String>> history = new HashMap<>(); // Using List<String> to store multiple transactions for the same date

    public finance(int budget) {
        this.budget = budget;
    }

    public finance() throws IOException {
        this.budget = 1000f;
        this.suppliers = new Suppliers();
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void buy(String ingredient, int quantity) {
        // Assuming this method gets the price for the ingredient
        float price = suppliers.getBest(ingredient, quantity);

        if (price != 0) {
            this.budget -= price * quantity;

            // Use current date for each transaction
            LocalDate currentDate = LocalDate.now(); // Get the current date each time
            String formattedDate = currentDate.format(formatter);

            // Create the transaction description
            String transactionDetails = ingredient + " - Quantity: " + quantity + " - Price: $" + price * quantity;

            // If the date is already in history, add the transaction to the list
            history.computeIfAbsent(formattedDate, k -> new ArrayList<>()).add(transactionDetails);
        }
    }

    public static float getBalance() {
        return budget;
    }

    public String printHistory() {
        StringBuilder build = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : history.entrySet()) {
            build.append(entry.getKey()).append(":\n");
            for (String transaction : entry.getValue()) {
                build.append("  ").append(transaction).append("\n");
            }
        }
        return build.toString();
    }


    public static void main(String[] args) throws IOException, InterruptedException {

    }
}
