package testModels;

import enumerations.ServiceType;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestServiceProvider {
    private ServiceProvider serviceProvider;

    @Before
    public void setup() {
        Name name = new Name("John", "Doe", "Smith");
        Authentication authentication = new Authentication("john.smith", "password1");
        Address address = new Address("USA", "New York");
        ContactInfo contactInfo = new ContactInfo("john.smith@example.com", "1234567890");

        List<Service> services = new ArrayList<>();
        services.add(new Service(ServiceType.DJ, 20, "dj"));

        serviceProvider = new ServiceProvider(name, authentication, address, contactInfo, services);
    }

    @Test
    public void testDefaultConstructor() {
        ServiceProvider defaultServiceProvider = new ServiceProvider();
        assertEquals(0, defaultServiceProvider.getServices().size());
        assertNull( defaultServiceProvider.getBookedDates());
        assertFalse(defaultServiceProvider.isPackageProvider());
    }

    @Test
    public void testGetServices() {
        assertEquals(1, serviceProvider.getServices().size());
    }

    @Test
    public void testSetServices() {
        List<Service> newServices = new ArrayList<>();
        newServices.add(new Service(ServiceType.CATERING, 30, "catering"));
        serviceProvider.setServices(newServices);

        assertEquals(1, serviceProvider.getServices().size());
        assertEquals(ServiceType.CATERING, serviceProvider.getServices().get(0).getServiceType());
    }

    @Test
    public void testGetBookedDates() {
        assertEquals(0, serviceProvider.getBookedDates().size());
    }

    @Test
    public void testIsPackageProvider() {
        assertFalse(serviceProvider.isPackageProvider());
    }

    @Test
    public void testSetPackageProvider() {
        serviceProvider.setPackageProvider(true);
        assertTrue(serviceProvider.isPackageProvider());
    }

    @Test
    public void testToString() {
        String expected = "\n\tService Provider "+serviceProvider.getName().toString()+"\n\t"+ serviceProvider.getServices().get(0).toString()+"\n";
        assertEquals(expected, serviceProvider.toString());
        serviceProvider.setPackageProvider(true);
        serviceProvider.getServices().add(new Service(ServiceType.DJ, 20.0, "dj"));
        serviceProvider.getServices().add(new Service(ServiceType.PHOTOGRAPHY, 50.0, "wedding photography"));

        StringBuilder pack=new StringBuilder("\tOffer Package items:\n");
        int counter=1;
        for(Service element:serviceProvider.getServices()){
            pack.append("\t");
            pack.append(counter);
            pack.append("- ");
            pack.append(element.toString());
            pack.append("\n");
            counter++;
        }
        String result =  "\n\tService Provider "+serviceProvider.getName().toString()+"\n"+ pack+"\n"   ;

        // Assert that the actual string representation matches the expected one
        assertEquals(result, serviceProvider.toString());
    }

    @Test
    public void testCalculateServiceProviderPrice() {
        assertEquals(20.0, serviceProvider.calculateServiceProviderPrice(), 0.001);
    }

    @Test
    public void testAddRequest() {

        serviceProvider.addRequest(new Request("bahaalawneh@gmail.com","baha@gmail.com","hii",new RegisteredEvent()));
        assertEquals(1, serviceProvider.getRequests().size());
    }

    @Test
    public void testEquals() {
        ServiceProvider sameServiceProvider = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"), serviceProvider.getServices());
        assertEquals(serviceProvider, sameServiceProvider);

        ServiceProvider differentServiceProvider = new ServiceProvider(new Name("Jane", "Doe", "Smith"), new Authentication("jane.doe", "password2"), new Address("UK", "London"), new ContactInfo("jane.doe@example.com", "0987654321"), new ArrayList<>());
        assertNotEquals(serviceProvider, differentServiceProvider);
    }

    @Test
    public void testHashCode() {
        ServiceProvider sameServiceProvider = new ServiceProvider(new Name("John", "Doe", "Smith"), new Authentication("john.smith", "password1"), new Address("USA", "New York"), new ContactInfo("john.smith@example.com", "1234567890"), serviceProvider.getServices());
        assertEquals(serviceProvider.hashCode(), sameServiceProvider.hashCode());
    }
}
