package testControllers.ServiceProvider;

import exceptions.UserNotFoundException;
import controllers.ServiceProviderControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import static org.junit.Assert.*;
public class TestUpdateServiceDescription {
ServiceProvider serviceProvider;
String newDesc;
    @When("Service Provider Who's Want to Change His Service Description is {string}")
    public void serviceProviderWhoSWantToChangeHisServiceDescriptionIs(String string) throws UserNotFoundException {
      serviceProvider= (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @When("Want To Change The Description To {string}")
    public void wantToChangeTheDescriptionTo(String string) {
        // Write code here that turns the phrase above into concrete actions
        newDesc=string;
    }
    @Then("The Description Will Be Updated Successfully")
    public void theDescriptionWillBeUpdatedSuccessfully() {
      Service service= ServiceProviderControl.getServiceFromServiceProvider(serviceProvider);
      String oldDesc=ServiceProviderControl.getServiceDescription(service);
      ServiceProviderControl.editServiceDescription(service,newDesc);
        assertEquals(ServiceProviderControl.
                getServiceFromServiceProvider(serviceProvider).
                getDescription(), newDesc);
        assertNotEquals(ServiceProviderControl.getServiceDescription(service), oldDesc);
    }

}
