package testControllers.ServiceProvider;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;

public class TestUpdateServiceType {
    private ServiceType serviceType;

    @Given("Data Base already filled")
    public void dataBaseAlreadyFilled() throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
    }
    @When("serivce provider  who wants to update its service type is {string}")
    public void serivceProviderWhoWantsToUpdateItsServiceTypeIs(String string) throws UserNotFoundException {
        EventPlanner.setCurrentUser(EventPlanner.getServiceProviderByUsername(string));

    }

    @When("the value of updating the type is {string}")
    public void theValueOfUpdatingTheTypeIs(String string) {
      this.serviceType=ServiceType.valueOf(string);
    }
    @Then("the service type will be updated successfully")
    public void theServiceTypeWillBeUpdatedSuccessfully() {
        ServiceProvider serviceProvider= (ServiceProvider) EventPlanner.getCurrentUser();
        ServiceProviderControl.editServiceType(serviceProvider.getServices().get(0),this.serviceType);
        assert (serviceProvider.getServices().get(0).getServiceType().equals(this.serviceType));
    }
    @Then("the service type update will be fail")
    public void theServiceTypeUpdateWillBeFail() {
        ServiceProvider serviceProvider= (ServiceProvider) EventPlanner.getCurrentUser();
        Service service=new Service(ServiceType.DJ,2020,"dj");
        ServiceProviderControl.editServiceType(service,this.serviceType);
        assert (!serviceProvider.getServices().get(0).getServiceType().equals(this.serviceType));
    }

}
