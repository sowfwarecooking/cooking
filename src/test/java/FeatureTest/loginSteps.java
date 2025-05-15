package FeatureTest;

import ProductionCode.userLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class loginSteps {
    userLogin ul;

    @Given("the data loaded from file")
    public void theDataLoadedFromFile() {
        ul = new userLogin();

    }


    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        System.out.println("user is on login page");
    }


    @When("the user enters a valid {string} and {string}")
    public void theUserEntersAValidAnd(String arg0, String arg1) {
        ul.setUsername(arg0);
        ul.setPassword(arg1);


    }


    @Then("the system grants access")
    public void theSystemGrantsAccess() {
        String result = ul.attemptLogin(ul.getUsername(), ul.getPassword());
        assert result.equals("Access granted");
    }

    @When("the user enters a valid {string} and wrong {string}")
    public void theUserEntersAValidAndWrong(String arg0, String arg1) {
        ul.setUsername(arg0);
        ul.setPassword(arg1);

    }

    @Then("the system displays the error message")
    public void theSystemDisplaysTheErrorMessage() {
        String result = ul.attemptLogin(ul.getUsername(), ul.getPassword());
        assertEquals("Incorrect password", result);
    }


    @When("the user enters an unregistered username {string}")
    public void theUserEntersAnUnregisteredUsername(String arg0) {
        ul.setUsername(arg0);
        assertEquals(arg0, ul.getUsername());

    }


    @Then("the system displays the error message saying {string};")
    public void theSystemDisplaysTheErrorMessageSaying(String arg0) {
        String expectedMessage = arg0;
        String actualMessage = ul.attemptLogin(ul.getUsername(), ul.getPassword());
        assertEquals(expectedMessage, actualMessage);
    }
}
