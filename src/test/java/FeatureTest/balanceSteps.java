package FeatureTest;

import ProductionCode.Admin;
import ProductionCode.CookReportPDF;
import ProductionCode.finance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class balanceSteps {
    finance F = new finance();
    CookReportPDF reportPDF = new CookReportPDF(F);
    public balanceSteps() throws IOException {
    }


    @Given("i am a admin")
    public void iAmAAdmin() {
        Admin admin = new Admin("admin");
        assertNotNull(admin);

        // Ensure F is properly initialized, as it seems to be the "account" or "finance" object
        assertNotNull(F);
        assertEquals(1000f, F.getBalance(), 0.01f); // Check the balance is initially 1000
    }



    @When("i buy product")
    public void iBuyProduct() {
        F.buy("pepper",2);
        System.out.println(F.getBalance());
    }

    @Then("i should see balance")
    public void iShouldSeeBalance() {
        assertEquals(996f, F.getBalance(), 0.01f);
    }

    @When("i buy product multiple times")
    public void iBuyProductMultipleTimes() {
        F.setBudget(1000f);
        System.out.println(F.getBalance());
        F.buy("pepper",2);
        F.buy("kiwi",5);
        F.buy("Carrot",3);
        System.out.println(F.getBalance());

    }

    @Then("i should see total balance")
    public void iShouldSeeTotalBalance() {
        float expectedBalance = 981f;

        assertEquals(expectedBalance, F.getBalance(), 0.01f);

    }

    @When("i request a pdf")
    public void iRequestAPdf() {

    }

    @Then("i should see balance history")
    public void iShouldSeeBalanceHistory() throws IOException {
        finance financeOBJ = new finance();
        financeOBJ.buy("pepper",2);
        financeOBJ.buy("kiwi",5);
        financeOBJ.buy("Carrot",3);
        System.out.println(financeOBJ.printHistory());
        String expicted = "13-05-2025:\n" +
                "  pepper - Quantity: 2 - Price: $4.0\n" +
                "  kiwi - Quantity: 5 - Price: $15.0\n";
        assertEquals(expicted, financeOBJ.printHistory());
    }

    @Then("i should see the pdf file")
    public void iShouldSeeThePdfFile() {
        String path = "reports/test.pdf";
        File file = new File(path);
        assertFalse("File does not exist",file.exists());
        reportPDF.generateReportPDF(path);
        assertTrue("File does exist ", file.exists());

    }
}
