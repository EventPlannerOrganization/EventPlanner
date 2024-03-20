package testControllers.Event;

import Exceptions.EventAlreadyExist;
import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import controllers.EventsControl;
import controllers.ServiceProviderControl;
import controllers.UserControl;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;
import  org.junit.Assert.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class TestAddEvent {
    private RegisteredEvent newEvent;
    private User currentUser;
    private List<ServiceProvider> list;
    public static LocalDate convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    @Given("Data Base is already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();

    }

    @When("current user who wants to add an event is {string}")
    public void currentUserWhoWantsToAddAnEventIs(String userName) throws UserNotFoundException {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.setCurrentUser(EventPlanner.getUserByUsername(userName));
        currentUser=(User)(EventPlanner.getCurrentUser());
    }
    @When("added event information are {string} {string} {string},{string} {string},{string}")
    public void added_event_information_are(String string, String string2,
                                            String string3, String string4,
                                            String string5, String string6) throws UserNotFoundException, EventAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        List<ServiceProvider> serviceProviders=new ArrayList<>();
        serviceProviders.add((ServiceProvider) EventPlanner.getServiceProviderByUsername(string3));
        serviceProviders.add((ServiceProvider) EventPlanner.getServiceProviderByUsername(string4));
        list=serviceProviders;
        List<String> emails=new ArrayList<>();
        emails.add(string5);
        emails.add(string6);

        newEvent =new RegisteredEvent(string2,
                                    convertStringToDate(string),
                                    EventPlanner.calculateTotalPriceForMultiProviders(serviceProviders)
                                    ,emails);
    }
    @Then("event will be added successfully and requset will be send the service provider")
    public void event_will_be_added_successfully() throws EventAlreadyExist, MessagingException, IOException {
//         Write code here that turns the phrase above into concrete actions
        int sizeBeforeAdding=currentUser.getRegisteredEvents().size();
        boolean existingBeforeAdding =currentUser.isThisEventExist(newEvent.getEventName());
        EventsControl.addEvent(newEvent.getDate(),newEvent.getEventName()
                            ,newEvent.getCost()
                            ,newEvent.getGuestsEmails());
        UserControl.sendRequestToServiceProvider(list.get(0),newEvent.getDate(),newEvent);
        UserControl.sendRequestToServiceProvider(list.get(1),newEvent.getDate(),newEvent);
//        newEvent.getServiceProviders().add(list.get(0));
//        newEvent.getServiceProviders().add(list.get(1));
        int sizeAfterAdding=currentUser.getRegisteredEvents().size();
        assert(sizeAfterAdding==sizeBeforeAdding+1&&currentUser.isThisEventExist(newEvent.getEventName())&&!existingBeforeAdding);
        assertEquals(list.get(0).getRequests().get(0).getEvent(), newEvent);
        assertEquals(list.get(1).getRequests().get(0).getEvent(), newEvent);
        assert(newEvent.getServiceProviders().get(0).equals(list.get(0)));
        assert (newEvent.getServiceProviders().get(1).equals(list.get(1)));


    }
    @Then("event will not be added successfully")
    public void event_will_not_be_added_successfully() throws EventAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
         //currentUser=(User)(EventPlanner.getCurrentUser());
        int sizeBeforeAdding=currentUser.getRegisteredEvents().size();
        boolean existingBeforeAdding =currentUser.isThisEventExist(newEvent.getEventName());

        int sizeAfterAdding=currentUser.getRegisteredEvents().size();
        assertThrows(EventAlreadyExist.class,()->EventsControl.addEvent(newEvent.getDate(),newEvent.getEventName()
               ,newEvent.getCost()
                ,newEvent.getGuestsEmails()));
        //assert(sizeAfterAdding==sizeBeforeAdding);

    }

    @And("service provider accept the request")
    public void serviceProviderAcceptTheRequest() throws FileNotFoundException {
        ServiceProviderControl.respondToRequests(true,newEvent,list.get(0));
        ServiceProviderControl.respondToRequests(true,newEvent,list.get(1));
    }
}