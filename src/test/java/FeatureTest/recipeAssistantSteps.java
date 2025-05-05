package FeatureTest;

import io.cucumber.java.en.*;
import ProductionCode.recipeAssistant;

import java.util.Optional;

import static org.junit.Assert.*;

public class recipeAssistantSteps {

    @Given("the user has a dietary preference of {string}")
    public void the_user_has_a_dietary_preference_of(String string) {
        recipeAssistant ingreadnt = new recipeAssistant();
        ingreadnt.setIngredient(string);
        assertNotNull(ingreadnt);

    }
    @When("the user requests recipe recommendations")
    public void the_user_requests_recipe_recommendations() {
        recipeAssistant assistant = new recipeAssistant();
        assistant.setRestrictions("vegetarian");

        // Verify restrictions are set correctly
        assertEquals("vegetarian", assistant.getRestrictions());

        // Fetch recommendation
        String recommendation = assistant.sugestmeal();

        // Verify recommendation is not null
        assertNotNull(recommendation);
        System.out.println(recommendation);

    }
    @Then("the assistant should recommend only vegetarian recipes")
    public void the_assistant_should_recommend_only_vegetarian_recipes() {
        recipeAssistant assistant = new recipeAssistant();
        assistant.setRestrictions("vegetarian");

        // Verify restrictions are set correctly
        assertEquals("vegetarian", assistant.getRestrictions());

        // Fetch recommendation
        String recommendation = assistant.sugestmeal();
        boolean containsVegetarian = false;

        // Check if recommendation contains vegetarian recipes
        if (recommendation.contains("Vegetarian") || recommendation.contains("Veggie")|| recommendation.contains("Vegan")) {
            containsVegetarian = true;
        }
        assertTrue(containsVegetarian);


    }

    @Given("the user wants recipes that take less than {int} minutes to cook")
    public void the_user_wants_recipes_that_take_less_than_minutes_to_cook(Integer int1) {
      recipeAssistant assistant = new recipeAssistant();
      assistant.setTime(int1);
      int expectedTime = int1;
      assertEquals(expectedTime, assistant.getTime());
    }
    @When("the user requests recipe recommendations based on cooking time")
    public void the_user_requests_recipe_recommendations_based_on_cooking_time() {

    }
    @Then("the assistant should recommend recipes with a cooking time of less than {int} minutes")
    public void the_assistant_should_recommend_recipes_with_a_cooking_time_of_less_than_minutes(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
    }

}
