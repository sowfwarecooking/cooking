package ProductionCode;

import java.io.IOException;

public class finance {
    private float budget;
    Suppliers suppliers;

    public finance(int budget) {
        this.budget = budget;
    }
    public finance() throws IOException {
        this.budget = 1000f;
        this.suppliers = new Suppliers();
    }


    public void setBudget(float budget) {
        this.budget = budget;
    }

    public void buy(String ingredient, int quantity) {
        float price = suppliers.getBest(ingredient, quantity); // Assuming this method returns the price
        if (price != 0) {
            this.budget -= price * quantity; // Deduct the total cost (price * quantity) from the budget
        }
    }


    public float getBalance() {
        return this.budget;
    }
}
