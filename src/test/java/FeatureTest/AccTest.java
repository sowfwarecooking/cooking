package FeatureTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


/**
 * The type Acc test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features="UserCases",
        glue = "FeatureTest"

)
public class AccTest {


}
