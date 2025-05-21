package productionCode;

import java.util.ArrayList;
import java.util.Collections;

public class KitchenManager {

    String orderMessage ="";

    ArrayList<Chef> availableChefs = new ArrayList<>();

    Task currentTask = new Task();

    public void setOrderMessage(String sected) {
        this.orderMessage = sected;
    }
    public String getOrderMessage(){
     return this.orderMessage;
    }


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

    /***
     * @date 22/04/2025
     * Returns a string representation of all available chefs' names.
     * Each chef's name is placed on a new line.
     */
    public String availableChefsToString(){
        String s="";
        for (Chef temp: availableChefs){
            s+= temp.getName()+"\n";
        }
        return s;

    }
    /***
     * @date 26/04/2025
     * Assigns the current task to the first available chef who matches
     * the required expertise and has a workload of fewer than 5 tasks.
     * If a suitable chef is found, the task is assigned and the current task is cleared.
     */
    public String assignTask() {
        for(Chef chef: availableChefs){
            if(chef.getMyExpertise()==currentTask.taskExpertise && chef.getWorkLoad()<5){
                chef.addTask(currentTask);
                currentTask = new Task();
                return "\nTask Assigned To: "+chef.getName()+"\n";
            }
        }
        return "\nNo Available Chefs At The Moment!\n";

    }
}
