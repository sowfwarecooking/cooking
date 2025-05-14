package FeatureTest;

import ProductionCode.recipeAssistant;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class recipeAssistantSteps {

    private recipeAssistant app;
    private recipeAssistant assistant;
    private String result;

    @Before
    public void setUp() {
        assistant = new recipeAssistant();
        app = new recipeAssistant();
    }

    @Given("the user has a dietary preference of {string}")
    public void the_user_has_a_dietary_preference_of(String preference) {
        assistant.setRestrictions(preference);
        assertNotNull(assistant.getRestrictions());
    }

    @When("the user requests recipe recommendations")
    public void the_user_requests_recipe_recommendations() {
        result = assistant.suggestMeal(assistant.getRestrictions());
        assertNotNull(result);
    }

    @Then("the assistant should recommend only vegetarian recipes")
    public void the_assistant_should_recommend_only_vegetarian_recipes() {
        System.out.println("Response: " + result);
        boolean isVegetarian = result.toLowerCase().contains("vegetarian");
        assertTrue("Expected a vegetarian meal, but got: " + result, isVegetarian);
    }

    @Given("the user has a dietary preference of {string} and ingredients {string} and time {string}")
    public void theUserHasADietaryPreferenceOfAndIngredientsAndTime(String arg0, String arg1, String arg2) {
       app.setRestrictions(arg0);
       app.setIngredient(arg1);
       app.setTime(Integer.parseInt(arg2));
       assertNotNull(app.getRestrictions());
       assertNotNull(app.getIngredient());
       assertNotNull(app.getTime());
    }

    @When("the user requests recipe recommendations based on unmatch")
    public void theUserRequestsRecipeRecommendationsBasedOnUnmatch() {
        result = app.suggestMealBasedOnIngredient(app.getTime(), app.getRestrictions(), app.getIngredient());
        assertNotNull(result);
    }

    @Then("the assistant should return No suitable meal")
    public void theAssistantShouldReturnNoSuitableMeal() {
        String expected = "not suitable";
        assertEquals(expected, result);
    }

    @Given("the user has a entered of nothing")
    public void theUserHasAEnteredOfNothing() {
        app.setRestrictions("");
        app.setIngredient("");
        app.setTime(0);
        assertNotNull(app);
    }

    @When("the user requests recipe recommendations of nothing")
    public void theUserRequestsRecipeRecommendationsOfNothing() {
        result = app.suggestMealBasedOnIngredient(app.getTime(), app.getRestrictions(), app.getIngredient());
        assertNotNull(result);
    }

    @Then("the assistant should return Nothing")
    public void theAssistantShouldReturnNothing() {
        String expected = "not suitable";
        assertEquals(expected, result);
    }

    @Given("the chef entered the time for cooking")
    public void theChefEntederTheTimeForCooking() {
        app.setTime(35);
        assertNotNull(app);
    }

    @Then("the assistant should recommend recipes that can be cooked in the given time")
    public void theAssistantShouldRecommendRecipesThatCanBeCookedInTheGivenTime() {
        String response = app.suggestMealBasedOnTime(app.getTime());
        assertNotNull(response);
        boolean validResponse = response.contains(" minutes");
        assertTrue(validResponse);
    }

    @Given("the chef entered the time for cooking and ingredients and restrictions")
    public void theChefEnteredTheTimeForCookingAndIngredientsAndRestrictions() {
        app.setTime(40);
        app.setRestrictions("vegan");
        app.setIngredient("Tomatoes, basil, garlic");
        assertNotNull(app);
    }

    @Then("the assistant should know recommend recipes t")
    public void theAssistantShouldKnowRecommendRecipesT() {
        String response = app.suggestMealBasedOnIngredient(app.getTime(), app.getRestrictions(), app.getIngredient());
        System.out.println(response);
        assertNotNull(response);

        boolean validResponse = response.contains("Tomato Basil Soup");
        assertTrue(validResponse);
    }
}
