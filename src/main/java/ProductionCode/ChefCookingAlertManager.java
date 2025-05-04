package ProductionCode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChefCookingAlertManager {
    private final LocalDateTime taskDateTime;
    private final DateTimeFormatter formatter;
    private final String taskName;
    private String cookingStatusMessage = "";

    // Accessor Methods
    public String getCookingStatusMessage() {
        return cookingStatusMessage;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public String getTaskName() {
        return taskName;
    }

    // Constructor with date string
    public ChefCookingAlertManager(String taskName, String taskDateTimeStr, String formatPattern) {
        this.taskName = taskName;
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.taskDateTime = LocalDateTime.parse(taskDateTimeStr, formatter);
    }

    // Constructor with LocalDateTime
    public ChefCookingAlertManager(String taskName, LocalDateTime taskDateTime, String formatPattern) {
        this.taskName = taskName;
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.taskDateTime = taskDateTime;
    }

    // Method to wait until 1-hour and 24-hour reminders are triggered
    public void waitForCookingAlerts() {
        cookingStatusMessage = "Waiting for cooking task \"" + taskName + "\" scheduled at: " + taskDateTime.format(formatter);
        System.out.println(cookingStatusMessage);

        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        if (taskDateTime.isBefore(now)) {
            cookingStatusMessage = "⚠️ The cooking task \"" + taskName + "\" (" + taskDateTime.format(formatter) + ") has already passed.";
            System.out.println(cookingStatusMessage);
            return;
        }

        boolean oneHourNoticeShown = false;
        boolean twentyFourHourNoticeShown = false;

        while (!oneHourNoticeShown) {
            now = LocalDateTime.now().withSecond(0).withNano(0);

            // 24-hour warning
            if (!twentyFourHourNoticeShown &&
                    Duration.between(now, taskDateTime).toHours() == 24 &&
                    now.getMinute() == taskDateTime.getMinute()) {

                cookingStatusMessage = "⏳ 24 hours remaining until \"" + taskName + "\": " + taskDateTime.format(formatter) + "!";
                System.out.println(cookingStatusMessage);
                twentyFourHourNoticeShown = true;
            }

            // 1-hour notice
            if (!oneHourNoticeShown &&
                    Duration.between(now, taskDateTime).toHours() == 1 &&
                    now.getMinute() == taskDateTime.getMinute()) {

                cookingStatusMessage = "🍽️ Your cooking task \"" + taskName + "\" is in 1 hour: " + taskDateTime.format(formatter) + "!";
                System.out.println(cookingStatusMessage);
                oneHourNoticeShown = true;
            }

            try {
                Thread.sleep(1000);  // Check every second
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
                break;
            }
        }
    }

    // One-second check method for test-friendly validation
    public void waitOneSecondAndStop() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        if (taskDateTime.isBefore(now)) {
            cookingStatusMessage = "⚠️ The cooking task \"" + taskName + "\" (" + taskDateTime.format(formatter) + ") has already passed.";
            System.out.println(cookingStatusMessage);
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during wait.");
            return;
        }

        now = LocalDateTime.now().withSecond(0).withNano(0);

        if (Duration.between(now, taskDateTime).toHours() == 24 &&
                now.getMinute() == taskDateTime.getMinute()) {
            cookingStatusMessage = "⏳ [Test] 24 hours remaining until \"" + taskName + "\": " + taskDateTime.format(formatter) + "!";
            System.out.println(cookingStatusMessage);
        } else if (Duration.between(now, taskDateTime).toHours() == 1 &&
                now.getMinute() == taskDateTime.getMinute()) {
            cookingStatusMessage = "🍽️ [Test] Your cooking task \"" + taskName + "\" is in 1 hour: " + taskDateTime.format(formatter) + "!";
            System.out.println(cookingStatusMessage);
        } else {
            System.out.println("✅ [Test] One-second check done. No alerts triggered.");
        }
    }
}
