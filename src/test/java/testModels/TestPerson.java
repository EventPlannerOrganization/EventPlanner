package testModels;

import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import models.*;
import org.junit.Before;
import org.junit.Test;

import static models.EventPlanner.*;
import static org.junit.Assert.*;

public class TestPerson {
    private Person person1;
    private Person person2;
    private Person person3;

    @Before
    public void setup() throws UserIsAlreadyExist {
        Name name1 = new Name("John", "Doe", "Smith");
        Name name2 = new Name("Jane", "Doe", "Smith");
        Authentication auth1 = new Authentication("john.smith", "password1");
        Authentication auth2 = new Authentication("jane.doe", "password2");
        Address address1 = new Address("USA", "New York");
        Address address2 = new Address("UK", "London");
        ContactInfo contactInfo1 = new ContactInfo("john.smith@example.com", "1234567890");
        ContactInfo contactInfo2 = new ContactInfo("jane.doe@example.com", "0987654321");

        person1 = new Person(name1, auth1, address1, contactInfo1);
        person2 = new Person(name2, auth2, address2, contactInfo2);
        person3= new Person(name2, auth2, address2, contactInfo2);



    }

    @Test
    public void testGetName() {
        person3.setName(new Name("baha","kh","alawneh"));
        assertEquals("baha", person3.getName().getfName());
        assertEquals("kh", person3.getName().getmName());
        assertEquals("alawneh", person3.getName().getlName());
    }

    @Test
    public void testGetAuthentication() {
        person3.setAuthentication(new Authentication("bahaalawneh","bahaA123$"));
        assertEquals("bahaalawneh", person3.getAuthentication().getUsername());
        assertEquals("bahaA123$", person3.getAuthentication().getPassword());
    }

    @Test
    public void testGetAddress() {
        person3.setAddress(new Address("palestine","jenin"));
        assertEquals("palestine", person3.getAddress().getCountry());
        assertEquals("jenin", person3.getAddress().getCity());
    }

    @Test
    public void testGetContactInfo() {
        person3.setContactInfo(new ContactInfo("bahaalawneh@gmail.com","0594371093"));
        assertEquals("bahaalawneh@gmail.com", person3.getContactInfo().getEmail());
        assertEquals("0594371093", person3.getContactInfo().getPhoneNumber());
    }
    @Test
    public void testEquals() {
        assertEquals(person1, new Person(null, new Authentication("john.smith", "password1"), null, new ContactInfo("john.smith@example.com", "1234567890")));
        assertNotEquals(person1, person2);
        assertNotEquals(person1.getAuthentication(), person2.getAuthentication());
        assertNotEquals(person1.getName(), person2.getName());
        assertNotEquals(person1.getAddress(), person2.getAddress());
        assertNotEquals(person1.getContactInfo(), person2.getContactInfo());


        assertNotEquals(person1, null);
    }

    @Test
    public void testHashCode() {
        assertEquals(person1.hashCode(), new Person(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890")).hashCode());
    }
    @Test
    public void testDefaultConstructor() {
        Person defaultPerson = new Person();
        assertNull(defaultPerson.getName());
        assertNull(defaultPerson.getAuthentication());
        assertNull(defaultPerson.getAddress());
        assertNull(defaultPerson.getContactInfo());
    }
    @Test
    public void testSignout() {
        signout();
        assertNull(getCurrentUser());

    }
    @Test
    public void testCheckEmailIfExist() throws UserIsAlreadyExist {
        EventPlanner.addUser(person1);
        assertTrue(checkEmailIfExist("john.smith@example.com"));
        assertFalse(checkEmailIfExist("john.smith@exampleeeeeeeeee.com"));

    }

    @Test
    public void testGetUserByEmail() throws UserNotFoundException, UserIsAlreadyExist {
        EventPlanner.addUser(person2);
        Person person;
        person=getUserByEmail("jane.doe@example.com");
        assertEquals(person,person2);
        assertThrows(UserNotFoundException.class,()->getUserByEmail("john.smith@exampleeeeeee.com"));

    }



}
