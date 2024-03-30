package TestIo;

import io.YmlHandler;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class TestYmlHandler {


    @Test
    public void testGetValue() throws FileNotFoundException {
        assertEquals("eventplannerteam4@gmail.com", YmlHandler.getValue("email-config", "fromEmail"));
        assertEquals("ebzh kxzz dewz iyjq", YmlHandler.getValue("email-config", "password"));
    }
}