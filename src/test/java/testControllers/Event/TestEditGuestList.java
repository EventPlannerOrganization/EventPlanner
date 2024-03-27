package testControllers.Event;

import Exceptions.EventNotFound;
import Exceptions.UserNotFoundException;
import controllers.EventsControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class TestEditGuestList {

    private User currentUser;
    private String eventName;
    private List<String> emails;

    @When("current user is {string}")
    public void currentUserWhoWantsToDeleteGuestEmailsIs(String string) throws UserNotFoundException {
        currentUser = (User) EventPlanner.getUserByUsername(string);
        EventPlanner.setCurrentUser(currentUser);
    }

    @When("event name is {string}")
    public void eventNameIs(String string) {
        this.eventName = string;
    }

    @When("guset emails that the user wants to add is {string},{string}")
    public void gusetEmailsThatTheUserWantsToAddIs(String string, String string2) throws EventNotFound {
        emails = new ArrayList<>();
        this.emails.add(string);
        this.emails.add(string2);
        EventsControl.addNewGuests(currentUser.getEventByName(eventName), emails);

    }

    @When("guset emails that the user wants delete is {string},{string}")
    public void gusetEmailsThatTheUserWantsDeleteIs(String string, String string2) {
        emails = new ArrayList<>();
        emails.add(string);
        emails.add(string2);
    }

    @Then("emails will be added successfylly")
    public void emailsWillBeAddedSuccessfylly() throws EventNotFound {
        assert (currentUser.getEventByName(eventName).getGuestsEmails().contains(emails.get(0)));
        assert (currentUser.getEventByName(eventName).getGuestsEmails().contains(emails.get(1)));

    }

    @Then("emails will be deleted successfylly")
    public void emailsWillBeDeletedSuccessfylly() throws EventNotFound {
        EventsControl.deleteGuest(emails.get(0), currentUser.getEventByName(eventName));
        EventsControl.deleteGuest(emails.get(1), currentUser.getEventByName(eventName));
        assert (!currentUser.getEventByName(eventName).getGuestsEmails().contains(emails.get(0)));
        assert (!currentUser.getEventByName(eventName).getGuestsEmails().contains(emails.get(1)));

    }


}
