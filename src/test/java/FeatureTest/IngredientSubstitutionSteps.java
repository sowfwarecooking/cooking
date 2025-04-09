package FeatureTest;

import ProductionCode.IngredientSubManager;
import ProductionCode.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IngredientSubstitutionSteps {
    Order myOrder = new Order();
    void initializeOrder(){
        myOrder.setDietaryPreference("VEGAN");
    }
    @Given("the user follows a specific diet type \\(e.g., VEGAN, KETO, LOW_CARB, VEGETARIAN, GLUTEN_FREE)")
    public void theUserFollowsASpecificDietTypeEGVEGANKETOLOWCARBVEGETARIANGLUTENFREE() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager i = new IngredientSubManager();
        initializeOrder();
        i.setOrder(myOrder);
        assertTrue(i.hasADiet());
    }
    @Given("the user inputs a recipe containing restricted ingredients")
    public void theUserInputsARecipeContainingRestrictedIngredients() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager i = new IngredientSubManager();
        i.restrictedKetoIngredients.add("bread");
        ArrayList<String>tempSelection = new ArrayList<>();
        tempSelection.add("BREAD");
        assertTrue(i.selectionContainsRestrictions(tempSelection));
    }
    @When("the system processes the recipe")
    public void theSystemProcessesTheRecipe() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager i = new IngredientSubManager();
        String s = "Tuna";
        String s2 = "Milk";
        i.sumbitOrderForSubReview(s,s2);
        assertEquals(s, i.myOrderforSubRev.get(0));
        assertEquals(s2, i.myOrderforSubRev.get(1));

    }
    @Then("the system suggests suitable alternatives that adhere to the user's dietary restrictions")
    public void theSystemSuggestsSuitableAlternativesThatAdhereToTheUserSDietaryRestrictions() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("displays explanations for each suggestion")
    public void displaysExplanationsForEachSuggestion() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("the user indicates an ingredient is unavailable")
    public void theUserIndicatesAnIngredientIsUnavailable() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("the user inputs a recipe containing that ingredient")
    public void theUserInputsARecipeContainingThatIngredient() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the system suggests suitable alternatives based on availability")
    public void theSystemSuggestsSuitableAlternativesBasedOnAvailability() {
        // Write code here that turns the phrase above into concrete actions

    }
    /// /////CHANGE 3RD SCENARIO
    @Given("the user receives ingredient suggestions")
    public void theUserReceivesIngredientSuggestions() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the suggestions are displayed")
    public void theSuggestionsAreDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("each suggestion includes an explanation of why it is suitable")
    public void eachSuggestionIncludesAnExplanationOfWhyItIsSuitable() {
        // Write code here that turns the phrase above into concrete actions

    }













}
