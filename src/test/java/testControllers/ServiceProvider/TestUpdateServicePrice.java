package testControllers.ServiceProvider;

import Exceptions.UserNotFoundException;
import controllers.ServiceProviderControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;
import static org.junit.Assert.*;

public class TestUpdateServicePrice {
    ServiceProvider serviceProvider;
    String newPrice;
    @When("Service Provider Who's Want to Change His Service Price is {string}")
    public void serviceProviderWhoSWantToChangeHisServicePriceIs(String string) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        serviceProvider= (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);

    }
    @When("Want To Change The Price To {string}")
    public void wantToChangeThePriceTo(String string) {
        // Write code here that turns the phrase above into concrete actions
newPrice=string;
    }
    @Then("The Price Will Be Updated Successfully")
    public void thePriceWillBeUpdatedSuccessfully() {
        ServiceProviderControl.editServicePrice(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider),
                newPrice);
        double actualPrice = Double.parseDouble(ServiceProviderControl.getServicePrice(ServiceProviderControl.getServiceFromServiceProvider(serviceProvider)));
        double expectedPrice = Double.parseDouble(newPrice);
       assertEquals(actualPrice,expectedPrice,3);


    }
}
