package BusinessFlow;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty","junit:target/XMLReports/flow.xml"
		,"html:target/HtmlReports/index.html"},
monochrome=true,
features="./src/test/resources/Features/JPetStore.feature",
glue= {"BusinessFlow"})

public class JPetStoreRunner {
	
}
