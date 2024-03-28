package testControllers.AdminEventsManagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/AdminEventsManagement",
        glue = "testControllers.AdminEventsManagement",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForAdminEventsManagement {
}
