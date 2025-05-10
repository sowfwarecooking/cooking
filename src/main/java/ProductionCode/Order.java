package ProductionCode;

import javax.swing.*;
import java.util.ArrayList;

public class Order {
   public ArrayList<String> ingredients = new ArrayList<>();

   public enum Dietarypreference {VEGAN, LOW_CARB, VEGETARIAN, KETO, GLUTEN_FREE, NONE};
   Dietarypreference myDP = Dietarypreference.NONE;

   public String getDietaryPreference() {
      return (String.valueOf(this.myDP)).toLowerCase();
   }

   public void setIngredients(String ...s){
      for(String a:s){
         this.ingredients.add(a);
      }
   }
   public void setDietaryPreference(String a) {
      String s = a.toLowerCase();
      switch (s) {
         case "vegan": this.myDP = Dietarypreference.VEGAN;break;
         case "low carb": this.myDP = Dietarypreference.LOW_CARB;break;
         case "vegetarian": this.myDP = Dietarypreference.VEGETARIAN;break;
         case "keto": this.myDP = Dietarypreference.KETO;break;
         case "gluten free": this.myDP = Dietarypreference.GLUTEN_FREE;break;
         default: this.myDP = Dietarypreference.NONE;
      }
   }

   public String submitOrder() {
      String s = "";
      if(!this.ingredients.isEmpty()){
         s=this.ingredients.get(0);
         for(int i=1; i<this.ingredients.size();i++){
            s+=", "+this.ingredients.get(i);
         }
         //JOptionPane.showMessageDialog(null,mealCreatedSuccessfullyMessage());

      }
      else{s=selectIngredientsMessage();}
      return s;
   }

   public String submitOrderWithDietaryPreferences() {
     String s = "";
      if(!this.ingredients.isEmpty()){
          s = this.getDietaryPreference();
         for(int i=0; i<this.ingredients.size();i++){
            s+=", "+this.ingredients.get(i);
         }
         //JOptionPane.showMessageDialog(null,mealCreatedSuccessfullyMessage());
      }
      else{s=selectIngredientsMessage();}
      return s;
   }
   public ArrayList<String> getOrderAsArray(){

return this.ingredients;

   }

   public String mealCreatedSuccessfullyMessage(){
      return "Meal Created Successfully";
   }
   public String selectIngredientsMessage(){
      return "Please select at least one ingredient";
   }
}
