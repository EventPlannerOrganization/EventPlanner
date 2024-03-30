package testControllers.ServiceProvider;

import exceptions.EmptyList;
import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import controllers.ServiceProviderControl;
import enumerations.ServiceType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestShowEventsForServiceProvider {
    ServiceProvider serviceProvider1;
    List<RegisteredEvent> actualEvents=new ArrayList<>();

    @Given("Data Base already filled2")
    public void dataBaseAlreadyFilled2() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
EventPlanner.initializeRepositoryWithDataForTesting();
    }

    @When("Service Provider how's want to Show Events is {string}")
    public void serviceProviderHowSWantToShowEventsIs(String string) throws UserNotFoundException {
      serviceProvider1 = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @Then("The Events Will Display Correctly")
    public void theEventsWillDisplayCorrectly() throws EmptyList {
        List<Service>serviceList= new ArrayList<Service>();
        serviceList.add(  new Service(ServiceType.DJ,1400,"Best Sound Quality And Music"));

        ServiceProvider serviceProvider = new ServiceProvider(new Name("Mohammed","Munir","Shadid"),
                new Authentication("moshadid","Mo2003@@"),
                new Address("Palestine","Tulkarem"),
                new ContactInfo("3sfr3sfr@gmail.com","0598772189"),
                serviceList);
        List<ServiceProvider>serviceProviderList = new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        User user1 = new User(new Name("Naser","Mohammed","Abu Safieh"),
                new Authentication("naserabusafieh","Naser2003@"),
                new Address("Palestine","Nablus"),
                new ContactInfo("naserabusafia1@gmail.com","0597094028"));
        User user2 = new User(new Name("Baha","Khaled","Alawneh"),
                new Authentication("bahaalawneh","Baha2003@"),
                new Address("Palestine","Jenin"),
                new ContactInfo("bahaalawneh@gmail.com","0598223192"));
        User user3 = new User(new Name("Faiq","Fehmi","Bakri"),
                new Authentication("faiqBakri","Faiq2002@"),
                new Address("Palestine","Nablus"),
                new ContactInfo("Faiqbakri@gmail.com","0598995326"));

        List<String> emails = new ArrayList<>();
        emails.add("samihadman@gmail.com");
        emails.add("AliSurakji@gmail.com");

        LocalDate localDate1=LocalDate.of(2024,8,10);
        RegisteredEvent registeredEvent = new RegisteredEvent("Birthday",serviceProviderList,localDate1,1400,emails);
        user1.getRegisteredEvents().add(registeredEvent);

        LocalDate localDate2=LocalDate.of(2024,9,12);
        RegisteredEvent registeredEvent2 = new RegisteredEvent("Wedding",serviceProviderList,localDate2,1400,emails);
        user2.getRegisteredEvents().add(registeredEvent2);

        LocalDate localDate3=LocalDate.of(2023,9,12);
        RegisteredEvent registeredEvent3 = new RegisteredEvent("Party",serviceProviderList,localDate3,1400,emails);
        user3.getRegisteredEvents().add(registeredEvent3);

        actualEvents.addAll(ServiceProviderControl.getServiceProviderEvents(serviceProvider1));
        assertEquals(actualEvents.get(0),registeredEvent);
        assertEquals(actualEvents.get(1),registeredEvent2);
        assertEquals(actualEvents.get(2),registeredEvent3);
    }

    @When("Service Provider how's want to Show upcoming Events is {string}")
    public void serviceProviderHowSWantToShowUpcomingEventsIs(String string) throws UserNotFoundException {
        serviceProvider1 = (ServiceProvider) EventPlanner.getServiceProviderByUsername(string);
    }
    @Then("The Upcoming Events Will Display Correctly")
    public void theUpcomingEventsWillDisplayCorrectly() throws EmptyList {
        List<Service>serviceList= new ArrayList<Service>();
        serviceList.add(  new Service(ServiceType.DJ,1400,"Best Sound Quality And Music"));

        ServiceProvider serviceProvider = new ServiceProvider(new Name("Mohammed","Munir","Shadid"),
                new Authentication("moshadid","Mo2003@@"),
                new Address("Palestine","Tulkarem"),
                new ContactInfo("3sfr3sfr@gmail.com","0598772189"),
                serviceList);
        List<ServiceProvider>serviceProviderList = new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        User user1 = new User(new Name("Naser","Mohammed","Abu Safieh"),
                new Authentication("naserabusafieh","Naser2003@"),
                new Address("Palestine","Nablus"),
                new ContactInfo("naserabusafia1@gmail.com","0597094028"));
        User user2 = new User(new Name("Baha","Khaled","Alawneh"),
                new Authentication("bahaalawneh","Baha2003@"),
                new Address("Palestine","Jenin"),
                new ContactInfo("bahaalawneh@gmail.com","0598223192"));

        List<String> emails = new ArrayList<>();
        emails.add("samihadman@gmail.com");
        emails.add("AliSurakji@gmail.com");

        LocalDate localDate1=LocalDate.of(2024,8,10);
        RegisteredEvent registeredEvent = new RegisteredEvent("Birthday",serviceProviderList,localDate1,1400,emails);
        user1.getRegisteredEvents().add(registeredEvent);

        LocalDate localDate2=LocalDate.of(2024,9,12);
        RegisteredEvent registeredEvent2 = new RegisteredEvent("Wedding",serviceProviderList,localDate2,1400,emails);
        user2.getRegisteredEvents().add(registeredEvent2);
        List<RegisteredEvent> actualEvents = ServiceProviderControl.getServiceProviderUpComingEvents(serviceProvider1);
        assertEquals(actualEvents.get(0),registeredEvent);
        assertEquals(actualEvents.get(1),registeredEvent2);

    }
    @Then("empty list exception will be thrown")
    public void emptyListExceptionWillBeThrown() {
        assertThrows(EmptyList.class,() ->ServiceProviderControl.getServiceProviderEvents(serviceProvider1) );
    }

}
