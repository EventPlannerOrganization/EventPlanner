package testControllers.AdminUserManagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/AdminUserManagement",
        glue = "testControllers.AdminUserManagement",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForAdminUserManagementGeneralOperations {

}
