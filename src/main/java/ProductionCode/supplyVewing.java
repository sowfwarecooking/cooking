package ProductionCode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class supplyVewing {
    private static Map<String, Integer> stockLevels = new HashMap<>();
    private static final String FILE_PATH = "data/stock.txt";
    public supplyVewing() {
        loadStockFromFile();
    }

    public void loadStockFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Stock data file not found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String ingredient = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    stockLevels.put(ingredient, quantity);
                }
            }
            System.out.println("Stock data loaded.");
        } catch (IOException e) {
            System.err.println("Error reading stock file: " + e.getMessage());
        }
    }
    public void addIngredient(String ingredient, int quantity) {
        stockLevels.put(ingredient, quantity);
        saveStockToFile();
    }

    /**
     * Saves the current stock levels to a file.
     * <p>
     * This method iterates through all the ingredients in the `stockLevels` map, writes each ingredient and its corresponding stock level
     * to a file at the specified `FILE_PATH`. The data is saved in a CSV format with the ingredient name followed by its stock level.
     * The method handles any IO exceptions that may occur during the file writing process.
     * </p>
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     *
     * @author mohammed saeed
     * @date 2024-10-10
     * @version 1.0
     */
    private void saveStockToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String ingredient : stockLevels.keySet()) {
                writer.write(ingredient + "," + stockLevels.get(ingredient));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving stock data: " + e.getMessage());
        }
    }

    /***
     * checkLowStock
     * <p>
     *     this will compare the stock level of the ingredient with 5
     *     if the stock level less than or equal to 5
     *     will return true
     * </p>
     *
     * @param ingredient the target ingredient
     * @return if the stock level of the ingredient is less than or equal to 5 can be change
     * @author mohammed saeed
     * @date 2024-10-10
     * @version 1.0
     */
    public static boolean checkLowStock(String ingredient) {
        return stockLevels.getOrDefault(ingredient, 0) <= 5 ;
    }

    /**
     * Checks if the stock level of the specified ingredient is low.
     *
     * <p>
     * This method compares the stock level of the ingredient to 5.
     * If the stock level is less than or equal to 5, it returns {@code true}; otherwise, it returns {@code false}.
     * </p>
     *
     * @param ingredient The name of the ingredient to check.
     * @return {@code true} if the stock level of the ingredient is low (≤ 5), {@code false} otherwise.
     * @author Mohammed Saeed
     * @date 2024-10-10
     * @version 1.0
     */
    public static String getRestockingMessage(String ingredient) {
        int stock = stockLevels.getOrDefault(ingredient, 0);

        if (checkLowStock(ingredient)) {
            return "Restock needed: " + ingredient + " (" + stock + " left)";
        }
        return "Stock sufficient for " + ingredient;
    }

    /***
     * checkLowStock()
     * <p><
     * this method will check the stock level of
     * all the ingredients and return the message if the stock level is less than 5
     *
     * /p>
     *
     * @author mohammed saeed
     * @date 2024-10-10
     * @Version: 1.0
     *
     * @return string
     */

    public String checkLowStock(){
        StringBuilder lowStockMessage = new StringBuilder();
        for (String ingredient : stockLevels.keySet()) {
            if (checkLowStock(ingredient)) {
                lowStockMessage.append("Restock needed: ").append(ingredient)
                        .append(" (").append(stockLevels.get(ingredient)).append(" left)\n");
            }
        }

        if (lowStockMessage.length() > 0) {
            return lowStockMessage.toString();
        } else {
            return "All ingredients have sufficient stock";
        }}

    /**
     * updateIngredientQuantity(String ingredient, int additionalQuantity)
     * <p>
     *
     *     this will update the stock level of the wanted ingredient by the additional quantity
     *
     * </p>
     *
     * @param ingredient the target ingredient
     * @param additionalQuantity the quantity to add
     *  @author mohammed saeed
     *  @date 2024-10-10
     *  @Version: 1.0
     */
    public void updateIngredientQuantity(String ingredient, int additionalQuantity) {
        if (stockLevels.containsKey(ingredient)) {
            int newQuantity = stockLevels.get(ingredient) + additionalQuantity;
            stockLevels.put(ingredient, newQuantity);
            saveStockToFile();
            System.out.println("Updated " + ingredient + " quantity to " + newQuantity);
        } else {
            System.out.println("Ingredient not found: " + ingredient);
        }
    }




    public int getStockLevel(String target) {
        return stockLevels.getOrDefault(target, 0);  // Return 0 if ingredient does not exist
    }



}