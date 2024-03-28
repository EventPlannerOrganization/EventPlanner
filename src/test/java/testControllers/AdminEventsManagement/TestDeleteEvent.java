package testControllers.AdminEventsManagement;

import Exceptions.ServiceNotFoundException;
import controllers.AdminControl;
import controllers.EventsControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.RegisteredEvent;

import java.util.List;

public class TestDeleteEvent {
    List < RegisteredEvent> registeredEvent;
    @When("admin enter Event Name  {string}")
    public void adminEnterEventName(String string) {
        registeredEvent = AdminControl.searchEvents(string);
    }
    @Then("deleting will complete successfully")
    public void deletingWillCompleteSuccessfully() throws ServiceNotFoundException {
        int beforDeleting = AdminControl.getAllEventsNames().size();
        AdminControl.deleteEvent(registeredEvent.get(0));
        int afterDeleting = AdminControl.getAllEventsNames().size();
        assert (afterDeleting==beforDeleting-1);

    }
}