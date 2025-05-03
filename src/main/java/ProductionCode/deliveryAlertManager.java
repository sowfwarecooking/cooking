package ProductionCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class deliveryAlertManager {
    String deliveryMessage = "";
    String currentDeliveryTimeString = "03-05-2025 17:39";  // Change to your desired date and time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime targetDateTime = LocalDateTime.parse(currentDeliveryTimeString, formatter);

    public void setCurrentDeliveryTimeString(String currentDeliveryTimeString) {
        this.currentDeliveryTimeString = currentDeliveryTimeString;
        targetDateTime = LocalDateTime.parse(currentDeliveryTimeString, formatter);
    }

    public String getCurrentDeliveryTimeString() {
        return currentDeliveryTimeString;
    }

    public LocalDateTime getTargetDateTime() {
        return targetDateTime;
    }
}
