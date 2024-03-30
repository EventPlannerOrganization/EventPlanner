package testControllers.ServiceProvider;

import exceptions.UserNotFoundException;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

public class TestShowService {
    ServiceProvider serviceProvider;
    @When("Service provider who is want to show his service is {string}")
    public void serviceProviderWhoIsWantToShowHisServiceIs(String string) throws UserNotFoundException {
       serviceProvider = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @Then("The Service Will Display Correctly")
    public void theServiceWillDisplayCorrectly() {
      List< Service> serviceList = new ArrayList<>();
      serviceList.add(new Service(ServiceType.CLEANING,3200,"tesing"));
        List<Service> serviceProvdierServices = ServiceProviderControl.getServiceProviderServices(serviceProvider);
        assertEquals(serviceList.get(0).toString(), serviceProvdierServices.get(0).toString());
    }

    @When("PackageProvider who is want to show his services is {string}")
    public void packageProviderWhoIsWantToShowHisServicesIs(String string) throws UserNotFoundException {
        serviceProvider = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @Then("The Servicess Will Display Correctly")
    public void theServicessWillDisplayCorrectly() {
        List< Service> serviceList = new ArrayList<>();
        serviceList.add(  new Service(ServiceType.PHOTOGRAPHY,3200,"tesing"));
        serviceList.add(new Service(ServiceType.SECURITY,3200,"tesing"));
        List<Service> serviceProvdierServices = ServiceProviderControl.getServiceProviderServices(serviceProvider);
        assertEquals(serviceProvdierServices.get(0).toString(),serviceList.get(0).toString());
        assertEquals(serviceProvdierServices.get(1).toString(),serviceProvdierServices.get(1).toString());
    }


}
