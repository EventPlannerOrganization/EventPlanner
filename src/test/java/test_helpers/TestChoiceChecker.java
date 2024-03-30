package test_helpers;

import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import helpers.ChoiceChecker;
import models.EventPlanner;
import models.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestChoiceChecker {
    @Test
    public void testIsValidChoice() {
        assertTrue(ChoiceChecker.isValidChoice("2",3));
        assertTrue(ChoiceChecker.isValidChoice("3",3));
        assertFalse(ChoiceChecker.isValidChoice("4",3));
        assertFalse(ChoiceChecker.isValidChoice("0",3));
        assertTrue(ChoiceChecker.isValidNumberOfServices("2"));
        assertTrue(ChoiceChecker.isValidNumberOfServices("3"));
        assertTrue(ChoiceChecker.isValidNumberOfServices("4"));
        assertFalse(ChoiceChecker.isValidNumberOfServices("1"));
    }
    @Test
    public void testReadEventName() throws UserNotFoundException, UserIsAlreadyExist {
        EventPlanner.initializeRepositoryWithData();
       User user= (User) EventPlanner.getUserByUsername("Naser");
       EventPlanner.setCurrentUser(user);
        assertEquals("ramadan sohoor", ChoiceChecker.readingEventName("ramadan sohoor"));

    }
}
