package FeatureTest;

import productionCode.Customer;
import productionCode.finance;
import productionCode.menuItems;
import io.cucumber.java.en.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class invoiceSteps {
    Customer customer = new Customer("admin", "Vegetarian", "none");
    menuItems menuItems = new menuItems();

    @Given(": the customer is logged in and is Vegetarian")
    public void Vegetarian() {
    assertEquals("Vegetarian", customer.getDietaryPreferences());

    }
    @When("the customer purchases see the menu")
    public void the_customer_purchases_see_the_menu() {
        String menu = menuItems.loadMenuItems(customer.getDietaryPreferences(), customer.getAllergies());
        String expected =
                "Veggie Burger - gluten - $8.99\n" +
                        "Avocado Toast - soy - $7.50\n" +
                        "Mushroom Spinach Wrap - gluten - $7.95\n" +
                        "Avocado Toast with Peanut Butter - tree nuts - $8.75\n" +
                        "Baked Sweet Potatoes - soy - $8.50";
        assertEquals(expected, menu);
    }


    @And("the customer selects a single item")
    public void theCustomerSelectsASingleItem() throws IOException {
        customer.selectOrder("Veggie Burger");
        customer.placeOrder(customer.getSected());
        assertEquals("Veggie Burger", customer.getSected());
    }

    @Then("the invoice is generated")
    public void theInvoiceIsGenerated() {
        String expected =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "Invoice: 8.99";
        String invoice = customer.invoice();
        assertEquals(expected, invoice);
    }

    @And("the customer selects multiple items")
    public void theCustomerSelectsMultipleItems() throws IOException {
        customer.selectOrder("Veggie Burger");
        customer.placeOrder(customer.getSected());
        customer.selectOrder("Tofu Stir Fry");
        customer.placeOrder(customer.getSected());
        float actual = Math.round(customer.getCharge() * 100f) / 100f;
        float expected = 18.79f;
        assertEquals(expected, actual, 0.001f);
        assertEquals(actual, customer.getCharge(), 0.001f);


    }



    @Then("the invoice is generated for multiple items")
    public void theInvoiceIsGeneratedForMultipleItems() {
        String expected =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "Invoice: 18.79";
        String invoice = customer.invoice();
        assertEquals(expected, invoice);
    }

    @Then("the balance is updated")
    public void theBalanceIsUpdated() throws IOException {
        finance finance = new finance(1000);
        finance.buy("pepper",2);
        //996f

        finance.buyer(customer.getCharge());
        assertEquals(1014.79f, finance.getBalance(),0.001f);
    }
}
