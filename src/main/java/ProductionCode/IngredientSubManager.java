package ProductionCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IngredientSubManager {
    Order myOrder = new Order();
    Random random = new Random();
    public ArrayList<String>restrictedVeganIngredients=new ArrayList<>();
    public ArrayList<String>restrictedLowCarbIngredients=new ArrayList<>();
    public ArrayList<String>restrictedVegetarianIngredients=new ArrayList<>();
    public ArrayList<String>restrictedKetoIngredients=new ArrayList<>();
    public ArrayList<String>restrictedGlutenFreeIngredients=new ArrayList<>();

    public void setRestrictedVeganIngredients(String...s){
        this.restrictedVeganIngredients.addAll(Arrays.asList(s));
    }

    public void setRestrictedLowCarbIngredients(String...s){
        this.restrictedLowCarbIngredients.addAll(Arrays.asList(s));
    }

    public void setRestrictedVegetarianIngredients(String...s){
        this.restrictedVegetarianIngredients.addAll(Arrays.asList(s));
    }

    public void setRestrictedKetoIngredients(String...s){
        this.restrictedKetoIngredients.addAll(Arrays.asList(s));
    }

    public void setRestrictedGlutenFreeIngredients(String...s){
        this.restrictedGlutenFreeIngredients.addAll(Arrays.asList(s));
    }

    public ArrayList<String>alternativeVeganIngredients=new ArrayList<>();
    public ArrayList<String>alternativeLowCarbIngredients=new ArrayList<>();
    public ArrayList<String>alternativeVegetarianIngredients=new ArrayList<>();
    public ArrayList<String>alternativeKetoIngredients=new ArrayList<>();
    public ArrayList<String>alternativeGlutenFreeIngredients=new ArrayList<>();

    public ArrayList<String> myOrderForSubRev =new ArrayList<>();

    public void setAlternativeVeganIngredients(String...s){
       this.alternativeVeganIngredients.addAll(Arrays.asList(s));
    }
    public void setAlternativeLowCarbIngredients(String...s){
        this.alternativeLowCarbIngredients.addAll(Arrays.asList(s));
    }
    public void setAlternativeVegetarianIngredients(String...s){
        this.alternativeVegetarianIngredients.addAll(Arrays.asList(s));
    }
    public void setAlternativeKetoIngredients(String...s){
        this.alternativeKetoIngredients.addAll(Arrays.asList(s));
    }
    public void setAlternativeGlutenFreeIngredients(String...s){
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
        if (containsNonVegan(s)) return true;
        if (containsNonLowCarb(s)) return true;
        if (containsNonVegetarian(s)) return true;
        if (containsNonKeto(s)) return true;
        if (containsGluten(s)) return true;
        return false;


    }

    public boolean containsGluten(String s) {
        if (this.restrictedGlutenFreeIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean containsNonKeto(String s) {
        if (this.restrictedKetoIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean containsNonVegetarian(String s) {
        if (this.restrictedVegetarianIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean containsNonLowCarb(String s) {
        if (this.restrictedLowCarbIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean containsNonVegan(String s) {
        if (this.restrictedVeganIngredients.stream().anyMatch(a -> a.equalsIgnoreCase(s))) {
            return true;
        }
        return false;
    }

    public boolean arrContainsGluten(ArrayList<String> myArr) {

      for(String s:myArr){
          if(containsGluten(s)){return true;}
      }
      return false;
    }

    public boolean arrContainsNonVegetarian(ArrayList<String> myArr) {
        for(String s:myArr){
            if(containsNonVegetarian(s)){return true;}
        }
        return false;
    }
    public boolean arrContainsNonLowCarb(ArrayList<String> myArr) {
        for(String s:myArr){
            if(containsNonLowCarb(s)){return true;}
        }
        return false;
    }
    public boolean arrContainsNonKeto(ArrayList<String> myArr) {
        for(String s:myArr){
            if(containsNonKeto(s)){return true;}
        }
        return false;
    }
    public boolean arrContainsNonVegan(ArrayList<String> myArr) {
        for(String s:myArr){
            if(containsNonVegan(s)){return true;}
        }
        return false;
    }
    //
    public String containsGlutenAsString(ArrayList<String> myArr) {
        ArrayList<String> contGluten = new ArrayList<>();

        for(String s:myArr){
            if(containsGluten(s)){contGluten.add(s);}
        }
        return String.join(", ", contGluten);
    }
    public String containsNonVeganAsString(ArrayList<String> myArr) {
        ArrayList<String> contNonVegan = new ArrayList<>();

        for(String s:myArr){
            if(containsNonVegan(s)){contNonVegan.add(s);}
        }
        return String.join(", ", contNonVegan);
    }
    public String containsNonVegetarianAsString(ArrayList<String> myArr) {
        ArrayList<String> contNonVegetarian = new ArrayList<>();

        for(String s:myArr){
            if(containsNonVegetarian(s)){contNonVegetarian.add(s);}
        }
        return String.join(", ", contNonVegetarian);
    }
    public String containsNonKetoAsString(ArrayList<String> myArr) {
        ArrayList<String> contNonKeto = new ArrayList<>();

        for(String s:myArr){
            if(containsNonKeto(s)){contNonKeto.add(s);}
        }
        return String.join(", ", contNonKeto);
    }
    public String containsNonLowCarbAsString(ArrayList<String> myArr) {
        ArrayList<String> contNonLowCarb = new ArrayList<>();

        for(String s:myArr){
            if(containsNonLowCarb(s)){contNonLowCarb.add(s);}
        }
        return String.join(", ", contNonLowCarb);
    }
    //

    public ArrayList<String> getOrderAsArray(Order o){
        return o.getOrderAsArray();
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

        myOrderForSubRev = getOrderAsArray(o);
        String suggestionMessage = "";
        String orderDietaryPreference = o.getDietaryPreference();

        
        switch (orderDietaryPreference.toLowerCase()) {

            case "vegan": if(arrContainsNonVegan(this.myOrderForSubRev)){
                String randomElement ="";
                String s = containsNonVeganAsString(myOrderForSubRev);
                String[] undesiredIngredientsArr= s.split(", ");
                int undesiredIngredientsNumber = undesiredIngredientsArr.length;
                for(int i = 0 ; i< undesiredIngredientsNumber; i++){
                    randomElement = alternativeVeganIngredients.get(random.nextInt(alternativeVeganIngredients.size()));
                    suggestionMessage +=displayMessage(undesiredIngredientsArr[i].toLowerCase(),randomElement.toLowerCase(),orderDietaryPreference);
                }
            }break;

            case "vegetarian": if(arrContainsNonVegetarian(this.myOrderForSubRev)){
                String randomElement ="";
                String s = containsNonVegetarianAsString(myOrderForSubRev);
                String[] undesiredIngredientsArr= s.split(", ");
                int undesiredIngredientsNumber = undesiredIngredientsArr.length;
                for(int i = 0 ; i< undesiredIngredientsNumber; i++){
                    randomElement = alternativeVegetarianIngredients.get(random.nextInt(alternativeVegetarianIngredients.size()));
                    suggestionMessage +=displayMessage(undesiredIngredientsArr[i].toLowerCase(),randomElement.toLowerCase(),orderDietaryPreference);
                }
            }break;

            case "keto": if(arrContainsNonKeto(this.myOrderForSubRev)){
                String randomElement ="";
                String s = containsNonKetoAsString(myOrderForSubRev);
                String[] undesiredIngredientsArr= s.split(", ");
                int undesiredIngredientsNumber = undesiredIngredientsArr.length;
                for(int i = 0 ; i< undesiredIngredientsNumber; i++){
                    randomElement = alternativeKetoIngredients.get(random.nextInt(alternativeKetoIngredients.size()));
                    suggestionMessage +=displayMessage(undesiredIngredientsArr[i].toLowerCase(),randomElement.toLowerCase(),orderDietaryPreference);
                }
            }break;

            case "low_carb": if(arrContainsNonLowCarb(this.myOrderForSubRev)){
                String randomElement ="";
                String s = containsNonLowCarbAsString(myOrderForSubRev);
                String[] undesiredIngredientsArr= s.split(", ");
                int undesiredIngredientsNumber = undesiredIngredientsArr.length;
                for(int i = 0 ; i< undesiredIngredientsNumber; i++){
                    randomElement = alternativeLowCarbIngredients.get(random.nextInt(alternativeLowCarbIngredients.size()));
                    suggestionMessage +=displayMessage(undesiredIngredientsArr[i].toLowerCase(),randomElement.toLowerCase(),orderDietaryPreference);
                }
            }break;

            case "gluten_free": if(arrContainsGluten(this.myOrderForSubRev)){
                String randomElement ="";
                String s = containsGlutenAsString(myOrderForSubRev);
                String[] undesiredIngredientsArr= s.split(", ");
                int undesiredIngredientsNumber = undesiredIngredientsArr.length;
                for(int i = 0 ; i< undesiredIngredientsNumber; i++){
                    randomElement = alternativeGlutenFreeIngredients.get(random.nextInt(alternativeGlutenFreeIngredients.size()));
                    suggestionMessage +=displayMessage(undesiredIngredientsArr[i].toLowerCase(),randomElement.toLowerCase(),orderDietaryPreference);
                }
            }break;

            default: suggestionMessage = "All Your Ingredients Comply With Your Dietary Preference!";
            break;
        }

        return suggestionMessage;
    }
public void reset(){
        myOrderForSubRev.clear();
}

public String displayMessage(String undesiredI,String subI, String orderDP){

    return(undesiredI+ " isn't "+ orderDP+ ", you can try "+subI+" instead.\n");

}
}
