package testControllers.Event;

import Exceptions.EventNotFound;
import Exceptions.ServiceNotFoundException;
import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import controllers.EventsControl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;

public class TestDeleteServiceFromEvent {

    private String serviceProviderName;
    private String eventName;



    @When("current user who wants to delete service from an event is {string}")
    public void currentUserWhoWantsToDeleteServiceFromAnEventIs(String string) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.setCurrentUser(EventPlanner.getUserByUsername(string));
    }
    @When("the eventName  is {string}")
    public void theEventNameIs(String string) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        eventName=string;
    }
    @When("the service Provider who offer this service is {string}")
    public void theServiceProviderWhoOfferThisServiceIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        serviceProviderName=string;

    }
    @Then("service will be deleted successfully")
    public void serviceWillBeDeletedSuccessfully() throws UserNotFoundException, EventNotFound, ServiceNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        User currentuser=((User)EventPlanner.getCurrentUser());
        int sizeBeforeDelete=currentuser.getEventByName(eventName).getServiceProviders().size();
        ServiceProvider serviceProvider=(ServiceProvider) EventPlanner.getServiceProviderByUsername(serviceProviderName);
        RegisteredEvent registeredEvent =currentuser.getEventByName(eventName);
        EventsControl.deleteService(registeredEvent,serviceProvider);
        int sizeAfterDelete=currentuser.getEventByName(eventName).getServiceProviders().size();
        assert (sizeBeforeDelete==sizeAfterDelete+1&&!registeredEvent.getServiceProviders().contains(serviceProvider));

    }
}
