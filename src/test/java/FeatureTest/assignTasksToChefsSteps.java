package FeatureTest;

import ProductionCode.Chef;
import ProductionCode.KitchenManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class assignTasksToChefsSteps {

    KitchenManager m;

    @Before
    public void setup (){
         m = new KitchenManager();
        Chef chef1 = new Chef("Sarah", Chef.expertise.PASTRY_EXPERTISE,1);
        Chef chef2 = new Chef("Ahmad", Chef.expertise.GRILLING_EXPERTISE,2);
        Chef chef3 = new Chef("Yousef", Chef.expertise.SALAD_EXPERTISE,3);
        Chef chef4 = new Chef("Khaled", Chef.expertise.SEAFOOD_EXPERTISE,4);
        Chef chef5 = new Chef("Nour", Chef.expertise.MULTI_CUISINE_EXPERTISE,5);
        m.addAvailableChefs(chef1, chef2, chef3, chef4, chef5);

    }



    @Given("Chef Alice specializes in pastry with a workload of {int}")
    public void chef_alice_specializes_in_pastry_with_a_workload_of(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs().get(0);
        actualChef.setWorkLoad(int1);
        String expectedName = "Sarah";
        int expectedWorkLoad  = int1;

        assertEquals(expectedName, actualChef.getName());
        assertEquals(expectedWorkLoad, actualChef.getWorkLoad());
    }
    //create task class and implement it
    @Given("the task {string} requires pastry expertise")
    public void the_task_requires_pastry_expertise(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I assign the task")
    public void i_assign_the_task() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("it should be assigned to Chef Alice")
    public void it_should_be_assigned_to_chef_alice() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("Chef Bob specializes in grilling with a workload of {int}")
    public void chef_bob_specializes_in_grilling_with_a_workload_of(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("the task {string} requires grilling expertise")
    public void the_task_requires_grilling_expertise(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("it should be assigned to Chef Bob")
    public void it_should_be_assigned_to_chef_bob() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Given("all chefs have full workloads")
    public void all_chefs_have_full_workloads() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("I attempt to assign the task")
    public void i_attempt_to_assign_the_task() {
        // Write code here that turns the phrase above into concrete actions

    }






}
