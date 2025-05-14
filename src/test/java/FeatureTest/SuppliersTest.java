package FeatureTest;

import ProductionCode.Suppliers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SuppliersTest {

    private Suppliers supplierObj;
    private Object Arrays;

    // Given step to initialize the Suppliers object
    @Given("the system is connected to the suppliers")
    public void the_system_is_connected_to_the_suppliers() throws IOException {
        supplierObj = new Suppliers();
        // Initialize or assert necessary conditions, if applicable
        assertNotNull(supplierObj.suppliers1);  // Example check to ensure suppliers1 is initialized
        assertNotNull(supplierObj.suppliers3);  // Example check to ensure suppliers3 is initialized
    }

    // When step for requesting price of an ingredient
    @When("the kitchen manager requests the price for an ingredient")
    public void the_kitchen_manager_requests_the_price_for_an_ingredient() {
        assertNotNull(supplierObj);  // Ensure supplierObj is initialized
    }

    // Then step to verify if the correct price is returned for the ingredient
    @Then("the system should return the current price for ingredient from the suppliers")
    public void the_system_should_return_the_current_price_for_ingredient_from_the_suppliers() throws IOException {
        Suppliers supplierObj = new Suppliers();
        supplierObj.loadFromFiles();

        float actualPrice = supplierObj.getBestPrice("potato");
        float expectedPrice = 0.7f; // Adjust this according to the expected result
        assertEquals(expectedPrice, actualPrice,0.00000000001f);
    }

    @And("the system should reload the stock")
    public void theSystemShouldReloadTheStock() {
    }
    @Test
    public void lessThan3Suppliers() throws IOException {
        Suppliers supplierObj = new Suppliers();

        // Manually create a List using ArrayList
        List<String> filePaths = new ArrayList<>();
        filePaths.add("data/supplier1.txt");
        filePaths.add("data/supplier2.txt");
        // No need to add a third file to trigger the exception

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            supplierObj.readFromFiles(filePaths);
        });}}


