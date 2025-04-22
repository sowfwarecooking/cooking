package ProductionCode;

import java.util.ArrayList;
import java.util.Collections;

public class KitchenManager {
    ArrayList<Chef> availableChefs = new ArrayList<>();

    public void addAvailableChefs(Chef ... chefs) {
        Collections.addAll(availableChefs, chefs);
    }
    public ArrayList<Chef> getAvailableChefs(){
        return availableChefs;
    }
    public String availableChefsToString(){
        String s="";
        for (Chef temp: availableChefs){
            s+= temp.getName()+"\n";
        }
        return s;

    }
}
