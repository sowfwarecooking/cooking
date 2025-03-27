package ProductionCode;

import java.util.ArrayList;

public class Ingredients {
    public ArrayList<String> availableIngredientsA = new ArrayList<>();
    public ArrayList<String> availableIngredientsB = new ArrayList<>();
    public ArrayList<String> selectedIngredients = new ArrayList<>();

    public boolean isAvailableIngredient(String s){
        for(String i:this.availableIngredientsA){
            if(i.equalsIgnoreCase(s)){
                return true;
            }
        }
        for(String i:this.availableIngredientsB){
            if(i.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public void addAvailableIngredients(String ...s){
            for(String temp:s){
                this.availableIngredientsA.add(temp);
            }

    }

    public void addAvailableIngredients(int i,String ...s) {
        if (i == 2) {
            for (String temp : s) {
                this.availableIngredientsB.add(temp);
            }

        } else {
            for (String temp : s) {
                this.availableIngredientsA.add(temp);
            }

        }
    }
//fix "remove" problem -> use iterator!!!!!!!!!!!!!!!!
    public String addDesiredIngredients(String ...s){

        String status = "";
        ArrayList<String> selectedIngredientsTemp = new ArrayList<>();
        boolean inIngredientGrpA = false;
        boolean inIngredientGrpB = false;
        boolean grpABias = false;
        boolean grpBBias = false;
        for(String temp:s){
            if(this.isAvailableIngredient(temp))
          this.selectedIngredients.add(temp);
            else{
                status += temp+" IS UNAVAILABLE\n";

            }
        }
//If the selection wasn't available -> return the status and don't worry about the rest
        if(selectedIngredients.isEmpty()){
            return status;
        }

        if(this.availableIngredientsA.contains(this.selectedIngredients.getFirst())){grpABias = true;}
        else {grpBBias=true;}
       for(String temp :this.selectedIngredients){
           if(grpABias){
               if(this.availableIngredientsA.contains(temp)){
             inIngredientGrpA = true;
             selectedIngredientsTemp.add(temp);
               }
                else {
                    inIngredientGrpB = true;
                }
           }
           else if (grpBBias) {
               if(this.availableIngredientsA.contains(temp)){
                   inIngredientGrpA = true;
               }
               else {
                   inIngredientGrpB = true;
                   selectedIngredientsTemp.add(temp);
               }
           }
       }
        if(inIngredientGrpA && inIngredientGrpB){
            status = "Selected Ingredients were incompatible, incompatible ingredients were removed";
            this.selectedIngredients=selectedIngredientsTemp;
        }

        return  status;
    }







}
