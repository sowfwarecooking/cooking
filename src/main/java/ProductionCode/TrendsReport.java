package ProductionCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TrendsReport {
    private String reportDetails;
    private HashMap<String,Integer> orderCounts = new HashMap<>();
    private String filePath;

    public TrendsReport(String reportDetails) {
        this.filePath= "data/order.txt";
        this.reportDetails = reportDetails;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    String getFilePath() {
        return filePath;
    }
    public void loadOrdersFromFile() {
        orderCounts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] order = line.split(",");
                // Start from 1 to skip the username (index 0)
                for (int i = 1; i < order.length; i++) {
                    String meal = order[i].trim(); // Get the meal name and trim any extra spaces
                    // Count each meal in the order
                    orderCounts.put(meal, orderCounts.getOrDefault(meal, 0) + 1);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Order file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading order file", e);
        }
    }

    /**
     * This method analyzes the order trends in the restaurant and shows the top 3 most popular meals.
     * The analysis works by:
     * 1) Accessing the HashMap that contains meal names and their order counts.
     * 2) Converting the map to a stream of entries.
     * 3) Sorting the entries by their value (the number of orders) in descending order.
     * 4) Limiting the results to the top 5 meals based on the highest order count.
     * 5) Printing the top meals along with their order counts.
     *
     * Methods Used:
     * - entrySet(): Converts the map into a set of entries (key-value pairs).
     * - stream(): Converts the set into a stream for further processing.
     * - sorted(): Sorts the entries based on the value in descending order.
     * - limit(5): Limits the results to the top 5 meals.
     * - forEach(): Iterates over the stream and prints each entry.
     *
     * @author mohammed saeed
     * @date [3/6/2025
     * @version 1.0
     */

    public String analyzeOrderTrends() {
        StringBuilder result = new StringBuilder("Order Trends:\n");

        return orderCounts.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort by highest count
                .limit(3) // Limit to top 5
                .map(entry -> entry.getKey() + " ordered " + entry.getValue() + " time" + (entry.getValue() > 1 ? "s" : "") + ".") // Handle singular/plural
                .collect(Collectors.joining("\n")); // Join them with a newline
    }


}