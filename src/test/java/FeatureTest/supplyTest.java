package FeatureTest;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import ProductionCode.supplyVewing;
import ProductionCode.Admin;

public class supplyTest {

    @Given("the kitchen manager is logged into the system")
    public void the_kitchen_manager_is_logged_into_the_system() {
        // Simulate the login process for the kitchen manager.
        Admin admin = new Admin("admin");
        assertNotNull(admin);  // Ensure the admin object is not null, which means login is successful.
    }

    @When("they update the stock level of an ingredient after usage")
    public void they_update_the_stock_level_of_an_ingredient_after_usage() {
        // Simulate updating the stock level after using an ingredient.
        supplyVewing supply = new supplyVewing();
        supply.addIngredient("kiwi", 0);
        supply.updateIngredientQuantity("kiwi", -1);  // Reduce the stock of kiwi by 1.

        // Get the restocking message after updating stock.
        String message = supplyVewing.getRestockingMessage("kiwi");
        String expectedMessage = "Restock needed: kiwi (-1 left)";  // Adjust based on your logic.

        // Assert that the message matches the expected message.
        assertEquals(expectedMessage, message);
    }

    @Then("the system should reflect the updated stock level in real time")
    public void the_system_should_reflect_the_updated_stock_level_in_real_time() {
        // Simulate checking if the stock level is correctly updated in the system.
        supplyVewing supply = new supplyVewing();
        supply.addIngredient("onion", 1);
        assertEquals(1, supply.getStockLevel("onion"));
    }

    @Given("an ingredient's stock level falls below a predefined threshold")
    public void an_ingredient_s_stock_level_falls_below_a_predefined_threshold() {

        supplyVewing supply = new supplyVewing();

        // Assuming banana stock is below threshold, check if it's low stock.
        assertTrue(supply.checkLowStock("banana"));  // Check if banana stock is below threshold.
    }

    @When("the system detects the low stock level")
    public void the_system_detects_the_low_stock_level() {

        supplyVewing supply = new supplyVewing();

        // Confirm the system detects low stock for banana.
        assertTrue(supply.checkLowStock("banana"));  // Ensure banana is detected as low stock.
    }

    @Then("it should automatically generate a restocking suggestion and notify the kitchen manager")
    public void it_should_automatically_generate_a_restocking_suggestion_and_notify_the_kitchen_manager() {

        supplyVewing supply = new supplyVewing();

        // Get the restocking message.
        String message = supplyVewing.getRestockingMessage("banana");
        String expectedMessage = "Restock needed: banana (5 left)";  // Adjust based on your logic.

        // Assert that the restocking message matches the expected message.
        assertEquals(expectedMessage, message);
    }
    @Then("it should automatically generate a  multi restocking suggestion and notify the kitchen manager")
    public void it_should_automatically_generate_a_multi_restocking_suggestion_and_notify_the_kitchen_manager() {
        supplyVewing supply = new supplyVewing();
        String message = supply.checkLowStock();
        String expectedMessage = "Restock needed: banana (5 left)\n" +
                "Restock needed: kiwi (-1 left)\n" +
                "Restock needed: onion (1 left)\n" +
                "Restock needed: orange (4 left)\n";

        assertEquals(expectedMessage, message);
    }
}