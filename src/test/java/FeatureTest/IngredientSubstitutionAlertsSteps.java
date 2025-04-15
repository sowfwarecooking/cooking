package FeatureTest;
import ProductionCode.Ingredients;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientSubstitutionAlertsSteps {
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
    i.addAvailableIngredients("a","b","c","d");
    i.addDesiredIngredients("a","B","c");
        System.out.println(i.getSelectedIngredients());
        i.removeDesiredIngredients("A");
        System.out.println(i.getSelectedIngredients());
        i.addDesiredIngredients("a");
        System.out.println(i.getSelectedIngredients());
        i.substituteDesiredIngredients("A", "d");
        System.out.println(i.getSelectedIngredients());

    }
    @Then("the chef is alerted")
    public void the_chef_is_alerted() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("can approve or adjust.")
    public void can_approve_or_adjust() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the chef approves {string}")
    public void the_chef_approves(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the recipe is updated")
    public void the_recipe_is_updated() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the approval logged.")
    public void the_approval_logged() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the chef selects {string}")
    public void the_chef_selects(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the recipe is updated with {string}")
    public void the_recipe_is_updated_with(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the adjustment logged.")
    public void the_adjustment_logged() {
        // Write code here that turns the phrase above into concrete actions

    }


}
