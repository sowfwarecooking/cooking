package ProductionCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Customer {

    ArrayList<String> myOrder = new ArrayList<>();




    public void setMyOrder(ArrayList<String> myOrder) {
        this.myOrder = myOrder;
    }

    public ArrayList<String> getMyOrder() {
        return myOrder;
    }
}
