package support;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/lp_report.html", "json:target/lp_report.json"},
                glue = {"steps", "support"},
                features = {"src/test/resources/features"})
public class TestRunner {

}