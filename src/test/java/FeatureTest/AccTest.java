package FeatureTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="UserCases", // Path to your feature files
        glue = "FeatureTest", // Package where step definitions are
        plugin = {
                "pretty", // Pretty console output
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json" // JSON report for CI tools
        },
        monochrome = true, // Clean console output
        tags = "@smoke" // Optional: only run tests with @smoke tag
)
public class AccTest {
        // This class should be empty - it's just a test runner
}