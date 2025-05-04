package FeatureTest;

import ProductionCode.Chef;
import ProductionCode.ChefCookingAlertManager;
import ProductionCode.DeliveryAlertManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChefCookingNotificationsSteps {
    @Given("a cooking task {string} is scheduled for tomorrow")
    public void a_cooking_task_is_scheduled_for_tomorrow(String string) {
        // Write code here that turns the phrase above into concrete actions
        String targetDateTimeStr = "01-05-2025 22:10";
        String formatPattern = "dd-MM-yyyy HH:mm";

       ChefCookingAlertManager m = new ChefCookingAlertManager(string, targetDateTimeStr, formatPattern);

        String expected = "2025-05-01T22:10";
        assertEquals(expected,String.valueOf(m.getTaskDateTime()) );
        assertEquals(string,m.getTaskName());
    }
    @When("the system checks cooking reminders")
    public void the_system_checks_cooking_reminders() {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now();
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager("Boil Eggs", time, formatPattern);

        String timeString = time.format(formatter);
        String expected = "";
        String actual = m.getCookingStatusMessage();
        assertEquals(expected, actual);
    }
    @Then("a 24hour cooking reminder for {string} is sent")
    public void a_24hour_cooking_reminder_for_is_sent(String string) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(24);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager(string,time, formatPattern);
        m.waitOneSecondAndStop();

        String timeString = time.format(formatter);
        String expected= "⏳ [Test] 24 hours remaining until \""+string+"\": "+timeString+"!";
        String actual = m.getCookingStatusMessage();
        assertEquals(expected, actual);


    }
    @Given("a cooking task {string} is scheduled for {int} hour from now")
    public void a_cooking_task_is_scheduled_for_hour_from_now(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager(string,time, formatPattern);

        String expected = time.format(formatter);
        String actual = m.getTaskDateTime().format(formatter);
        assertEquals(expected, actual);
    }
    @Then("a 1hour cooking reminder for {string} is sent")
    public void a_1hour_cooking_reminder_for_is_sent(String string) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager(string,time, formatPattern);
        m.waitOneSecondAndStop();
        String timeString = time.format(formatter);
        String expected = "🍽️ [Test] Your cooking task \""+string+"\" is in 1 hour: "+timeString+"!";
        String actual = m.getCookingStatusMessage();
        assertEquals(expected, actual);
    }
    @Given("a cooking task {string} was scheduled for yesterday")
    public void a_cooking_task_was_scheduled_for_yesterday(String string) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager(string,time, formatPattern);

        String expected = time.format(formatter);
        String actual = m.getTaskDateTime().format(formatter);
        assertEquals(expected, actual);
    }
    @Then("a past-task warning for {string} is sent")
    public void a_past_task_warning_for_is_sent(String string) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().minusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        ChefCookingAlertManager m = new ChefCookingAlertManager(string,time, formatPattern);
        m.waitForCookingAlerts();
        String timeString = time.format(formatter);
        String expected = "⚠️ The cooking task \""+string+"\" ("+timeString+") has already passed.";
        String actual = m.getCookingStatusMessage();
        assertEquals(expected, actual);
    }





}
