package testControllers.authentication;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/authentication",
        glue = "testControllers.authentication",
        snippets = CucumberOptions.SnippetType.CAMELCASE)

public class TestRunnerForUserGeneralOperations {
}
