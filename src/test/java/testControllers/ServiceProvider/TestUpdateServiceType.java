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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestUpdateServiceType {
    ServiceProvider serviceProvider;
    Service service;
    List<Service> serviceList =new ArrayList<>();
    ServiceType serviceType;
    String description;
    double price;

    @Given("Data Base already filled")
    public void dataBaseAlreadyFilled() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.initializeRepositoryWithData();    }
    @When("Service Provider or Package Provider Who's Want to Change His Service Type To Single Service Is {string}")
    public void serviceProviderOrPackageProviderWhoSWantToChangeHisServiceTypeToSingleServiceIs(String string) throws UserNotFoundException {
       serviceProvider = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @When("The new Service is {string} {string} {string}")
    public void theNewServiceIs(String string, String string2, String string3) {
        serviceType = ServiceType.valueOf(string);
        price = Double.parseDouble(string3);
        description = string2;
     service = new Service(serviceType,price,description);
    }
    @Then("The Service Will Update Successfully")
    public void theServiceWillUpdateSuccessfully() {
        ServiceProviderControl.changeServiceProviderService(serviceProvider,service );
        assertEquals(serviceProvider.getServices().get(0),service);
    }

    @When("Service Provider or Package Provider Who's Want to Change His Service Type To Multi Service Is {string}")
    public void serviceProviderOrPackageProviderWhoSWantToChangeHisServiceTypeToMultiServiceIs(String string) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
       serviceProvider = (ServiceProvider)EventPlanner.getServiceProviderByUsername(string);
    }

    @When("The First Service is  {string} {string} {string}")
    public void theFirstServiceIs(String string, String string2, String string3) {
        serviceType = ServiceType.valueOf(string);
        price = Double.parseDouble(string3);
        description = string2;
        serviceList.add(new Service(serviceType,price,description));

    }
    @When("The Second Service is {string} {string} {string}")
    public void theSecondServiceIs(String string, String string2, String string3) {
        serviceType = ServiceType.valueOf(string);
        price = Double.parseDouble(string3);
        description = string2;
        serviceList.add(new Service(serviceType,price,description));
    }
    @When("The Third Service is {string} {string} {string}")
    public void theThirdServiceIs(String string, String string2, String string3) {
        serviceType = ServiceType.valueOf(string);
        price = Double.parseDouble(string3);
        description = string2;
        serviceList.add(new Service(serviceType,price,description));
    }
    @Then("The List Of Services Will Update Successfully")
    public void theListOfServicesWillUpdateSuccessfully() {
        ServiceProviderControl.changePackageProviderServices(serviceProvider,serviceList);
        List<Service>ActualList = ServiceProviderControl.getServiceProviderServices(serviceProvider);
        assertEquals(ActualList.get(0),serviceList.get(0));
        assertEquals(ActualList.get(1),serviceList.get(1));
        assertEquals(ActualList.get(2),serviceList.get(2));

    }



}
