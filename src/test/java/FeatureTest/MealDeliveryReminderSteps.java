package FeatureTest;

import ProductionCode.DeliveryAlertManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealDeliveryReminderSteps {
    @Given("a delivery is scheduled for tomorrow")
    public void a_delivery_is_scheduled_for_tomorrow() {
        // Write code here that turns the phrase above into concrete action
        String targetDateTimeStr = "01-05-2025 22:10";
        String formatPattern = "dd-MM-yyyy HH:mm";

        DeliveryAlertManager m = new DeliveryAlertManager(targetDateTimeStr, formatPattern);

        String expected = "2025-05-01T22:10";
        assertEquals(expected,String.valueOf(m.getDeliveryDateTime()) );

    }
    @Then("a 24hour reminder is sent")
    public void a_24hour_reminder_is_sent() {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(24);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);
        m.waitOneSecondAndStop();

        String timeString = time.format(formatter);
        String expected = "⏳ [Test] 24 hours remaining until delivery: "+ timeString+"!";
        String actual = m.getDeliveryStatusMessage();
        assertEquals(expected, actual);


    }
    @When("the system checks reminders")
    public void the_system_checks_reminders() {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now();
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);

        String timeString = time.format(formatter);
        String expected = "";
        String actual = m.getDeliveryStatusMessage();
        assertEquals(expected, actual);

    }

    @Then("a 1hour reminder is sent")
    public void a_1hour_reminder_is_sent() {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);
        m.waitOneSecondAndStop();
        String timeString = time.format(formatter);
        String expected = "📦 [Test] Your delivery is 1 hour away: "+timeString+"!";
        String actual = m.getDeliveryStatusMessage();
        assertEquals(expected, actual);
    }
    @Given("a delivery is scheduled for {int} hour from now")
    public void a_delivery_is_scheduled_for_hour_from_now(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);

        String expected = time.format(formatter);
        String actual = m.getDeliveryDateTime().format(formatter);
        assertEquals(expected, actual);


    }
    @Given("a delivery was scheduled for yesterday")
    public void a_delivery_was_scheduled_for_yesterday() {
        // Write code here that turns the phrase above into concrete actions
        LocalDateTime time = LocalDateTime.now().plusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);

        String expected = time.format(formatter);
        String actual = m.getDeliveryDateTime().format(formatter);
        assertEquals(expected, actual);

    }
    @Then("special message is sent")
    public void special_message_is_sent() {
        // Write code here that turns the phrase above into concrete actions

        LocalDateTime time = LocalDateTime.now().minusHours(1);
        String formatPattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        DeliveryAlertManager m = new DeliveryAlertManager(time, formatPattern);
        m.waitForDeliveryAlerts();
        String timeString = time.format(formatter);
        String expected = "⚠️ The scheduled delivery time ("+timeString+") has already passed.";
        String actual = m.getDeliveryStatusMessage();
        assertEquals(expected, actual);
    }
}
