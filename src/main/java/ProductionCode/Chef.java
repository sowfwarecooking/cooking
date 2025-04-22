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

    public String approveOrder(Ingredients i) {
        approvedOrder = i.getSelectedIngredients();
        return"\nOrder Approved\n";
    }
    public ArrayList<String> getApprovedOrder(){
        return approvedOrder;
    }

    public String removeIngredient(Ingredients i, String s){
        i.removeDesiredIngredients(s, this);
        this.approveOrder(i);
        return "\nChef Removed Ingredient Successfully\n";
    }
    public String addIngredient(Ingredients i, String... s){
        i.addDesiredIngredients(this, s);
        this.approveOrder(i);
        return "\nChef Added Ingredient Successfully\n";
    }
    public String subIngredient(Ingredients i, String toBeSubbed, String subbedWith){
        i.substituteDesiredIngredients(toBeSubbed, subbedWith, this);
        this.approveOrder(i);
        return "\nChef Substituted Ingredient Successfully\n";
    }

}