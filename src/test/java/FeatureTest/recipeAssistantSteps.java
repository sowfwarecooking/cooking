package FeatureTest;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import ProductionCode.recipeAssistant;

import static org.junit.Assert.*;

public class recipeAssistantSteps {

    private recipeAssistant assistant;

    @Before
    public void setUp() {
        assistant = new recipeAssistant(); // This runs before every scenario
    }

    @Given("the user has a dietary preference of {string}")
    public void the_user_has_a_dietary_preference_of(String preference) {
        assistant.setIngredient(preference);
        assertNotNull(assistant);
    }

    @When("the user requests recipe recommendations")
    public void the_user_requests_recipe_recommendations() {
        assistant.setRestrictions("vegetarian");
        assertEquals("vegetarian", assistant.getRestrictions());
        String recommendation = assistant.sugestmeal();
        assertNotNull(recommendation);
        System.out.println(recommendation);
    }

    @Then("the assistant should recommend only vegetarian recipes")
    public void the_assistant_should_recommend_only_vegetarian_recipes() {
        assistant.setRestrictions("vegetarian");
        assertEquals("vegetarian", assistant.getRestrictions());
        String recommendation = assistant.sugestmeal();
        assertNotNull(recommendation);
        boolean containsVegetarian = recommendation.contains("Vegetarian") ||
                recommendation.contains("Veggie") ||
                recommendation.contains("Vegan");
        assertTrue(containsVegetarian);
    }

    @Given("the user wants recipes that take less than {int} minutes to cook")
    public void the_user_wants_recipes_that_take_less_than_minutes_to_cook(Integer time) {
        assistant.setTime(time);
        assertEquals(time.intValue(), assistant.getTime());
    }

    @When("the user requests recipe recommendations based on cooking time")
    public void the_user_requests_recipe_recommendations_based_on_cooking_time() {
        assistant.setTime(30); // Default test time
        assertNotNull(assistant);
        String recommendation = assistant.sugestMealBeasdonTime(assistant.getTime());
        assertNotNull(recommendation);
    }

    @Then("the assistant should recommend recipes with a cooking time of less than {int} minutes")
    public void the_assistant_should_recommend_recipes_with_a_cooking_time_of_less_than_minutes(Integer time) {
        assistant.setTime(time);
        assertEquals(time.intValue(), assistant.getTime());
        String recommendation = assistant.sugestMealBeasdonTime(assistant.getTime());
        assertNotNull(recommendation);
        boolean contains = recommendation.contains(time.toString());
        assertTrue(contains);
    }

    @Given("the user has a dietary preference is {string}")
    public void theUserHasADietaryPreferenceIs(String pref) {
        assistant.setIngredient(pref);
        assertNotNull(assistant);
    }

    @And("the user wants recipes that take  {int} minutes to cook")
    public void theUserWantsRecipesThatTakeMinutesToCook(int time) {
        assistant.setTime(time);
        assertNotNull(assistant.getTime());
    }

    @And("the user has a ingredient of {string}")
    public void theUserHasAIngredientOf(String ingredient) {
        assistant.setIngredient(ingredient);
        assertNotNull(assistant.getIngredient());
    }

    @When("the user requests recipe recommendations based on cooking time and dietary preferences and available ingredient and dietary preferences")
    public void theUserRequestsRecipeRecommendationsBasedOnCookingTimeAndDietaryPreferencesAndAvailableIngredientAndDietaryPreferences() {
        String sugget = assistant.sugestmealOningredint();
        assertNotNull(sugget);
    }

    @Then("the assistant should recommend recipes with a cooking time of less than {int} minutes and dietary preferences of keto")
    public void theAssistantShouldRecommendRecipesWithACookingTimeOfLessThanMinutesAndDietaryPreferencesOfKeto(int time) {
        String sugget = assistant.sugestmealOningredint();
        System.out.println(sugget);
        boolean contains =  sugget.contains("Chicken Pasta with Tomato");
        assertTrue(contains);




    }
}
