package testControllers.AdminServiceProviderManagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/AdminServiceProviderManagement",
        glue = "testControllers.AdminServiceProviderManagement",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForAdminServiceProviderManagement {
}
