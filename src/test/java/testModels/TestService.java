package testModels;

import enumerations.ServiceType;
import models.Service;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestService {

    @Test
    public void testGetServiceType() {
        Service service = new Service(ServiceType.DJ, 20.0, "dj");
        assertEquals(ServiceType.DJ, service.getServiceType());
    }

    @Test
    public void testSetServiceType() {
        Service service = new Service(ServiceType.PHOTOGRAPHY, 50.0, "wedding photography");
        service.setServiceType(ServiceType.CATERING);
        assertEquals(ServiceType.CATERING, service.getServiceType());
    }

    @Test
    public void testGetDescription() {
        Service service = new Service(ServiceType.DJ, 20.0, "dj");
        assertEquals("dj", service.getDescription());
    }

    @Test
    public void testSetDescription() {
        Service service = new Service(ServiceType.DJ, 20.0, "dj");
        service.setDescription("live DJ performance");
        assertEquals("live DJ performance", service.getDescription());
    }

    @Test
    public void testToString() {
        Service service = new Service(ServiceType.PHOTOGRAPHY, 50.0, "wedding photography");
        String expected = "\tType: PHOTOGRAPHY \n\tPrice: 50.0$\n\tDescription: wedding photography";
        assertEquals(expected, service.toString());
    }

    @Test
    public void testGetPrice() {
        Service service = new Service(ServiceType.PHOTOGRAPHY, 50.0, "wedding photography");
        assertEquals(50.0, service.getPrice(), 0.001); // delta is the maximum difference between expected and actual for which both are still considered equal.
    }

    @Test
    public void testSetPrice() {
        Service service = new Service(ServiceType.PHOTOGRAPHY, 50.0, "wedding photography");
        service.setPrice(60.0);
        assertEquals(60.0, service.getPrice(), 0.001);
    }
}
