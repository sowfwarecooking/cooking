package FeatureTest;

import ProductionCode.Order;
import ProductionCode.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class customMealRequestSteps {

    @Given("I am on the {string} page")
    public void iAmOnThePage(String string) {
        Page p = new Page();
        p.setTitle(string);
        assertTrue(p.title.equalsIgnoreCase(string));

    }

    @When("I select ingredients {string}, {string}, and {string}")
    public void iSelectIngredientsAnd(String string, String string2, String string3) {
        Order o = new Order();
        o.setIngredients(string,string2,string3);
        assertEquals(o.ingredients.get(0), string);
        assertEquals(o.ingredients.get(1), string2);
        assertEquals(o.ingredients.get(2), string3);

    }

    @When("I submit my custom meal request")
    public void iSubmitMyCustomMealRequest() {
        Order o = new Order();
        String string = "rice";
        String string1 = "mustard";
        String string2 = "broccoli";

        o.setIngredients(string, string1, string2);
        String actual=o.submitOrder();
        String expected = string+", "+string1+", "+string2;
        assertEquals(expected,actual);
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("I should see {string}")
    public void iShouldSee(String string) {
        // Write code here that turns the phrase above into concrete actions
        Order o = new Order();
        String actual = o.mealCreatedSuccessfullyMessage();
        String expected = "Meal Created Successfully";
        assertEquals(expected, actual);


    }

    @When("I select ingredients {string} and {string}")
    public void iSelectIngredientsAnd(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        Order o = new Order();
        o.setIngredients(string, string2);
        assertEquals(string, o.ingredients.get(0));
        assertEquals(string2, o.ingredients.get(1));
    }

    //CONTINUE HERE!!!!!!!!!!!
    @When("I choose dietary preferences {string}")
    public void iChooseDietaryPreferencesAnd(String string) {
        // Write code here that turns the phrase above into concrete actions
    Order o = new Order();
    String s = "TOFU";
    String s1 = "SOY";
    o.setIngredients(s, s1);
    o.setDietaryPreference(string);
        assertEquals(o.getDietaryPreference(), string.toLowerCase());

    }

    @When("I submit my custom meal request without selecting ingredients")
    public void iSubmitMyCustomMealRequestWithoutSelectingIngredients() {
        // Write code here that turns the phrase above into concrete actions
        Order o = new Order();
        assertEquals("Please select at least one ingredient", o.submitOrder());
        assertEquals("Please select at least one ingredient", o.submitOrderWithDietaryPreferences());
    }



}
