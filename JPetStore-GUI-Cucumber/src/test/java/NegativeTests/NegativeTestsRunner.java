package NegativeTests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin= {"pretty","html:target/HtmlReports/index.html"},
monochrome=true,
glue= {"NegativeTests"},
features="./src/test/resources/Features/NegativeTests.feature")

public class NegativeTestsRunner {

}
