package ProductionCode;


import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Order {
   public ArrayList<String> ingredients = new ArrayList<>();

   public enum Dietarypreference {VEGAN, LOW_CARB, VEGETARIAN, KETO, GLUTEN_FREE, NONE}

   ;
   Dietarypreference myDP = Dietarypreference.NONE;

   public String getDietaryPreference() {
      return (String.valueOf(this.myDP)).toLowerCase();
   }

   public void setIngredients(String... s) {
      for (String a : s) {
         this.ingredients.add(a);
      }
   }

   public void setDietaryPreference(String a) {
      String s = a.toLowerCase();
      switch (s) {
         case "vegan":
            this.myDP = Dietarypreference.VEGAN;
            break;
         case "low carb":
            this.myDP = Dietarypreference.LOW_CARB;
            break;
         case "vegetarian":
            this.myDP = Dietarypreference.VEGETARIAN;
            break;
         case "keto":
            this.myDP = Dietarypreference.KETO;
            break;
         case "gluten free":
            this.myDP = Dietarypreference.GLUTEN_FREE;
            break;
         default:
            this.myDP = Dietarypreference.NONE;
      }
   }

   public String submitOrder() {
      String s = "";
      if (!this.ingredients.isEmpty()) {
         s = this.ingredients.get(0);
         for (int i = 1; i < this.ingredients.size(); i++) {
            s += ", " + this.ingredients.get(i);
         }


      } else {
         s = selectIngredientsMessage();
      }
      return s;
   }

   public String submitOrderWithDietaryPreferences() {
      String s = "";
      if (!this.ingredients.isEmpty()) {
         s = this.getDietaryPreference();
         for (int i = 0; i < this.ingredients.size(); i++) {
            s += ", " + this.ingredients.get(i);
         }
         //JOptionPane.showMessageDialog(null,mealCreatedSuccessfullyMessage());
      } else {
         s = selectIngredientsMessage();
      }
      return s;
   }

   public ArrayList<String> getOrderAsArray() {

      return this.ingredients;

   }

   public String mealCreatedSuccessfullyMessage() {
      return "Meal Created Successfully";
   }

   public String selectIngredientsMessage() {
      return "Please select at least one ingredient";
   }

   public ArrayList<String> getIngredientsFromFile(String mealName) throws IOException {

      try (BufferedReader reader = new BufferedReader(new FileReader("data/ingreadint.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
            if (line.contains(mealName)) {
               // Split the line by comma
               String[] parts = line.split(",");
               // Convert from index 1 to the end into a List
               return (ArrayList<String>) Arrays.stream(parts, 1, parts.length)
                       .collect(Collectors.toList());
            }
         }
      }
      return new ArrayList<>();
   }
   public void updateQuantities(ArrayList<String> ingredientsToReduce) throws IOException {
      File file = new File("data/stock.txt");

      // Read file lines into a list
      List<String> lines = Files.readAllLines(file.toPath());

      // Create a map: ingredient normalized -> quantity
      Map<String, Integer> ingredientQuantityMap = new LinkedHashMap<>();

      // Also keep original line (ingredient name) for writing back with same case
      Map<String, String> originalNameMap = new HashMap<>();

      for (String line : lines) {
         String[] parts = line.split(",");
         if (parts.length == 2) {
            String ingredientName = parts[0].trim();
            String ingredientKey = ingredientName.toLowerCase();
            int quantity = Integer.parseInt(parts[1].trim());
            ingredientQuantityMap.put(ingredientKey, quantity);
            originalNameMap.put(ingredientKey, ingredientName);
         }
      }

      // For each ingredient in input list, reduce its quantity by 1 if exists and quantity > 0
      for (String ingredient : ingredientsToReduce) {
         String key = ingredient.toLowerCase().trim();
         if (ingredientQuantityMap.containsKey(key)) {
            int qty = ingredientQuantityMap.get(key);
            if (qty > 0) {
               ingredientQuantityMap.put(key, qty - 1);
            }
         }
      }

      // Now write back all ingredients and updated quantities
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
         for (String key : ingredientQuantityMap.keySet()) {
            String originalName = originalNameMap.get(key);
            int updatedQty = ingredientQuantityMap.get(key);
            writer.write(originalName + "," + updatedQty);
            writer.newLine();
         }
      }
   }


   public float getCost(String mealName) {
      float cost = 0;
      try (BufferedReader reader = new BufferedReader(new FileReader("data/menu.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length == 0) continue;

            String name = parts[0].trim();

            if (!name.equalsIgnoreCase(mealName)) {
               continue; // skip if meal name does not match
            }

            // Find the price in any part after the meal name
            float price = -1;
            for (int i = 1; i < parts.length; i++) {
               try {
                  price = Float.parseFloat(parts[i].trim());
                  break; // found price, stop looking
               } catch (NumberFormatException e) {
                  // not a price, continue searching
               }
            }

            if (price >= 0) {
               cost += price;
            }
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      return cost;
   }


}