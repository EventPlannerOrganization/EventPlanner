package test_helpers;

import helpers.PasswordChecker;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPasswordChecker {
    @Test
    public void testIsStrongPassword() {
        assertFalse(PasswordChecker.isStrongPassword("")); // empty
        assertFalse(PasswordChecker.isStrongPassword("baha123")); // length < 8
        assertFalse(PasswordChecker.isStrongPassword("Bahaalawneh11")); // missing special chars
        assertFalse(PasswordChecker.isStrongPassword("Baha#@!")); // missing digits
        assertFalse(PasswordChecker.isStrongPassword("baha#$%1")); // missing capital letters
        assertFalse(PasswordChecker.isStrongPassword("BAHAALAWNEH#@!$22")); // missing small letters
        assertTrue(PasswordChecker.isStrongPassword("BAHAAal$$%2003")); // strong password
    }

}
