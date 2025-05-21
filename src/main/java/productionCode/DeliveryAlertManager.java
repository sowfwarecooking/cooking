package productionCode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * The {@code DeliveryAlertManager} class manages and monitors scheduled delivery times.
 * It provides alert messages when a delivery is 24 hours or 1 hour away and can simulate
 * a one-second check for testing purposes.
 * <p>
 * The class uses {@link LocalDateTime} for handling delivery scheduling and supports
 * both string and object initialization.
 * </p>
 */
public class DeliveryAlertManager {
    private final LocalDateTime deliveryDateTime;
    private final DateTimeFormatter formatter;
    String deliveryStatusMessage = "";

    public String getDeliveryStatusMessage() {
        return deliveryStatusMessage;
    }



    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    /***
     * Constructs a {@code DeliveryAlertManager} with a delivery time provided as a string.
     *
     * @param deliveryDateTimeStr the delivery date and time as a string
     * @param formatPattern       the pattern used to parse the delivery time string
     */
    public DeliveryAlertManager(String deliveryDateTimeStr, String formatPattern) {
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.deliveryDateTime = LocalDateTime.parse(deliveryDateTimeStr, formatter);
    }
    /***
     * Constructs a {@code DeliveryAlertManager} with a delivery time provided as a {@link LocalDateTime} object.
     *
     * @param deliveryDateTime the delivery date and time
     * @param formatPattern    the pattern used for formatting
     */
    public DeliveryAlertManager(LocalDateTime deliveryDateTime, String formatPattern) {
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.deliveryDateTime = deliveryDateTime;
    }
    /***
     * Continuously checks the system time and prints alerts when the delivery time is
     * 24 hours or 1 hour away. If the delivery time has already passed, a warning is displayed.
     * <p>
     * This method runs an infinite loop until the 1-hour warning has been shown.
     * </p>
     */
    public void waitForDeliveryAlerts() {
        deliveryStatusMessage = "Waiting for delivery scheduled at: " + deliveryDateTime.format(formatter);

        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        if (deliveryDateTime.isBefore(now)) {
            deliveryStatusMessage = "⚠️ The scheduled delivery time (" + deliveryDateTime.format(formatter) + ") has already passed.";
            return;
        }

        boolean oneHourNoticeShown = false;
        boolean twentyFourHourNoticeShown = false;

        while (!oneHourNoticeShown) {
            now = LocalDateTime.now().withSecond(0).withNano(0);

            // 24-hour warning
            if (!twentyFourHourNoticeShown &&
                    Duration.between(now, deliveryDateTime).toHours() == 24 &&
                    now.getMinute() == deliveryDateTime.getMinute()) {

                deliveryStatusMessage = "⏳ 24 hours remaining until delivery: " + deliveryDateTime.format(formatter) + "!";
                System.out.println("⏳ 24 hours remaining until delivery: " + deliveryDateTime.format(formatter) + "!");
                twentyFourHourNoticeShown = true;
            }

            // 1-hour notice
            if (!oneHourNoticeShown &&
                    Duration.between(now, deliveryDateTime).toHours() == 1 &&
                    now.getMinute() == deliveryDateTime.getMinute()) {

                deliveryStatusMessage = "📦 Your delivery is 1 hour away: " + deliveryDateTime.format(formatter) + "!";
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
     * Performs a one-second wait and checks if either the 24-hour or 1-hour alert
     * should be triggered. This method is primarily intended for testing purposes.
     * <p>
     * Displays a console message based on the current proximity to the delivery time.
     * </p>
     */
    public void waitOneSecondAndStop() {
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        if (deliveryDateTime.isBefore(now)) {
            deliveryStatusMessage = "⚠️ The scheduled delivery time (" + deliveryDateTime.format(formatter) + ") has already passed.";
            System.out.println(deliveryStatusMessage);
            return;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during wait.");
            return;
        }

        now = LocalDateTime.now().withSecond(0).withNano(0);

        if (Duration.between(now, deliveryDateTime).toHours() == 24 &&
                now.getMinute() == deliveryDateTime.getMinute()) {
            deliveryStatusMessage = "⏳ [Test] 24 hours remaining until delivery: " + deliveryDateTime.format(formatter) + "!";
            System.out.println(deliveryStatusMessage);
        } else if (Duration.between(now, deliveryDateTime).toHours() == 1 &&
                now.getMinute() == deliveryDateTime.getMinute()) {
            deliveryStatusMessage = "📦 [Test] Your delivery is 1 hour away: " + deliveryDateTime.format(formatter) + "!";
            System.out.println(deliveryStatusMessage);
        } else {
            System.out.println("✅ [Test] One-second check done. No alerts triggered.");
        }
    }
}
