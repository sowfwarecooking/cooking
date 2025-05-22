package FeatureTest;

import productionCode.Chef;
import productionCode.Expertise;
import productionCode.KitchenManager;
import productionCode.Task;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ChefNotificationSteps {

    KitchenManager m;

    @Before
    public void setup (){
        m = new KitchenManager();
        Chef chef1 = new Chef("Sarah", Expertise.PASTRY_EXPERTISE,1);

        m.addAvailableChefs(chef1);

    }

    @Given("I have assigned cooking tasks")
    public void i_have_assigned_cooking_tasks() {
        // Write code here that turns the phrase above into concrete actions

        Task t1 = new Task("Task 1", Expertise.PASTRY_EXPERTISE);
        m.setCurrentTask(t1.getTaskName(),t1.getTaskExpertise());
        m.assignTask();


        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        String expected = "";
        String actual = actualChef.printCurrentTasks();
        assertEquals(1, actualChef.getMyTasks().size());
        assertEquals(actualChef.getMyTasks().getFirst().getTaskName(), t1.getTaskName());
        assertEquals(actualChef.getMyTasks().getFirst().getTaskExpertise(), t1.getTaskExpertise());

    }
    @When("I open the task list")
    public void i_open_the_task_list() {
        // Write code here that turns the phrase above into concrete actions
        Task t1 = new Task("Task 1", Expertise.PASTRY_EXPERTISE);
        m.setCurrentTask(t1.getTaskName(),t1.getTaskExpertise());
        m.assignTask();


        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        assertNotNull(actualChef.printCurrentTasks());


    }
    @Then("I should see all my assigned tasks")
    public void i_should_see_all_my_assigned_tasks() {
        // Write code here that turns the phrase above into concrete actions


        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        m.setCurrentTask("Task 1", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 2", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 3", Expertise.PASTRY_EXPERTISE);
        m.assignTask();


        String expected = "Tasks: \n" + "Task 1\n" + "Task 2\n"+"Task 3";

        String actual = actualChef.printCurrentTasks();

        assertEquals(expected, actual);
    }
    @Given("I have completed a cooking task")
    public void i_have_completed_a_cooking_task() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        m.setCurrentTask("Task 1", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 2", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 3", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        actualChef.completedTask("Task 1");
        assertEquals(2, actualChef.getMyTasks().size());

        String expected = "Tasks: \n" + "Task 2\n" + "Task 3";
        String actual = actualChef.printCurrentTasks();

        assertEquals(expected, actual);

    }
    @When("I mark the task as done")
    public void i_mark_the_task_as_done() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        m.setCurrentTask("Task 1", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 2", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        m.setCurrentTask("Task 3", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        assertEquals("Task 1 COMPLETED!\n" , actualChef.completedTask("Task 1"));

    }
    @Then("the task should be removed from my task list")
    public void the_task_should_be_removed_from_my_task_list() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        m.setCurrentTask("Task 1", Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        actualChef.completedTask("task 1");

        assertTrue(actualChef.getMyTasks().isEmpty());

    }
    @Given("a new cooking task is assigned to me")
    public void a_new_cooking_task_is_assigned_to_me() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        m.setCurrentTask("Task1", Expertise.PASTRY_EXPERTISE);
        m.assignTask();
        assertEquals(2, actualChef.getWorkLoad());

    }
    @When("the task is created")
    public void the_task_is_created() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        String taskName = "Task 1";
        m.setCurrentTask(taskName, Expertise.PASTRY_EXPERTISE);
        m.assignTask();
        assertEquals(taskName, actualChef.getMyTasks().getFirst().getTaskName());

    }
    @Then("I should receive a notification")
    public void i_should_receive_a_notification() {
        // Write code here that turns the phrase above into concrete actions
        Chef actualChef = m.getAvailableChefs()
                .stream()
                .filter(c -> c.getName().equals("Sarah"))
                .findFirst()
                .orElseThrow();

        actualChef.clearCurrentTasks();
        actualChef.setWorkLoad(1);
        String taskName1 = "Task 1";
        m.setCurrentTask(taskName1, Expertise.PASTRY_EXPERTISE);
        m.assignTask();
        String actual = actualChef.getNotificationMessage();
        String expected = "New Task Added: "+taskName1+"\n";
        assertEquals(expected, actual);

        String taskName2 = "Task 2";

        m.setCurrentTask(taskName2, Expertise.PASTRY_EXPERTISE);
        m.assignTask();

        actual = actualChef.getNotificationMessage();
        expected = "New Task Added: "+taskName2+"\n";
        assertEquals(expected, actual);




    }



}
