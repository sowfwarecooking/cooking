package FeatureTest;

import ProductionCode.IngredientSubManager;
import ProductionCode.Ingredients;
import ProductionCode.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class IngredientSubstitutionSteps {
    Order myOrder = new Order();
    IngredientSubManager i = new IngredientSubManager();

    void initializeOrder(){
        i.setAlternativeVeganIngredients("Vegan Meat");
        i.setRestrictedVeganIngredients("Beef");
        myOrder.setDietaryPreference("Vegan");
        myOrder.setIngredients("Beef","RICE","milk");
        i.setOrder(myOrder);
    }
    public void resetState() {
        i = new IngredientSubManager();
        myOrder = new Order();
        i.reset();
    }
    @Given("the user follows a specific diet type \\(e.g., VEGAN, KETO, LOW_CARB, VEGETARIAN, GLUTEN_FREE)")
    public void theUserFollowsASpecificDietTypeEGVEGANKETOLOWCARBVEGETARIANGLUTENFREE() {
        // Write code here that turns the phrase above into concrete actions

        initializeOrder();
        assertTrue(i.hasADiet());

    }
    @Given("the user inputs a recipe containing restricted ingredients")
    public void theUserInputsARecipeContainingRestrictedIngredients() {
        // Write code here that turns the phrase above into concrete actions

        i.restrictedKetoIngredients.add("bread");
        ArrayList<String>tempSelection = new ArrayList<>();
        tempSelection.add("BREAD");
        assertTrue(i.selectionContainsRestrictions(tempSelection));
    }
    @When("the system processes the recipe")
    public void theSystemProcessesTheRecipe() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager x = new IngredientSubManager();
        String s = "Tuna";
        String s2 = "Milk";
        x.submitOrderForSubReview(s,s2);
        assertEquals(s, x.myOrderForSubRev.get(0));
        assertEquals(s2, x.myOrderForSubRev.get(1));

    }
    @Then("the system suggests suitable alternatives that adhere to the user's dietary restrictions")
    public void theSystemSuggestsSuitableAlternativesThatAdhereToTheUserSDietaryRestrictions() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager z = i;
        System.out.println(z.suggestAlternativeIngredients(myOrder));
        assertNotNull(z.suggestAlternativeIngredients(myOrder));

    }

    @Given("the user indicates an ingredient is unavailable")
    public void theUserIndicatesAnIngredientIsUnavailable() {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String str1 = "cheese";
        String str2 = "pasta";
        String str3 = "beef";

        i.addAvailableIngredients(2, str1);
        i.addAvailableIngredients(str2);
        assertTrue(i.isAvailableIngredient(str2));
        assertFalse(i.isAvailableIngredient(str3));
    }
    @Given("the user inputs a recipe containing that ingredient")
    public void theUserInputsARecipeContainingThatIngredient() {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String str1 = "cheese";
        String str2 = "pasta";
        String str3 = "Onion";


        System.out.println(i.addDesiredIngredients(str1, str3));
        assertEquals(str1+ " IS UNAVAILABLE\n"+str3+ " IS UNAVAILABLE\n"+"Try These Instead: \n" +
                "def A\ndef A\n", i.addDesiredIngredients(str1, str3));
    }
    @Then("the system suggests suitable alternatives based on availability")
    public void theSystemSuggestsSuitableAlternativesBasedOnAvailability() {
        // Write code here that turns the phrase above into concrete actions
    Ingredients i = new Ingredients();
    i.addAvailableIngredients("A","B","C");
        i.addAvailableIngredients(2,"D","E","F");

        System.out.println( i.addDesiredIngredients("G"));
        System.out.println( i.addDesiredIngredients("A","B","L"));
    }
















}
