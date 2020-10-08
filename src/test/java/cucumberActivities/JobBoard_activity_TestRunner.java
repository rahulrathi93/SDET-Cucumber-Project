package cucumberActivities;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/Features",
	//features = "Feature",
    glue = {"stepDefinitions"},
    tags = "@JobBoard_activity",
    plugin = { "pretty", "html:target/CucumberProject/JobBoard_activityReport" },
    monochrome = true
    
 
)

public class JobBoard_activity_TestRunner {

}
