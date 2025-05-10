package ProductionCode;

public class Task {
    String taskName;
    Expertise taskExpertise;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Expertise getTaskExpertise() {
        return taskExpertise;
    }

    public void setTaskExpertise(Expertise taskExpertise) {
        this.taskExpertise = taskExpertise;
    }



    public Task(String name, Expertise expertise){
        this.taskName = name;
        this.taskExpertise= expertise;
    }
    public Task(){}
}
