package testModels;

import enumerations.UserType;
import exceptions.EventAlreadyExist;
import exceptions.EventNotFound;
import models.Address;
import models.Authentication;
import models.ContactInfo;
import models.Name;
import models.RegisteredEvent;
import models.User;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class TestUser {
    private User user;

    @Before
    public void setup() {
        Name name = new Name("John", "Doe", "Smith");
        Authentication authentication = new Authentication("john.smith", "password1");
        Address address = new Address("USA", "New York");
        ContactInfo contactInfo = new ContactInfo("john.smith@example.com", "1234567890");

        user = new User(name, authentication, address, contactInfo);
    }

    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNull(defaultUser.getName());
        assertNull(defaultUser.getAuthentication());
        assertNull(defaultUser.getAddress());
        assertNull(defaultUser.getContactInfo());
        assertNull (defaultUser.getUsertype());
        assertNull(defaultUser.getRegisteredEvents());
        assertEquals(0.0, defaultUser.getTotalCost(), 0.001);
    }

    @Test
    public void testGetUsertype() {
        assertEquals(UserType.USER, user.getUsertype());
    }

    @Test
    public void testSetUsertype() {
        user.setUsertype(UserType.ADMIN);
        assertEquals(UserType.ADMIN, user.getUsertype());
    }

    @Test
    public void testGetRegisteredEvents() {
        assertNotNull(user.getRegisteredEvents());
        assertTrue(user.getRegisteredEvents().isEmpty());
    }

    @Test
    public void testSetTotalCost() {
        user.setTotalCost(100.0);
        assertEquals(100.0, user.getTotalCost(), 0.001);
    }

    @Test
    public void testAddToTotalCost() {
        user.addToTotalCost(50.0);
        assertEquals(50.0, user.getTotalCost(), 0.001);
    }

    @Test
    public void testCheckEventExisting() {
        // Register an event
        RegisteredEvent event = new RegisteredEvent("Birthday Party", null, null, 0, null);
        user.getRegisteredEvents().add(event);

        // Check for existing event
        assertThrows(EventAlreadyExist.class, () -> user.checkEventExisting("Birthday Party"));
    }

    @Test
    public void testIsThisEventExist() {
        // Register an event
        RegisteredEvent event = new RegisteredEvent("Birthday Party", null, null, 0, null);
        user.getRegisteredEvents().add(event);

        // Check for existing event
        assertTrue(user.isThisEventExist("Birthday Party"));

        // Check for non-existing event
        assertFalse(user.isThisEventExist("Wedding"));
    }

    @Test
    public void testGetEventByName() throws EventNotFound {
        // Register an event
        RegisteredEvent event = new RegisteredEvent("Birthday Party", null, null, 0, null);
        user.getRegisteredEvents().add(event);

        // Retrieve the event by name
        assertEquals(event, user.getEventByName("Birthday Party"));
    }

    @Test
    public void testGetEventByName_EventNotFound() {
        // Try to retrieve a non-existing event
        assertThrows(EventNotFound.class, () -> user.getEventByName("Wedding"));
    }

    @Test
    public void testEquals() {
        User sameUser = new User(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"));
        assertEquals(user, sameUser);

        User differentUser = new User(new Name("Jane", "Doe", "Smith"), new Authentication("jane.doe", "password2"), new Address("UK", "London"), new ContactInfo("jane.doe@example.com", "0987654321"));
        assertNotEquals(user, differentUser);
    }

    @Test
    public void testHashCode() {
        User sameUser = new User(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"));
        assertEquals(user.hashCode(), sameUser.hashCode());
    }
}