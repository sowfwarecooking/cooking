package FeatureTest;

import ProductionCode.Chef;
import ProductionCode.Expertise;
import ProductionCode.KitchenManager;
import ProductionCode.Task;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class assignTasksToChefsSteps {

    KitchenManager m;

    @Before
    public void setup (){
         m = new KitchenManager();
        Chef chef1 = new Chef("Sarah", Expertise.PASTRY_EXPERTISE,1);
        Chef chef2 = new Chef("Ahmad", Expertise.GRILLING_EXPERTISE,2);
        Chef chef3 = new Chef("Yousef", Expertise.SALAD_EXPERTISE,3);
        Chef chef4 = new Chef("Khaled", Expertise.SEAFOOD_EXPERTISE,4);
        Chef chef5 = new Chef("Nour", Expertise.MULTI_CUISINE_EXPERTISE,5);
        m.addAvailableChefs(chef1, chef2, chef3, chef4, chef5);

    }



    @Given("Chef Alice specializes in pastry with a workload of {int}")
    public void chef_alice_specializes_in_pastry_with_a_workload_of(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

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
        m.setCurrentTask("COOKING PASTRIES", Expertise.PASTRY_EXPERTISE);
        Task ex = m.getCurrentTask();
        assertEquals("COOKING PASTRIES", ex.getTaskName());
        assertEquals(Expertise.PASTRY_EXPERTISE, ex.getTaskExpertise());
    }
    @When("I assign the task")
    public void i_assign_the_task() {
        // Write code here that turns the phrase above into concrete actions

        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();
        int firstWorkLoad = actualChef.getWorkLoad();

        m.setCurrentTask("COOKING PASTRIES", Expertise.PASTRY_EXPERTISE);
        String expectedMessage = "\nTask Assigned To: Sarah\n";
        String actualMsg = m.assignTask();
        assertEquals(expectedMessage, actualMsg);


        assertEquals(1, actualChef.getMyTasks().size());

        expectedMessage = "Tasks: \n" + "COOKING PASTRIES";
        actualMsg = actualChef.printCurrentTasks();
        assertEquals(expectedMessage, actualMsg);

      assertEquals(firstWorkLoad+1, actualChef.getWorkLoad());

      assertNull(m.getCurrentTask().getTaskExpertise());

      assertNull(m.getCurrentTask().getTaskName());


    }
    @Then("it should be assigned to Chef Alice")
    public void it_should_be_assigned_to_chef_alice() {
        // Write code here that turns the phrase above into concrete actions
//done in previous
    }
    @Given("Chef Bob specializes in grilling with a workload of {int}")
    public void chef_bob_specializes_in_grilling_with_a_workload_of(Integer int1) {
        // Write code here that turns the phrase above into concrete actions

        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Ahmad"))
                .findFirst()
                .orElseThrow();

        int firstWorkLoad = actualChef.getWorkLoad();
        m.setCurrentTask("Grilling Patty", Expertise.GRILLING_EXPERTISE);
        String actual = m.assignTask();
        String expected = "\nTask Assigned To: Ahmad\n";

        assertEquals(expected, actual);

        m.setCurrentTask("Roasting Chicken", Expertise.GRILLING_EXPERTISE);
        m.assignTask();

        assertEquals("Tasks: \n" + "Grilling Patty\n" + "Roasting Chicken", actualChef.printCurrentTasks());

        assertEquals(firstWorkLoad+2, actualChef.getWorkLoad());
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
