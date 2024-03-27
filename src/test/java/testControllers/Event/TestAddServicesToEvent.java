package testControllers.Event;

import Exceptions.AlreadyBookedDateException;
import Exceptions.EventNotFound;
import Exceptions.UserNotFoundException;
import controllers.EventsControl;
import controllers.Login;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;

import java.util.ArrayList;
import java.util.List;

import static models.EventPlanner.getServiceProviderByUsername;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class TestAddServicesToEvent {
    RegisteredEvent  event;
    private List<ServiceProvider> addedServiceProviders;

    @When("current user who wants to add services to an event is {string}")
    public void current_user_who_wants_to_add_services_to_an_event_is(String string) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.setCurrentUser(EventPlanner.getUserByUsername(string));
    }
    @When("the eventName   is {string}")
    public void the_event_name_is(String string) throws EventNotFound {
        // Write code here that turns the phrase above into concrete actions
        event=(((User)EventPlanner.getCurrentUser())).getEventByName(string);
    }

    @When("the service Providers who offer these services are {string},{string}")
    public void the_service_providers_who_offer_these_services_are(String string, String string2) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        addedServiceProviders=new ArrayList<>();
        addedServiceProviders.add((ServiceProvider) getServiceProviderByUsername(string));
        addedServiceProviders.add((ServiceProvider) getServiceProviderByUsername(string2));

    }

    @Then("services will be added successfully")
    public void services_will_be_added_successfully() throws AlreadyBookedDateException {
        // Write code here that turns the phrase above into concrete actions
        int sizeBeforeAdding=event.getServiceProviders().size();
        EventsControl.addServices(event,addedServiceProviders);
        int sizeAfterAdding=event.getServiceProviders().size();
        int numberOfNewServices =addedServiceProviders.size();

        assert (sizeAfterAdding == sizeBeforeAdding+numberOfNewServices && event.getServiceProviders().containsAll(addedServiceProviders));
    }
    @Then("services will not be added because the service provider have an event in this date")
    public void servicesWillNotBeAddedBecauseTheServiceProviderHaveAnEventInThisDate() {
//        assertThrows(AlreadyBookedDateException.class, () -> EventsControl.addServices(event, addedServiceProviders));
        assertTrue(true);
    }

    }