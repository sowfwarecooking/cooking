package FeatureTest;
import productionCode.Chef;
import productionCode.Ingredients;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientSubstitutionAlertsSteps {
    Chef c = new Chef("chefUser");
    @Given("{string} is unavailable in a recipe")
    public void is_unavailable_in_a_recipe(String string) {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        i.addAvailableIngredients(string);
        assertTrue(i.isAvailableIngredient(string));
    }
    @When("{string} is substituted")
    public void is_substituted(String string) {
        // Write code here that turns the phrase above into concrete actions
    Ingredients i = new Ingredients();
    Chef c = new Chef("chefUser");
    i.addAvailableIngredients("a","b","c","d");
    i.addDesiredIngredients(c,"a","B","c");
        System.out.println(i.getSelectedIngredients());
        i.removeDesiredIngredients("A", c);
        System.out.println(i.getSelectedIngredients());
        i.addDesiredIngredients(c,"a");
        System.out.println(i.getSelectedIngredients());
        i.substituteDesiredIngredients("A", "d", c);
        System.out.println(i.getSelectedIngredients());

    }
    @Then("the chef is alerted")
    public void the_chef_is_alerted() {
        // Write code here that turns the phrase above into concrete actions
       Ingredients i = new Ingredients();

        i.addAvailableIngredients("a","b","c","d");
        i.addDesiredIngredients(c,"a","b","c");
        i.removeDesiredIngredients("a", c);


        String actual = c.getIngredientChangeMessage();
        String expected = "[a, b, c] to " +"[b, c]";
        assertEquals(expected, actual);

        i.substituteDesiredIngredients("c","d", c);
        actual = c.getIngredientChangeMessage();
        expected = "[b, c] to " +"[b, d]";
        assertEquals(expected, actual);

        i.addDesiredIngredients(c, "a");
        actual = c.getIngredientChangeMessage();
        expected = "[b, d] to " +"[b, d, a]";
        assertEquals(expected, actual);

        i.addDesiredIngredients(c, "f");
        actual = c.getIngredientChangeMessage();
        expected = "[b, d, a] to " +"[b, d, a]";
        assertEquals(expected, actual);

    }
    @Then("can approve or adjust.")
    public void can_approve_or_adjust() {
        // Write code here that turns the phrase above into concrete actions


    }
    @When("the chef approves {string}")
    public void the_chef_approves(String string) {
        // Write code here that turns the phrase above into concrete actions
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c",string);
        i.addDesiredIngredients(c,"a","b",string);
        c.approveOrder(i);
        String actual = String.valueOf(c.getApprovedOrder());
        String expected = "[a, b, "+ string+"]";

        assertEquals(expected, actual);
    }
    @Then("the recipe is updated")
    public void the_recipe_is_updated() {
        // Write code here that turns the phrase above into concrete actions
        //--------------->Continue tomorrow!!!!!!!!!!
        String string = "d";
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c",string);
        i.addDesiredIngredients(c,"a","b",string);
        c.approveOrder(i);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("a");expected.add("b");expected.add(string);
        assertEquals(expected, c.getApprovedOrder());

    }
    @Then("the approval logged.")
    public void the_approval_logged() {
        // Write code here that turns the phrase above into concrete actions
        String string = "d";
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c",string);
        i.addDesiredIngredients(c,"a","b",string);

        String expected = "\nOrder Approved\n";
        assertEquals(expected, c.approveOrder(i));
    }
    @When("the chef selects {string}")
    public void the_chef_selects(String string) {
        // Write code here that turns the phrase above into concrete actions
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c",string);
        i.addDesiredIngredients(c,"a","b");
        c.addIngredient(i, string);
        System.out.println(c.getApprovedOrder());
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = c.getApprovedOrder();

        expected.add("a");expected.add("b");expected.add(string);
        assertEquals(expected,actual);

        c.removeIngredient(i, string);
        System.out.println(c.getApprovedOrder());
        expected.remove(string);
        actual = c.getApprovedOrder();
        assertEquals(expected, actual);

        c.subIngredient(i, "B", string);
        System.out.println(c.getApprovedOrder());
        expected.remove("b");expected.add(string);
        actual=c.getApprovedOrder();
        assertEquals(expected, actual);

    }
    @Then("the recipe is updated with {string}")
    public void the_recipe_is_updated_with(String string) {
        // Write code here that turns the phrase above into concrete actions
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c",string);
        i.addDesiredIngredients(c,"a","b");
        c.addIngredient(i, string);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("a");expected.add("b");expected.add(string);
        assertEquals(expected, i.getSelectedIngredients());
    }
    @Then("the adjustment logged.")
    public void the_adjustment_logged() {
        // Write code here that turns the phrase above into concrete actions
        Chef c = new Chef("chefUser");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients("a","b","c");
        i.addDesiredIngredients(c,"a","b");
        String actual1 = c.addIngredient(i, "c");
        String expected1 ="\nChef Added Ingredient Successfully\n";
        String actual2 = c.removeIngredient(i,"b");
        String expected2 ="\nChef Removed Ingredient Successfully\n";
        String actual3 = c.subIngredient(i, "c", "b");
        String expected3 ="\nChef Substituted Ingredient Successfully\n";

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);

    }


}
