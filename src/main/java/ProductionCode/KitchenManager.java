package ProductionCode;

import java.util.ArrayList;
import java.util.Collections;

public class KitchenManager {
    ArrayList<Chef> availableChefs = new ArrayList<>();

    Task currentTask = new Task();

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String name, Expertise expertise){
        currentTask.setTaskName(name);
        currentTask.setTaskExpertise(expertise);
    }

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

    public String assignTask() {
        for(Chef chef: availableChefs){
            if(chef.getMyExpertise()==currentTask.taskExpertise && chef.getWorkLoad()<=5){
                chef.addTask(currentTask);
                currentTask = new Task();
                return "\nTask Assigned To: "+chef.getName()+"\n";
            }
        }
        return "\nNo Available Chefs At The Moment!\n";

    }
}
