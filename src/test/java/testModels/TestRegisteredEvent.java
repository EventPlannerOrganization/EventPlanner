package testModels;

import enumerations.ServiceType;
import models.*;
import exceptions.ServiceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestRegisteredEvent {
    private RegisteredEvent event;
    private ServiceProvider serviceProvider;
    private List<Service> services;

    @Before
    public void setup() {
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        services = new ArrayList<>();
        Service service = new Service(ServiceType.DJ, 20, "dj");
        services.add(service);
        serviceProvider = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"), services);
        serviceProviders.add(serviceProvider);
        LocalDate date = LocalDate.of(2024, 4, 1);
        double cost = 100.0;
        List<String> guestsEmails = new ArrayList<>();
        event = new RegisteredEvent("Birthday Party", serviceProviders, date, cost, guestsEmails);
    }

    @Test
    public void testGetServiceProviders() {
        assertNotNull(event.getServiceProviders());
    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.of(2024, 4, 1), event.getDate());
    }

    @Test
    public void testGetCost() {
        assertEquals(100.0, event.getCost(), 0.001);
    }

    @Test
    public void testGetGuestsEmails() {
        assertNotNull(event.getGuestsEmails());
        assertTrue(event.getGuestsEmails().isEmpty());
    }

    @Test
    public void testSetLocation() {
        event.setLocation("New York");
        assertEquals("New York", event.getLocation());
    }

    @Test
    public void testEquals() {
        RegisteredEvent sameEvent = new RegisteredEvent("Birthday Party", LocalDate.of(2024, 4, 1), 100.0, new ArrayList<>());
        assertEquals(event, sameEvent);

        RegisteredEvent differentEvent = new RegisteredEvent("Wedding", LocalDate.of(2024, 4, 1), 200.0, new ArrayList<>());
        assertNotEquals(event, differentEvent);
    }

    @Test
    public void testHashCode() {
        RegisteredEvent sameEvent = new RegisteredEvent("Birthday Party", LocalDate.of(2024, 4, 1), 100.0, new ArrayList<>());
        assertEquals(event.hashCode(), sameEvent.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "\u001B[1m" + "\u001B[32m" + "Event Name: " + event.getEventName() + "\u001B[0m" +
                "\u001B[34m" + "\nServices: \n" + event.getServicesDetails() +
                "Date: " + event.getDate() +
                "\nTotal Cost: " + event.getCost() +
                "\nGuests List: \n" + event.getGuestsEmails() + "\n\n" + "\u001B[0m";
        String actual = event.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testToString2() {
        String expected = "\nEvent Name : Birthday Party\n Date : 2024-04-01\n Guests List :\n[]";
        assertEquals(expected, event.toString2());
    }

    @Test
    public void testAddServices() {
        List<Service> serviceList=new ArrayList<>();
        serviceList.add(new Service(ServiceType.CLEANING, 20, "rr"));
        serviceProvider = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"), serviceList);
        List<ServiceProvider> serviceProviderList=new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        event.addServices(serviceProviderList);
        assertTrue(event.getServiceProviders().contains(serviceProvider));
    }

    @Test
    public void testSubFromCost() {
        event.subFromCost(50.0);
        assertEquals(50.0, event.getCost(), 0.001);
    }

    @Test
    public void testCheckServiceProviderExisting()  {
        ServiceProvider serviceProvider1 = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("jad", "password1"), new Address("USA", "New York"), new ContactInfo("jad@example.com", "1234567890"), services);
        assertThrows(ServiceNotFoundException.class, () -> event.checkServiceProviderExisting(serviceProvider1));


    }
    @Test
    public void testSetGuestsEmails() {
        List<String> guestsEmails = new ArrayList<>();
        guestsEmails.add("example@example.com");
        event.setGuestsEmails(guestsEmails);
        assertEquals(guestsEmails, event.getGuestsEmails());
    }

    @Test
    public void testSetDate() {
        LocalDate newDate = LocalDate.of(2024, 5, 1);
        event.setDate(newDate);
        assertEquals(newDate, event.getDate());
    }

    @Test
    public void testSetServiceProviders() {
        List<ServiceProvider> newServiceProviders = new ArrayList<>();
        // Add new service providers to the list
        event.setServiceProviders(newServiceProviders);
        assertEquals(newServiceProviders, event.getServiceProviders());
    }

    @Test
    public void testSetEventName() {
        String newEventName = "Wedding";
        event.setEventName(newEventName);
        assertEquals(newEventName, event.getEventName());
    }
    @Test
    public void testDefaultConstructor() {
        RegisteredEvent defaultEvent = new RegisteredEvent();

        // Verify that default values are set correctly
        assertNull(defaultEvent.getEventName());
        assertNull(defaultEvent.getDate());
        assertEquals(0.0, defaultEvent.getCost(), 0.001);
        assertNull(defaultEvent.getLocation());
    }

}
