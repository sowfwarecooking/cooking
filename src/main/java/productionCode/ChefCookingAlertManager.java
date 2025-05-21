package productionCode;

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

    /***
     * Starts waiting for the cooking alerts.
     * This method blocks and continuously checks the current time.
     * It prints and updates alert messages when:
     * - 24 hours remain until the task start.
     * - 1 hour remains until the task start.
     * If the task date-time is already past, it prints an immediate warning.
     * This method runs an internal loop, checking every second.
     * It stops after the 1-hour alert is shown or if interrupted.
     */
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

    /***
     * Performs a single 1-second wait and check for cooking alerts.
     * This method is designed for test purposes to validate alert triggering logic.
     * After sleeping for 1 second, it checks if 24-hour or 1-hour alerts should be triggered.
     * If the task time is in the past, a warning is immediately printed.
     * Prints appropriate messages based on the alert status.
     */
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
