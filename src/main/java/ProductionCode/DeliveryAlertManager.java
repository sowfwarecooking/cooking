package ProductionCode;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public DeliveryAlertManager(String deliveryDateTimeStr, String formatPattern) {
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.deliveryDateTime = LocalDateTime.parse(deliveryDateTimeStr, formatter);
    }

    public DeliveryAlertManager(LocalDateTime deliveryDateTime, String formatPattern) {
        this.formatter = DateTimeFormatter.ofPattern(formatPattern);
        this.deliveryDateTime = deliveryDateTime;
    }

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
