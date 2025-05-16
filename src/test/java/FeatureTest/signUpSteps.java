package FeatureTest;

import ProductionCode.SignUper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class signUpSteps {
    private String username;
    private String password;
    private String message;
    private SignUper signUper;
    private String result;
    @Given("user at sign up page")
    public void userAtSignUpPage() {
        signUper = new SignUper();
    }

    @When("the user enters a valid username {string} and password {string}")
    public void theUserEntersAValidUsernameAndPassword(String arg0, String arg1) {
        this.username = arg0;
        this.password = arg1;
        this.result = signUper.signUp(arg0, arg1);



    }

    @Then("the system grantes access")
    public void theAccountShouldBeCreatedSuccessfully() {
       boolean added = signUper.isAdded(username);

        assertTrue(added);

    }


    @When("the user enters an taken username {string} and password {string}")
    public void theUserEntersAnTakenUsernameAndPassword(String arg0, String arg1) {
         this.message = signUper.signUp(arg0, arg1);
    }

    @Then("the system denies access")
    public void theSystemDeniesAccess() {
        assertEquals("Username already taken", message);
    }


    @When("the user enters a valid username {string} and invalid password {string}")
    public void theUserEntersAValidUsernameAndInvalidPassword(String arg0, String arg1) {
        this.message=signUper.signUp(arg0, arg1);

    }

    @Then("the system denies access with message {string}")
    public void theSystemDeniesAccessWithMessage(String arg0) {
        assertEquals("Weak password", message);
    }


}
