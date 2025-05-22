package productionCode;

public class recipeAssistant {

    private String ingredient;
    private int time;
    private String restrictions;
    private String recommendation;
    private llmConnction app;


    public recipeAssistant() {
        this.app = new llmConnction();
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
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

    public String suggestMeal(String restriction) {
        return app.suggestMeal(this.getRestrictions());
    }

    public String suggestMealBasedOnTime(int time) {
        return app.suggetMealTime(time);
    }

    public String suggestMealBasedOnIngredient(int i, String vegetarian, String checkin) {
        return app.sugestmealBasedONIngredients(time, restrictions, ingredient);
    }


}
