package testControllers.AdminServiceProviderManagement;

import exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;

import java.util.List;

public class TestBookedDateForServiceProvider {
    ServiceProvider serviceProvider;
    String bookedDate;
    @When("The Admin Want to Display Booked Date For service Provider {string}")
    public void theAdminWantToDisplayBookedDateForServiceProvider(String string) throws UserNotFoundException {
   serviceProvider = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @When("Service Provider has bookedDate {string}")
    public void serviceProviderHasBookedDate(String string) {
      bookedDate = string;
    }
    @Then("The bookedDate Will Appear Correctly")
    public void theBookedDateWillAppearCorrectly() {
    List<String> expectedDates = AdminControl.getBookedDatasForServiceProvider(serviceProvider);
    assert (expectedDates.get(0).equals(bookedDate));
    }

}
