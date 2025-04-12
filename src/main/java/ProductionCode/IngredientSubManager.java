package ProductionCode;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientSubManager {
    Order myOrder = new Order();

    public ArrayList<String>restrictedVeganIngredients=new ArrayList<>();
    public ArrayList<String>restrictedLowCarbIngredients=new ArrayList<>();
    public ArrayList<String>restrictedVegetarianIngredients=new ArrayList<>();
    public ArrayList<String>restrictedKetoIngredients=new ArrayList<>();
    public ArrayList<String>restrictedGlutenFreeIngredients=new ArrayList<>();

    public ArrayList<String>alternativeVeganIngredients=new ArrayList<>();
    public ArrayList<String>alternativeLowCarbIngredients=new ArrayList<>();
    public ArrayList<String>alternativeVegetarianIngredients=new ArrayList<>();
    public ArrayList<String>alternativeKetoIngredients=new ArrayList<>();
    public ArrayList<String>alternativeGlutenFreeIngredients=new ArrayList<>();

    public ArrayList<String> myOrderForSubRev =new ArrayList<>();

    public void addAlternativeVeganIngredients(String...s){
       this.alternativeVeganIngredients.addAll(Arrays.asList(s));
    }
    public void addAlternativeLowCarbIngredients(String...s){
        this.alternativeLowCarbIngredients.addAll(Arrays.asList(s));
    }
    public void addAlternativeVegetarianIngredients(String...s){
        this.alternativeVegetarianIngredients.addAll(Arrays.asList(s));
    }
    public void addAlternativeKetoIngredients(String...s){
        this.alternativeKetoIngredients.addAll(Arrays.asList(s));
    }
    public void addAlternativeGlutenFreeIngredients(String...s){
        this.alternativeGlutenFreeIngredients.addAll(Arrays.asList(s));
    }

    public void submitOrderForSubReview(String... s){
        this.myOrderForSubRev.addAll(Arrays.asList(s));
    }

    public void setOrder(Order o){this.myOrder=o;}

    public String getOrderDietaryPreference(){
        return (this.myOrder.getDietaryPreference());
    }
    public boolean hasADiet(){
        String s = getOrderDietaryPreference();
        if(getOrderDietaryPreference().equalsIgnoreCase("none")){
            return false;

        }else{return true;}

    }
    public boolean isRestrictedIngredient(String s){
        if (containNonVegan(this.restrictedVeganIngredients, s)) return true;
        //continue refactoring those->
        if(this.restrictedLowCarbIngredients.stream().anyMatch(a->a.equalsIgnoreCase(s))){
            return true;
        }
        if(this.restrictedVegetarianIngredients.stream().anyMatch(a->a.equalsIgnoreCase(s))){
            return true;
        }
        if(this.restrictedKetoIngredients.stream().anyMatch(a->a.equalsIgnoreCase(s))){
            return true;
        }
        if(this.restrictedGlutenFreeIngredients.stream().anyMatch(a->a.equalsIgnoreCase(s))){
            return true;
        }
        return false;


    }

    private boolean containNonVegan(ArrayList<String> restrictedVeganIngredients, String s) {
        if (restrictedVeganIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean selectionContainsRestrictions (ArrayList<String> arr){
        if(arr.isEmpty()){
            return false;
        }
        for(String s: arr){
            if (isRestrictedIngredient(s));
            return true;
        }
        return false;
    }
    //---------->Start From here
    public String suggestAlternativeIngredients(Order o){
        String suggestionMessage = "";
        String orderDietaryPreference = o.getDietaryPreference();
        if (myOrderForSubRev.isEmpty())return"No Order To Check";
        
        switch (orderDietaryPreference) {
            case "vegan":
        }
        return "";
    }

}
