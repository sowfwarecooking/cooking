package ProductionCode;

import java.util.ArrayList;

public class IngredientSubManager {
    Order myOrder = new Order();
    public ArrayList<String>restrictedVeganIngredients=new ArrayList<>();
    public ArrayList<String>restrictedLowCarbIngredients=new ArrayList<>();
    public ArrayList<String>restrictedVegetarianIngredients=new ArrayList<>();
    public ArrayList<String>restrictedKetoIngredients=new ArrayList<>();
    public ArrayList<String>restrictedGlutenFreeIngredients=new ArrayList<>();


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
        if(this.restrictedVeganIngredients.stream().anyMatch(a->a.equalsIgnoreCase(s))){
            return true;
        }
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

}
