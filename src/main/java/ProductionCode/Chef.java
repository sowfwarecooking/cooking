package ProductionCode;


import java.util.ArrayList;

public class Chef {
    String ingredientChangeMessage  = "";
    ArrayList<String> approvedOrder =new ArrayList<>();



    public void viewCustomerProfile(CustomerProfile profile) {
        if (profile != null) {
            System.out.println("Customer: " + profile.getCustomerName());
            System.out.println("Dietary Preferences: " + profile.getDietaryPreferences());
            System.out.println("Allergies: " + profile.getAllergies());
        } else {
            System.out.println("No customer profile found.");
        }
    }

    public String getIngredientChangeMessage() {
        return this. ingredientChangeMessage;
    }


    public void setIngredientChangeMessage(String s) {
        this. ingredientChangeMessage = s;
    }

    public void approveOrder(Ingredients i) {
        approvedOrder = i.getSelectedIngredients();
    }
    public ArrayList<String> getApprovedOrder(){
        return approvedOrder;
    }
}