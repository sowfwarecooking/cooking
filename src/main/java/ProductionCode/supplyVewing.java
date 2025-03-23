package ProductionCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class supplyVewing  {
    private static final Map<String, Integer> stockLevels = new HashMap<>();
    private static final String FILE_PATH = "data/stock.txt";

    public supplyVewing() {
        loadStockFromFile();
    }

    public synchronized void loadStockFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Stock data file not found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            stockLevels.clear(); // Clear existing data before reloading
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String ingredient = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    stockLevels.put(ingredient, quantity);
                }
            }
            System.out.println("Stock data reloaded from file.");
        } catch (IOException e) {
            System.err.println("Error reading stock file: " + e.getMessage());
        }
    }

    public synchronized void addIngredient(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
        saveStockToFile();
    }

    private synchronized void saveStockToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Integer> entry : stockLevels.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving stock data: " + e.getMessage());
        }
    }

    public static synchronized boolean checkLowStock(String ingredient) {
        return stockLevels.getOrDefault(ingredient, 0) <= 5;
    }

    public static synchronized String getRestockingMessage(String ingredient) {
        int stock = stockLevels.getOrDefault(ingredient, 0);
        if (checkLowStock(ingredient)) {
            return "Restock needed: " + ingredient + " (" + stock + " left)";
        }
        return "Stock sufficient for " + ingredient;
    }

    public synchronized String checkLowStock() {
        StringBuilder lowStockMessage = new StringBuilder();
        for (String ingredient : stockLevels.keySet()) {
            if (checkLowStock(ingredient)) {
                lowStockMessage.append("Restock needed: ").append(ingredient)
                        .append(" (").append(stockLevels.get(ingredient)).append(" left)\n");
            }
        }
        return lowStockMessage.length() > 0 ? lowStockMessage.toString() : "All ingredients have sufficient stock";
    }

    public synchronized void updateIngredientQuantity(String ingredient, int additionalQuantity) {
        if (stockLevels.containsKey(ingredient)) {
            int newQuantity = stockLevels.get(ingredient) + additionalQuantity;
            stockLevels.put(ingredient, newQuantity);
            saveStockToFile();
            System.out.println("Updated " + ingredient + " quantity to " + newQuantity);
        } else {
            System.out.println("Ingredient not found: " + ingredient);
        }
    }

    public void startStockChecker() {



        Thread stockThread = new Thread(() -> {
            while (true) {
                loadStockFromFile();
                try {
                    loadStockFromFile();  // Reload stock from file
                    System.out.println("Stock Check at " + java.time.LocalTime.now() + ":\n" + checkLowStock());
                    Thread.sleep(5000); // Check stock every 5 seconds
                } catch (InterruptedException e) {
                    System.out.println("Stock checking thread interrupted.");
                    break;
                }
            }
        });

        stockThread.setDaemon(true); // Allows JVM to exit if only daemon threads are running
        stockThread.start();
    }

    public synchronized int getStockLevel(String target) {
        return stockLevels.getOrDefault(target, 0);
    }

    public static void main(String[] args) {
        supplyVewing supplyViewing = new supplyVewing();
        supplyViewing.startStockChecker();
       System.out.println(supplyViewing.checkLowStock());
       System.out.println("ssssssssssssssssssssssssss");
        // Simulating running for 1 minute before stopping
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
