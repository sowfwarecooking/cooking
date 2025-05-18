package FeatureTest;

import ProductionCode.Customer;
import ProductionCode.menuItems;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static org.junit.Assert.*;

public class invoiceSteps {
    Customer customer;
    menuItems menuItems;
    invoiceSteps (){

         customer = new Customer("testUser", "Vegetarian", "none");
         menuItems = new menuItems();
    }
    @Given(": the customer is logged in and is Vegetarian")
    public void Vegetarian() {
         customer = new Customer("testUser", "Vegetarian", "none");
        assertEquals("Vegetarian", customer.getDietType());
    }
    @When("the customer purchases see the menu")
    public void the_customer_purchases_see_the_menu() {
         customer.viewMenu();
    }
    @Then("the customer selects {string}")
    public void the_customer_selects(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the invoice should list the item {string}")
    public void the_invoice_should_list_the_item(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the invoice should show the total amount as {double}")
    public void the_invoice_should_show_the_total_amount_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the invoice should include the date of purchase")
    public void the_invoice_should_include_the_date_of_purchase() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
