package testControllers.AdminEventsManagement;

import Exceptions.EventAlreadyExist;
import Exceptions.UserNotFoundException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.RegisteredEvent;
import models.ServiceProvider;
import models.User;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static controllers.AdminControl.addEventForUser;
import static models.EventPlanner.getServiceProviderByUsername;


public class TestCreateNewEvent {

    RegisteredEvent newEvent = new RegisteredEvent();
    User user;

    @When("event name is  {string}")
    public void eventNameIs(String string) {
        newEvent.setEventName(string);
    }

    @When("user who will added event to him {string}")
    public void userWhoWillAddedEventToHim(String string) throws UserNotFoundException {
        user = (User) EventPlanner.getUserByUsername(string);

    }

    @When("event date is {string}")
    public void eventDateIs(String string) {
        String[] dateString = string.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateString[2]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[0]));
        newEvent.setDate(date);

    }

    @When("the list of guests {string}")
    public void theListOfGuests(String string) {
        String[] guests = string.split(",");
        newEvent.setGuestsEmails(Arrays.stream(guests).toList());
    }

    @When("the list service Providers names {string}")
    public void theListServiceProvidersNames(String string) throws UserNotFoundException {
        List<ServiceProvider> serviceProvidersList = new ArrayList<>();
        String[] serviceProviders = string.split(",");
        for (String element : serviceProviders) {
            serviceProvidersList.add((ServiceProvider) getServiceProviderByUsername(element));
        }
        newEvent.setServiceProviders(serviceProvidersList);
    }


    @Then("added event successfully")
    public void added_event_successfully() throws EventAlreadyExist {
        int sizeBeforAdding = user.getRegisteredEvents().size();

        addEventForUser(user, newEvent);
        int sizeAfterAdding = user.getRegisteredEvents().size();

        assert (sizeAfterAdding == sizeBeforAdding + 1 && user.getRegisteredEvents().contains(newEvent));

    }

    @Then("event will not be added")
    public void event_will_not_be_added() {
        Assert.assertThrows(EventAlreadyExist.class, () -> addEventForUser(user, newEvent));
    }


}
