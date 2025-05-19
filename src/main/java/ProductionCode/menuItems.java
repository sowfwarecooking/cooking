package ProductionCode;

import java.io.BufferedReader;
import java.io.FileReader;

public class menuItems {
    private String filePath;

    public menuItems() {

        this.filePath = "data/menu.txt";
    }

    public String loadMenuItems(String dieatryPreferences, String allergies) {
        StringBuilder cloocter = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    String diet = parts[1].trim();
                    String allergy = parts[2].trim();
                    String price = parts[3].trim();

                    if (allergy.equalsIgnoreCase(allergies)) {
                        continue;
                    }

                    if (diet.equalsIgnoreCase(dieatryPreferences)) {
                        if (cloocter.length() > 0) {
                            cloocter.append("\n");
                        }
                        cloocter.append(name).append(" - ").append(allergy).append(" - $").append(price);
                    }
                }
            }
            return cloocter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloocter.toString();
    }


}
