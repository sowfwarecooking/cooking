package ProductionCode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

public class recipeAssistant {

    String ingredient;
    int time;
    String restrictions;
    String recommendation;
    llmConnction app = new llmConnction();

    public recipeAssistant(String ingredient, int time, String restrictions) {
        this.ingredient = ingredient;
        this.time = time;
        this.restrictions = restrictions;
        this.app = new llmConnction();
    }
    public recipeAssistant(){

    }
    public  void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getIngredient() {
        return ingredient;
    }
    public int getTime() {
        return time;
    }
    public String getRestrictions() {
        return restrictions;
    }
    public String sugestmeal(){
        String response = app.sugestmeal(restrictions);
        return  response;
}

}