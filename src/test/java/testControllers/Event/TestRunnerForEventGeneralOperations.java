package testControllers.Event;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/Event",
        glue = "testControllers.Event",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForEventGeneralOperations {
}
