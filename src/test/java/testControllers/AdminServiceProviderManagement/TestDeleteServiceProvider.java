package testControllers.AdminServiceProviderManagement;

import Exceptions.EmptyList;
import Exceptions.ServiceNotFoundException;
import Exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;
import java.util.List;
import static org.junit.Assert.assertThrows;

public class TestDeleteServiceProvider {
    private String deletedUsername;
    @When("admin enter username  {string}")
    public void adminEnterUsername(String string) {
        // Write code here that turns the phrase above into concrete actions
       deletedUsername = string;
    }
    @Then("deleteing will not be complete")
    public void deleteingWillNotBeComplete() {
        assertThrows(UserNotFoundException.class, () -> EventPlanner.getServiceProviderByUsername(deletedUsername));
    }

    @Then("Service Provider will be deleted successfully")
    public void serviceProviderWillBeDeletedSuccessfully() throws UserNotFoundException, EmptyList, ServiceNotFoundException {
        List<ServiceProvider> serviceProviderbeforeDeleteing = EventPlanner.getServiceProviders();
        ServiceProvider deletedUser =(ServiceProvider)EventPlanner.getServiceProviderByUsername(deletedUsername);
        AdminControl.deleteServiceProvider(deletedUser);
        List<ServiceProvider> serviceProviderAfterDeleteing = EventPlanner.getServiceProviders();
        boolean condition=(serviceProviderbeforeDeleteing.contains(deletedUser)
                &&!serviceProviderAfterDeleteing.contains(deletedUser)
                &&serviceProviderbeforeDeleteing.size()==serviceProviderAfterDeleteing.size()+1
        );
        assert (condition);
    }

}
