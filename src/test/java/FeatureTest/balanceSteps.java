package FeatureTest;

import ProductionCode.Admin;
import ProductionCode.CookReportPDF;
import ProductionCode.finance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.junit.After;
import org.junit.jupiter.api.Test;

import java.io.File;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.management.Query.times;
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
        float expectedBalance = 980f;

        assertEquals(expectedBalance, F.getBalance(), 0.01f);

    }

    @When("i request a pdf")
    public void iRequestAPdf() throws IOException {
        finance financeOBJ = new finance();
        financeOBJ.buy("pepper",2);
        CookReportPDF reportPDF1 = new CookReportPDF(financeOBJ);
        reportPDF1.generateReportPDF();
        String path = reportPDF1.pathMaker();
        File file = new File(path);
        assertTrue("File  exist ", file.exists());



    }

    @Then("i should see balance history")
    public void iShouldSeeBalanceHistory() throws IOException {
        finance financeOBJ = new finance();
        financeOBJ.buy("pepper",2);
        financeOBJ.buy("kiwi",5);
        financeOBJ.buy("Carrot",3);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String expected = today + ":\n" +
                "  pepper - Quantity: 2 - Price: $4.0\n" +
                "  kiwi - Quantity: 5 - Price: $16.0\n";
        assertEquals(expected, financeOBJ.printHistory());
    }

    @Then("i should see the pdf file")
    public void iShouldSeeThePdfFile() throws IOException {
        finance financeOBJ = new finance();
        financeOBJ.buy("pepper",2);
        CookReportPDF reportPDF1 = new CookReportPDF(financeOBJ);
        String path = reportPDF1.pathMaker();
        File file = new File(path);
        assertFalse("File does not exist",file.exists());
        reportPDF.generateReportPDF();
        assertTrue("File does exist ", file.exists());

    }


    @When("i buy product with long name")
    public void iBuyProductWithLongName() {
        F.setBudget(1000f); // Reset balance
        F.buy("this_is_a_very_long_product_name_that_will_wrap", 2); // Force wrapping

    }

    @Then("i should generate pdf with long product name")
    public void iShouldGeneratePdfWithLongProductName() {
        CookReportPDF report = new CookReportPDF(F);
        report.generateReportPDF();
        File file = new File(report.pathMaker());
        assertTrue(file.exists());
    }





}
