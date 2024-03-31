package test_enumerations;

import enumerations.Colors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestColors {
    @Test
    public void testColors() {
        assertEquals("\u001B[33m", Colors.YELLOW.getUniCodeValue());
        assertEquals("\u001B[32m", Colors.GREEN.getUniCodeValue());
        assertEquals("\u001B[0m", Colors.RESET.getUniCodeValue());
        assertEquals("\u001B[47m", Colors.BACKGROUND_WHITE.getUniCodeValue());
    }
}
