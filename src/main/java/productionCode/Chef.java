package productionCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chef {

    ArrayList<Task> myTasks = new ArrayList<>();
    String notificationMessage="";
    private boolean loggedIn;

    public Chef(String chefUser){
        this.name = chefUser;
        this.loggedIn=true;
    }
    public Chef(String name, Expertise chefExpertise){
        this.name = name;
        this.myExpertise=chefExpertise;
        this.loggedIn=true;
    }
    public Chef(String name, Expertise chefExpertise, int WorkLoad){
        this.name = name;
        this.myExpertise=chefExpertise;
        this.workLoad = WorkLoad;
        this.loggedIn=true;

    }

    int workLoad;
    Expertise myExpertise;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public Expertise getMyExpertise() {
        return myExpertise;
    }

    public void setMyExpertise(Expertise myExpertise) {
        this.myExpertise = myExpertise;
    }

    String ingredientChangeMessage  = "";
    ArrayList<String> approvedOrder =new ArrayList<>();


    //
    public void viewCustomerProfile(CustomerProfile profile) {
        if (profile != null) {
            System.out.println("Customer: " + profile.getCustomerName());
            System.out.println("Dietary Preferences: " + profile.getDietaryPreferences());
            System.out.println("Allergies: " + profile.getAllergies());
        } else {
            System.out.println("No customer profile found.");
        }
    }
    //
    public String getIngredientChangeMessage() {
        return this. ingredientChangeMessage;
    }


    public void setIngredientChangeMessage(String s) {
        this. ingredientChangeMessage = s;
    }

    public String approveOrder(Ingredients i) {
        approvedOrder = i.getSelectedIngredients();
        return"\nOrder Approved\n";
    }
    public ArrayList<String> getApprovedOrder(){
        return approvedOrder;
    }

    public String removeIngredient(Ingredients i, String s){
        i.removeDesiredIngredients(s, this);
        this.approveOrder(i);
        return "\nChef Removed Ingredient Successfully\n";
    }
    public String addIngredient(Ingredients i, String... s){
        i.addDesiredIngredients(this, s);
        this.approveOrder(i);
        return "\nChef Added Ingredient Successfully\n";
    }
    public String subIngredient(Ingredients i, String toBeSubbed, String subbedWith){
        i.substituteDesiredIngredients(toBeSubbed, subbedWith, this);
        this.approveOrder(i);
        return "\nChef Substituted Ingredient Successfully\n";
    }
    public void addTask(Task t){
        myTasks.add(t);
        this.workLoad++;
        this.setNotificationMessage("New Task Added: "+t.getTaskName()+"\n");
    }

    private void setNotificationMessage(String s) {
        this.notificationMessage = s;
    }

    public ArrayList<Task> getMyTasks(){
        return myTasks;
    }
    public String printCurrentTasks(){
        String s = "Tasks: ";
        for(Task t: myTasks){
            s+="\n"+t.getTaskName();
        }
        return s;
    }

    public void clearCurrentTasks() {
        myTasks.clear();
    }

    public String completedTask(String s) {
        for(Task t:myTasks){
            if(s.equalsIgnoreCase(t.getTaskName())){
                this.myTasks.remove(t);
                return (t.getTaskName()+" COMPLETED!\n");
            }
        }
        return"No Such Task Found!\n";

    }

    public String getNotificationMessage() {

        return notificationMessage;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }


    public List<Meal> viewCustomerOrders(String customerUsername) {
        return new ArrayList<>(Arrays.asList(new Meal("Pizza"), new Meal("Pasta")));
    }

    public MealPlan suggestMealPlan(List<Meal> customerOrders) {
        return new MealPlan("Custom Healthy Meal Plan");
    }
}