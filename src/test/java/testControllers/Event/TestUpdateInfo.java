package testControllers.Event;

import exceptions.*;
import controllers.EventsControl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.RegisteredEvent;
import models.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestUpdateInfo {
    private String eventName;
    private String field;
    private String value;

    @Given("Data Base already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
    }

    @When("current user who wants to update the event info is {string}")
    public void currentUserWhoWantsToUpdateTheEventInfoIs(String string) throws UserNotFoundException {
        EventPlanner.setCurrentUser(EventPlanner.getUserByUsername(string));
    }

    @When("the eventName is {string}")
    public void theEventNameIs(String string) {
        this.eventName = string;
    }

    @When("the field of updating the event is {string}")
    public void theFieldOfUpdatingTheEventIs(String string) {
        this.field = string;
    }

    @When("the value of updating the event is {string}")
    public void theValueOfUpdatingTheEventIs(String string) {
        this.value = string;
    }

    @Then("the event information will be updated successfully")
    public void theEventInformationWillBeUpdatedSuccessfully() throws EventNotFound, EventAlreadyExist, EventNotFoundException {
        User user = (User) EventPlanner.getCurrentUser();
        RegisteredEvent registeredEvent = user.getEventByName(eventName);

        if (field.equalsIgnoreCase("name")) {
            EventsControl.editEventName(user.getEventByName(eventName), value);
            assertEquals("wedding party", registeredEvent.getEventName());
        } else if (field.equalsIgnoreCase("location")) {
            EventsControl.editEventLocation(user.getEventByName(eventName), value);
            assertEquals("jenin", user.getEventByName(eventName).getLocation());
        }
    }

    @Then("the event infromation upadte will fail and Event Not Exist Exception will be thrown")
    public void theEventInfromationUpadteWillFailAndEventNotExistExceptionWillBeThrown() {
        User user = (User) EventPlanner.getCurrentUser();
        if (field.equalsIgnoreCase("name")) {
            assertThrows(EventNotFound.class, () -> EventsControl.editEventName(user.getEventByName(eventName), value));
        } else if (field.equalsIgnoreCase("location")) {
            assertThrows(EventNotFound.class, () -> EventsControl.editEventName(user.getEventByName(eventName), value));
        }
    }

}
