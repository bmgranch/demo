package demo.tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        dryRun = false,
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"demo.tests.stepdefs","demo.tests"},
        plugin = {"pretty", "json:target/cucumber.json"},
        tags = "@sanity")
public class TestNGRunnerTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
