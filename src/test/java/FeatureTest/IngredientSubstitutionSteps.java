package FeatureTest;

import ProductionCode.Chef;
import ProductionCode.IngredientSubManager;
import ProductionCode.Ingredients;
import ProductionCode.Order;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IngredientSubstitutionSteps {
    Order myOrder = new Order();
    IngredientSubManager i = new IngredientSubManager();
    Chef c = new Chef("chefUser");

    void initializeOrder(){
        i.setAlternativeVeganIngredients("Vegan Meat");
        i.setRestrictedVeganIngredients("Beef");
        myOrder.setDietaryPreference("Vegan");
        myOrder.setIngredients("Beef","RICE","milk");
        i.setOrder(myOrder);
    }
    public void resetState() {
        i = new IngredientSubManager();
        myOrder = new Order();
        i.reset();
    }
    @Given("the user follows a specific diet type \\(e.g., VEGAN, KETO, LOW_CARB, VEGETARIAN, GLUTEN_FREE)")
    public void theUserFollowsASpecificDietTypeEGVEGANKETOLOWCARBVEGETARIANGLUTENFREE() {
        // Write code here that turns the phrase above into concrete actions

        initializeOrder();
        assertTrue(i.hasADiet());


        IngredientSubManager m = new IngredientSubManager();
        String s = "abc";
        m.setRestrictedLowCarbIngredients(s);
        m.setRestrictedKetoIngredients(s);
        m.setRestrictedVeganIngredients(s);
        m.setRestrictedGlutenFreeIngredients(s);
        m.setRestrictedVegetarianIngredients(s);
        assertTrue(m.restrictedLowCarbIngredients.contains(s));
        assertTrue(m.restrictedKetoIngredients.contains(s));
        assertTrue(m.restrictedVeganIngredients.contains(s));
        assertTrue(m.restrictedGlutenFreeIngredients.contains(s));
        assertTrue(m.restrictedVegetarianIngredients.contains(s));

        m.setAlternativeVeganIngredients(s);
        m.setAlternativeGlutenFreeIngredients(s);
        m.setAlternativeVegetarianIngredients(s);
        m.setAlternativeLowCarbIngredients(s);
        m.setAlternativeKetoIngredients(s);
        assertTrue(m.alternativeVeganIngredients.contains(s));
        assertTrue(m.alternativeGlutenFreeIngredients.contains(s));
        assertTrue(m.alternativeVegetarianIngredients.contains(s));
        assertTrue(m.alternativeLowCarbIngredients.contains(s));
        assertTrue(m.alternativeKetoIngredients.contains(s));
        assertTrue(m.isRestrictedIngredient(s));
        ArrayList<String>arr = new ArrayList<>();
        arr.add(s);
        assertTrue(m.arrContainsGluten(arr));
        assertTrue(m.arrContainsNonKeto(arr));
        assertTrue(m.arrContainsNonLowCarb(arr));
        assertTrue(m.arrContainsNonVegetarian(arr));
        assertTrue(m.arrContainsNonVegan(arr));

        assertEquals(s, m.containsGlutenAsString(arr));
        assertEquals(s, m.containsNonLowCarbAsString(arr));
        assertEquals(s, m.containsNonKetoAsString(arr));
        assertEquals(s, m.containsNonVeganAsString(arr));
        assertEquals(s, m.containsNonVegetarianAsString(arr));
    }
    @Given("the user inputs a recipe containing restricted ingredients")
    public void theUserInputsARecipeContainingRestrictedIngredients() {
        // Write code here that turns the phrase above into concrete actions

        i.restrictedKetoIngredients.add("bread");
        ArrayList<String>tempSelection = new ArrayList<>();
        tempSelection.add("BREAD");
        assertTrue(i.selectionContainsRestrictions(tempSelection));
    }
    @When("the system processes the recipe")
    public void theSystemProcessesTheRecipe() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager x = new IngredientSubManager();
        String s = "Tuna";
        String s2 = "Milk";
        x.submitOrderForSubReview(s,s2);
        assertEquals(s, x.myOrderForSubRev.get(0));
        assertEquals(s2, x.myOrderForSubRev.get(1));

    }
    @Then("the system suggests suitable alternatives that adhere to the user's dietary restrictions")
    public void theSystemSuggestsSuitableAlternativesThatAdhereToTheUserSDietaryRestrictions() {
        // Write code here that turns the phrase above into concrete actions
        IngredientSubManager z = i;

        System.out.println(z.suggestAlternativeIngredients(myOrder));
        assertNotNull(z.suggestAlternativeIngredients(myOrder));
        //
        myOrder = new Order();
        z.setAlternativeVegetarianIngredients("Vegetarian Meat");
        z.setRestrictedVegetarianIngredients("Beef");
        myOrder.setDietaryPreference("Vegetarian");
        myOrder.setIngredients("Beef","RICE","milk");
        z.setOrder(myOrder);
        String expected = "beef isn't vegetarian, you can try vegetarian meat instead.\n";
        String actual =z.suggestAlternativeIngredients(myOrder);
        assertEquals(expected, actual);
        //
        myOrder = new Order();
        z.setAlternativeKetoIngredients("Keto x");
        z.setRestrictedKetoIngredients("Rice");
        myOrder.setDietaryPreference("Keto");
        myOrder.setIngredients("Beef","RICE","milk");
        z.setOrder(myOrder);
        expected = "rice isn't keto, you can try keto x instead.\n";
        actual =z.suggestAlternativeIngredients(myOrder);
        assertEquals(expected, actual);
        //
        myOrder = new Order();
        z.setAlternativeLowCarbIngredients("LowCarb x");
        z.setRestrictedLowCarbIngredients("Rice");
        myOrder.setDietaryPreference("low carb");
        myOrder.setIngredients("Beef","RICE","milk");
        z.setOrder(myOrder);
        expected = "rice isn't low_carb, you can try lowcarb x instead.\n";
        actual =z.suggestAlternativeIngredients(myOrder);
        assertEquals(expected, actual);
        //
        myOrder = new Order();
        z.setAlternativeGlutenFreeIngredients("Gluten Free x");
        z.setRestrictedGlutenFreeIngredients("milk");
        myOrder.setDietaryPreference("gluten free");
        myOrder.setIngredients("Beef","RICE","milk");
        z.setOrder(myOrder);
          expected = "milk isn't gluten_free, you can try gluten free x instead.\n";
        actual =z.suggestAlternativeIngredients(myOrder);
        assertEquals(expected, actual);

    }

    @Given("the user indicates an ingredient is unavailable")
    public void theUserIndicatesAnIngredientIsUnavailable() {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String str1 = "cheese";
        String str2 = "pasta";
        String str3 = "beef";

        i.addAvailableIngredients(2, str1);
        i.addAvailableIngredients(str2);
        assertTrue(i.isAvailableIngredient(str2));
        assertFalse(i.isAvailableIngredient(str3));
    }
    @Given("the user inputs a recipe containing that ingredient")
    public void theUserInputsARecipeContainingThatIngredient() {
        // Write code here that turns the phrase above into concrete actions
        Ingredients i = new Ingredients();
        String str1 = "cheese";
        String str2 = "pasta";
        String str3 = "Onion";


        System.out.println(i.addDesiredIngredients(c, str1, str3));
        assertEquals(str1+ " IS UNAVAILABLE\n"+str3+ " IS UNAVAILABLE\n"+"Try These Instead: \n" +
                "def A\ndef A\n", i.addDesiredIngredients(c, str1, str3));
    }
    @Then("the system suggests suitable alternatives based on availability")
    public void theSystemSuggestsSuitableAlternativesBasedOnAvailability() {
        // Write code here that turns the phrase above into concrete actions
    Ingredients i = new Ingredients();
    i.addAvailableIngredients("A","B","C");
        i.addAvailableIngredients(2,"D","E","F");

        System.out.println( i.addDesiredIngredients(c, "G"));
        System.out.println( i.addDesiredIngredients(c, "A","B","L"));
    }
















}
