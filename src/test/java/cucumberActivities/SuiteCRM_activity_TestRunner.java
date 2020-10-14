package cucumberActivities;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/Features",
	//features = "Feature",
    glue = {"stepDefinitions"},
    tags = "@SuiteCRM_activity",
    plugin = { "pretty", "html:target/CucumberProject/SuiteCRM_activityReport" },
    monochrome = true
    
 
)

public class SuiteCRM_activity_TestRunner {

}
