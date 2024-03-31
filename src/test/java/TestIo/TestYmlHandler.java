package TestIo;

import io.YmlHandler;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class TestYmlHandler {


    @Test
    public void testGetValue() throws FileNotFoundException {
        assertEquals("naserabusafia1@gmail.com", YmlHandler.getValue("email-config", "fromEmail"));
        assertEquals("ztmk zhxq yian aqhf", YmlHandler.getValue("email-config", "password"));
        assertEquals("C:\\Users\\HITECH\\IdeaProjects\\Sakancom\\src\\main\\resources\\images/building_"
                , YmlHandler.getValue("path-config", "path"));
    }
}