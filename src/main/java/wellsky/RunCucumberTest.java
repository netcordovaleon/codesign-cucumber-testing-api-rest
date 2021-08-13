package wellsky;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        features = {"src/main/resources/wellsky"},
        glue = {"wellsky.stepDefinition"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests  {
}
