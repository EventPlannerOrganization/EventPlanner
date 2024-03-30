package testControllers.AdminEventsManagement;


import exceptions.EventNotFoundException;
import exceptions.ServiceNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.RegisteredEvent;
import org.junit.Assert;

import java.util.List;

import static controllers.AdminControl.deleteEvent;

public class TestDeleteEvent {
    List < RegisteredEvent> registeredEvents;
    @When("admin enter Event Name  {string}")
    public void adminEnterEventName(String string) {
        registeredEvents = AdminControl.searchEvents(string);
    }
    @Then("deleting will complete successfully")
    public void deletingWillCompleteSuccessfully() throws ServiceNotFoundException, EventNotFoundException {
        int beforDeleting = AdminControl.getAllEventsNames().size();
        deleteEvent(registeredEvents.getFirst());
        int afterDeleting = AdminControl.getAllEventsNames().size();
        assert (afterDeleting==beforDeleting-1);
    }

    @Then("deleting will not complete")
    public void deletingWillNotComplete() {
        // Write code here that turns the phrase above into concrete actions
        assert(registeredEvents==null);
        RegisteredEvent doesNotExistEvent =new RegisteredEvent();
        Assert.assertThrows(EventNotFoundException.class, () -> deleteEvent(doesNotExistEvent));

    }

}