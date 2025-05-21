package productionCode;

import java.util.ArrayList;
import java.util.Random;

/***
 * The {@code Ingredients} class manages ingredient selection for a chef,
 * including available ingredients, selected ingredients, and handling
 * substitutions and compatibility rules between two ingredient groups (A and B).
 *
 * <p>It ensures that incompatible ingredient groups are not mixed, handles unavailable
 * ingredients by offering suggestions, and tracks changes to selected ingredients
 * for reporting or logging purposes.
 */

public class Ingredients {
    public ArrayList<String> availableIngredientsA = new ArrayList<>();
    public ArrayList<String> availableIngredientsB = new ArrayList<>();
    public ArrayList<String> selectedIngredients = new ArrayList<>();
    /** A snapshot of previously selected ingredients before the most recent change. */
    public ArrayList<String> selectedIngredientsOld = new ArrayList<>();
    Random random = new Random();

    /***
     * Checks if the specified ingredient is available in either group A or B(all groups).
     *
     * @param s the ingredient name to check
     * @return {@code true} if available, {@code false} otherwise
     */
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
    /***
     * Adds ingredients to a specified group(Grp A).
     *
     * @param s one or more ingredient names to add
     */
    public void addAvailableIngredients(String ...s){
            for(String temp:s){
                this.availableIngredientsA.add(temp);
            }

    }
    /***
     * Adds ingredients to a specified group.
     *
     * @param i if 2, adds to group B; otherwise, adds to group A
     * @param s one or more ingredient names to add
     */
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
    /***
     * Attempts to add a list of desired ingredients for a chef.
     * Handles unavailable items, suggests alternatives, and enforces group compatibility.
     *
     * @param c the {@link Chef} object to notify of ingredient changes
     * @param s list of desired ingredient names
     * @return status message indicating any unavailable ingredients and substitutions
     */
    public String addDesiredIngredients(Chef c, String ...s){
        this.selectedIngredientsOld = new ArrayList<>(this.selectedIngredients) ;

        addAvailableIngredients(2,"def B");
        addAvailableIngredients("def A");
        String status = "";
        ArrayList<String> selectedIngredientsTemp = new ArrayList<>();
        boolean inIngredientGrpA = false;
        boolean inIngredientGrpB = false;
        boolean grpABias = false;
        boolean grpBBias = false;
        int cnt = 0;
        String unavailableMsg="For the Unavailable Ingredients You Can Try These Instead:\n";
       for(String temp:s){
            if(this.isAvailableIngredient(temp)){
          this.selectedIngredients.add(temp);}
            else{
                status += temp+" IS UNAVAILABLE\n";
                cnt++;
            }
        }
//If the selection wasn't available -> return the status and don't worry about the rest
        if(selectedIngredients.isEmpty()){
            status+="Try These Instead: \n";
            for(int i = 0; i<cnt; i++){
                String randomElement = availableIngredientsA.get(random.nextInt(availableIngredientsB.size()));
                status+= randomElement+"\n";
            }
            c.setIngredientChangeMessage(selectedIngredientsOld +" to "+selectedIngredients);
            return status;
        }


        if(containsIgnoreCase(availableIngredientsA, this.selectedIngredients.getFirst())){grpABias = true;}
        else {grpBBias=true;}
       for(String temp :this.selectedIngredients){
           if(grpABias){
               if(containsIgnoreCase(this.availableIngredientsA, temp)){
             inIngredientGrpA = true;
             selectedIngredientsTemp.add(temp);
               }
                else {
                    inIngredientGrpB = true;
                }
           }
           else if (grpBBias) {
               if(
                       containsIgnoreCase(this.availableIngredientsA, temp)){
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
        if(grpABias){
            for(int i = 0; i<cnt; i++){
                String randomElement = availableIngredientsA.get(random.nextInt(availableIngredientsA.size()));
                unavailableMsg += randomElement+"\n";
            }
            if(cnt!=0)status+=unavailableMsg;
        }
        if(grpBBias){
            for(int i = 0; i<cnt; i++){
                String randomElement = availableIngredientsB.get(random.nextInt(availableIngredientsB.size()));
                unavailableMsg += randomElement+"\n";
            }
            if(cnt!=0)status+=unavailableMsg;
        }

        c.setIngredientChangeMessage(selectedIngredientsOld +" to "+ selectedIngredients);
        return  status;
    }
    /***
     * Removes a specific ingredient from the list of selected ingredients.
     *
     * @param s the ingredient name to remove
     * @param c the {@link Chef} to notify about the change
     */
    public void removeDesiredIngredients(String s, Chef c){
        this.selectedIngredientsOld = new ArrayList<>(this.selectedIngredients) ;

        for(int i =0;i<selectedIngredients.size();i++){
            String ing = selectedIngredients.get(i);
            if(ing.equalsIgnoreCase(s)){
                selectedIngredients.remove(i);
            }

            c.setIngredientChangeMessage(selectedIngredientsOld+" to "+selectedIngredients);
        }
    }
    /***
     * Substitutes one selected ingredient with another.
     *
     * @param toBeSubbed   the ingredient to be removed
     * @param subbedWith   the ingredient to be added instead
     * @param c            the {@link Chef} to notify about the change
     */
    public void substituteDesiredIngredients(String toBeSubbed, String subbedWith, Chef c){
        ArrayList<String> temp = new ArrayList<>(new ArrayList<>(this.selectedIngredients));

        System.out.println("\n"+selectedIngredientsOld);
        for(int i =0;i<selectedIngredients.size();i++){
            String ing = selectedIngredients.get(i);
            if(ing.equalsIgnoreCase(toBeSubbed)){
                selectedIngredients.remove(i);
                addDesiredIngredients(c, subbedWith);
            }
        }
        this.selectedIngredientsOld = new ArrayList<>(temp);
        c.setIngredientChangeMessage(selectedIngredientsOld+" to "+selectedIngredients);

    }
public ArrayList<String> getSelectedIngredients(){

        return selectedIngredients;
}

boolean containsIgnoreCase(ArrayList<String> arr, String s){
        for(String temp: arr){
            if (temp.equalsIgnoreCase(s))return true;
        }
        return false;
}
}
