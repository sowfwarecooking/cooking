package FeatureTest;

import ProductionCode.Ingredients;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IngredientsCompatibilitySteps {
    @Given("the following available ingredients:")
    public void theFollowingAvailableIngredients(io.cucumber.datatable.DataTable dataTable) {
        Ingredients i = new Ingredients();
        List<String> myList = dataTable.asList(String.class);
        for(String s:myList){
            i.addAvailableIngredients(s);

        }
        assertTrue(i.isAvailableIngredient(myList.get(0)));

    }
//import to Customer Class Later
    @When("a customer selects {string}, {string}, and {string}")
    public void aCustomerSelectsAnd(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        i.addAvailableIngredients(string, string2, string3);
        i.addDesiredIngredients(string,string2,string3);
        assertEquals(string,i.selectedIngredients.get(0));
        assertEquals(string2,i.selectedIngredients.get(1));
        assertEquals(string3,i.selectedIngredients.get(2));
    }
    @Then("the meal request is valid")
    public void theMealRequestIsValid() {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String string = "tomato";
        String string1 = "onion";
        String string2 = "pickles";

        i.addAvailableIngredients(string,string1, string2 );
        i.addDesiredIngredients(string,string1,string2);
        assertEquals(string,i.selectedIngredients.get(0));
        assertEquals(string1,i.selectedIngredients.get(1));
        assertEquals(string2,i.selectedIngredients.get(2));
        
    }
    @Given("the following incompatible ingredient combinations:")
    public void theFollowingIncompatibleIngredientCombinations(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> myList = dataTable.asLists(String.class);
        String temp = String.valueOf(myList.get(1));
        String [] myArr = temp.split(",");
        Ingredients i = new Ingredients();
        i.addAvailableIngredients(myArr[0]);
        i.addAvailableIngredients(2,myArr[1]);
        assertEquals(myArr[0], i.availableIngredientsA.get(0));
        assertEquals(myArr[1], i.availableIngredientsB.get(0));



    }
    @When("a customer selects {string} and {string}")
    public void aCustomerSelectsAnd(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    Ingredients i = new Ingredients();
    String expected = string+" IS UNAVAILABLE\n"+string2+" IS UNAVAILABLE\n"+"Try These Instead: \n"+"def A\n"+"def A\n";
    assertEquals(expected,i.addDesiredIngredients(string,string2));

    }
    @Then("the meal request is invalid with the message {string}")
    public void theMealRequestIsInvalidWithTheMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String a = "Tomato";
        String b = "Milk";
        i.addAvailableIngredients(1,a);
        i.addAvailableIngredients(2, b);
        assertEquals("Selected Ingredients were incompatible, incompatible ingredients were removed",i.addDesiredIngredients(a,b));
    }




    }
