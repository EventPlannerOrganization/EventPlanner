package testControllers.ServiceProvider;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/ServiceProvider",
        glue = "testControllers/ServiceProvider",
        snippets = CucumberOptions.SnippetType.CAMELCASE)

public class TestRunnerForUserGeneralOperations {
}