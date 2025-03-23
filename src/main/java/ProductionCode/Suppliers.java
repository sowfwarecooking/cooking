package ProductionCode;

import java.io.*;
import java.util.*;


public class Suppliers {

    public Map<String, LinkedHashMap<Float, Float>> suppliers1 = new HashMap<>();
    public Map<String, LinkedHashMap<Float, Float>> suppliers2 = new HashMap<>();
    public Map<String, LinkedHashMap<Float, Float>> suppliers3 = new HashMap<>();
    List<String> filePaths = Arrays.asList("data/supplier1.txt", "data/supplier2.txt", "data/supplier3.txt");

    public void loadFromFiles() throws IOException {
        readFromFiles(filePaths);
    }
    public void readFromFiles(List<String> filePaths)

            throws IOException {
        if (filePaths.size() != 3) {
            throw new IllegalArgumentException("There should be exactly 3 file paths.");
        }

        suppliers1 = readFromFile(filePaths.get(0));
        suppliers2 = readFromFile(filePaths.get(1));
        suppliers3 = readFromFile(filePaths.get(2));
    }

    private Map<String, LinkedHashMap<Float, Float>> readFromFile(String filePath) throws IOException {
        Map<String, LinkedHashMap<Float, Float>> dataMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    float quantity = Float.parseFloat(parts[1].trim()); // Parse as Float
                    float price = Float.parseFloat(parts[2].trim());   // Parse as Float

                    dataMap.putIfAbsent(name, new LinkedHashMap<>()); // Use LinkedHashMap
                    dataMap.get(name).put(quantity, price);
                }
            }
        }

        return dataMap;
    }


    public String getBestPrice(String ingredient, float quantity) {
        Float bestPrice = Float.MAX_VALUE;
        LinkedHashMap<Float, Float> supply1 = suppliers1.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply2 = suppliers2.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply3 = suppliers3.getOrDefault(ingredient, new LinkedHashMap<>());

        String path = null;

        for (Map.Entry<Float, Float> entry : supply1.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && Float.compare(price, bestPrice) < 0) {
                bestPrice = price;
                path = "data/supplier1.txt";
            }
        }

        for (Map.Entry<Float, Float> entry : supply2.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && Float.compare(price, bestPrice) < 0) {
                bestPrice = price;
                path = "data/supplier2.txt";
            }
        }

        for (Map.Entry<Float, Float> entry : supply3.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && Float.compare(price, bestPrice) < 0) {
                bestPrice = price;
                path = "data/supplier3.txt";
            }
        }

        return path;  // Returns the supplier with the best price
    }

    public void editTheStock(String ingredient, float quant) {

    }
    public Float getBestPrice(String ingredient) {
        Float bestPrice = Float.MAX_VALUE;
        LinkedHashMap<Float, Float> supply1 = suppliers1.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply2 =  suppliers2.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply3 = suppliers3.getOrDefault(ingredient, new LinkedHashMap<>());
        for (Map.Entry<Float, Float> entry : supply1.entrySet()) {
            Float value = entry.getValue();
                bestPrice =  Math.min(bestPrice, value); // Use Math.min to find the minimum value;
                System.out.println(bestPrice);}

        for (Map.Entry<Float, Float> entry : supply2.entrySet()) {
            Float value = entry.getValue();

            bestPrice =  Math.min(bestPrice, value); // Use Math.min to find the minimum value;
                System.out.println(bestPrice);}


        for (Map.Entry<Float, Float> entry : supply3.entrySet()) {

            Float value = entry.getValue();

             bestPrice =  Math.min(bestPrice, value); // Use Math.min to find the minimum value;
                System.out.println(bestPrice);}


        return bestPrice;


    }

    public void restock(String ingredient, float quantity) {
        String table = getBestPrice(ingredient, quantity);
        if  (table == null){
            System.out.println("No supplier found with sufficient stock or ingredient not found");
        }
        else {

            supplyVewing add = new supplyVewing();
            add.addIngredient(ingredient, (int) quantity);
        }


    }
    public void editStock(String ingredient, int quantity) {
        String target = getBestPrice(ingredient, quantity);
        List<String> lines = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(ingredient)) {
                    try {
                        float oldValue = Float.parseFloat(parts[1]);
                        float newValue = Math.max(0, oldValue - quantity); // Prevent negative stock
                        parts[1] = String.valueOf(newValue);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing stock value: " + parts[1]);
                    }
                }
                lines.add(String.join(",", parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void restock(String ingredient, int quantity) {
        String table = getBestPrice(ingredient, quantity);
        if  (table == null){
            System.out.println("No supplier found with sufficient stock or ingredient not found");
        }
        else {
            supplyVewing add = new supplyVewing();
            add.updateIngredientQuantity(ingredient, quantity);
            editStock(ingredient, quantity);

        }
    }

    public static void main(String[] args) throws IOException {
        Suppliers supplierObj = new Suppliers();
        supplierObj.loadFromFiles();
        LinkedHashMap<Float, Float> suppliers = supplierObj.suppliers1.getOrDefault("pepper", new LinkedHashMap<>());
        supplierObj.editStock("pepper",5);


    }

}