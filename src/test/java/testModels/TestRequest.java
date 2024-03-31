package testModels;

import enumerations.ServiceType;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestRequest {
    private RegisteredEvent event;
    private Request request;

    @Before
    public void setUp() {
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        List<Service> services = new ArrayList<>();
        Service service = new Service(ServiceType.DJ, 20, "dj");
        services.add(service);
        ServiceProvider serviceProvider = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"), services);
        serviceProviders.add(serviceProvider);
        LocalDate date = LocalDate.of(2024, 4, 1);
        double cost = 100.0;
        List<String> guestsEmails = new ArrayList<>();
        event = new RegisteredEvent("Birthday Party", serviceProviders, date, cost, guestsEmails);
        request = new Request("user@example.com", "provider@example.com", "Message", event);
    }

    @Test
    public void testEquals() {


        Request request1 = new Request("user@example.com", "provider@example.com", "Message", event);
        Request request2 = new Request("user@example.com", "provider@example.com", "Message", event);
        Request request3 = new Request("user@example.com", "proviider@example.com", "Message", event);

        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
    }

    @Test
    public void testHashCode() {


        Request request1 = new Request("user@example.com", "provider@example.com", "Message", event);
        Request request2 = new Request("user@example.com", "provider@example.com", "Message", event);

        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("user@example.com", request.getUserEmail());
        assertEquals("provider@example.com", request.getServiceProviderEmail());
        assertEquals("Message", request.getMessage());
        assertEquals(event, request.getEvent());

        request.setUserEmail("newuser@example.com");
        request.setServiceProviderEmail("newprovider@example.com");
        request.setMessage("New Message");

        assertEquals("newuser@example.com", request.getUserEmail());
        assertEquals("newprovider@example.com", request.getServiceProviderEmail());
        assertEquals("New Message", request.getMessage());
    }
}
