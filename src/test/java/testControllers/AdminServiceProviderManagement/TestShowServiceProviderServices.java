package testControllers.AdminServiceProviderManagement;

import exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;

import java.util.List;

public class TestShowServiceProviderServices {
    ServiceProvider serviceProvider;
    String serviceType;
    String serivcePrice;
    String serviceDescription;
    @When("Admin Want To Display Services For ServiceProvider {string}")
    public void adminWantToDisplayServicesForServiceProvider(String string) throws UserNotFoundException {
       serviceProvider= (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @When("Service Provider has {string} {string} {string}")
    public void serviceProviderHas(String string, String string2, String string3) {
       serviceType = string;
       serivcePrice = string2;
       serviceDescription = string3;
    }
    @Then("Services Will Display Successfully")
    public void servicesWillDisplaySuccessfully() {
       List<String> services= AdminControl.getServicesForServiceProvider(serviceProvider);
       assert (services.get(0).contains(serviceType));
       assert (services.get(0).contains(serivcePrice));
       assert (services.get(0).contains(serviceDescription));
    }

}
