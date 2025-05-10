package FeatureTest;

import ProductionCode.Admin;
import ProductionCode.finance;
import io.cucumber.java.en.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class balanceSteps {
    finance F = new finance(); // Make sure this is initialized

    public balanceSteps() throws IOException {
    }


    @Given("i am a admin")
    public void iAmAAdmin() {
        Admin admin = new Admin("admin");
        assertNotNull(admin);

        // Ensure F is properly initialized, as it seems to be the "account" or "finance" object
        assertNotNull(F);
        assertEquals(1000f, F.getBalance(), 0.01f); // Check the balance is initially 1000
    }



    @When("i buy product")
    public void iBuyProduct() {
        F.buy("pepper",2);
        System.out.println(F.getBalance());
    }

    @Then("i should see balance")
    public void iShouldSeeBalance() {
        assertEquals(996f, F.getBalance(), 0.01f);
    }

    @When("i buy product multiple times")
    public void iBuyProductMultipleTimes() {
        F.setBudget(1000f);
        System.out.println(F.getBalance());
        F.buy("pepper",2);
        F.buy("kiwi",5);
        F.buy("Carrot",3);
        System.out.println(F.getBalance());

    }

    @Then("i should see total balance")
    public void iShouldSeeTotalBalance() {
        float expectedBalance = 981f;

        assertEquals(expectedBalance, F.getBalance(), 0.01f);

    }
}
