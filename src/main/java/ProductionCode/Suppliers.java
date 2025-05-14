package ProductionCode;

import java.io.*;
import java.util.*;

public class Suppliers {

    public Map<String, LinkedHashMap<Float, Float>> suppliers1 = new HashMap<>();
    public Map<String, LinkedHashMap<Float, Float>> suppliers2 = new HashMap<>();
    public Map<String, LinkedHashMap<Float, Float>> suppliers3 = new HashMap<>();
    List<String> filePaths = Arrays.asList("data/supplier1.txt", "data/supplier2.txt", "data/supplier3.txt");
    public Suppliers() throws IOException {
        loadFromFiles();
    }
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



    public Float getBestPrice(String ingredient) {
        Float bestPrice = Float.MAX_VALUE;
        LinkedHashMap<Float, Float> supply1 = suppliers1.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply2 =  suppliers2.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply3 = suppliers3.getOrDefault(ingredient, new LinkedHashMap<>());
        for (Map.Entry<Float, Float> entry : supply1.entrySet()) {
            Float key = entry.getKey();
            Float value = entry.getValue();

            bestPrice =  value;
            System.out.println(bestPrice);
        }
        for (Map.Entry<Float, Float> entry : supply2.entrySet()) {
            Float key = entry.getKey();
            Float value = entry.getValue();
            bestPrice = Math.min(bestPrice, value);
            System.out.println(bestPrice);

        }
        for (Map.Entry<Float, Float> entry : supply3.entrySet()) {
            Float key = entry.getKey();
            Float value = entry.getValue();
            bestPrice = Math.min(bestPrice, value);
            System.out.println(bestPrice);

        }
        return bestPrice;


    }
    public float getBest(String ingredient, float quantity) {
        Float bestPrice = Float.MAX_VALUE;

        LinkedHashMap<Float, Float> supply1 = suppliers1.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply2 = suppliers2.getOrDefault(ingredient, new LinkedHashMap<>());
        LinkedHashMap<Float, Float> supply3 = suppliers3.getOrDefault(ingredient, new LinkedHashMap<>());

        boolean found = false;

        for (Map.Entry<Float, Float> entry : supply1.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && price < bestPrice) {
                bestPrice = price;
                found = true;
            }
        }

        for (Map.Entry<Float, Float> entry : supply2.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && price < bestPrice) {
                bestPrice = price;
                found = true;
            }
        }

        for (Map.Entry<Float, Float> entry : supply3.entrySet()) {
            Float stock = entry.getKey();
            Float price = entry.getValue();
            if (quantity <= stock && price < bestPrice) {
                bestPrice = price;
                found = true;
            }
        }

        return found ? bestPrice : 0.0f; // return 0.0f if no suitable supplier found
    }




}