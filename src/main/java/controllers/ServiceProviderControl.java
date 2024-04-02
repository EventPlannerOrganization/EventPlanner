package controllers;






import exceptions.EmptyList;



import models.*;




import java.time.LocalDate;
import java.util.*;


public class ServiceProviderControl {

    private ServiceProviderControl() {

    }
    public static List<Service> getServiceProviderServices(ServiceProvider serviceProvider){
        return new ArrayList<>(serviceProvider.getServices());

    }





    public static List<RegisteredEvent> getServiceProviderEvents(ServiceProvider serviceProvider) throws EmptyList {
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);
        if (filteredUsers.isEmpty()) {
            throw new EmptyList();
        }
          return getEventsRelatedWithServiceProvider(serviceProvider);
    }

    public static List<RegisteredEvent>  getServiceProviderUpComingEvents(ServiceProvider serviceProvider) throws EmptyList {

           List<RegisteredEvent> filteredEvents = getServiceProviderEvents(serviceProvider);
           return filteredEvents.stream().filter(registeredEvent -> registeredEvent.getDate().isAfter(LocalDate.now())).toList();


    }

    public static List<RegisteredEvent> getFilteredEvents(ServiceProvider serviceProvider, List<RegisteredEvent> reg) {

        return reg.stream().filter(event -> event.getServiceProviders().contains(serviceProvider)).toList();

    }

    public static List<User> getUsersRelatedWithServiceProvider(ServiceProvider serviceProvider) {

        List<User> allUsers = EventPlanner.getUsers();

        return   allUsers.stream()
                .filter(user -> user.getRegisteredEvents().stream()
                        .anyMatch(event -> event.getServiceProviders().contains(serviceProvider))).toList();
    }

    public static List<RegisteredEvent> getEventsRelatedWithServiceProvider(ServiceProvider serviceProvider) {
        List<User> filteredUsers =getUsersRelatedWithServiceProvider(serviceProvider);
        List<RegisteredEvent> serviceProviderEvents = new ArrayList<>();
        for (User user : filteredUsers) {
            List<RegisteredEvent> filtered = getFilteredEvents(serviceProvider,user.getRegisteredEvents());
            serviceProviderEvents.addAll(filtered);
        }
            return serviceProviderEvents;

    }




    public static void editServiceDescription(Service service,String description){
        service.setDescription(description);

    }

    public static void editServicePrice(Service service, String price) {
        service.setPrice(Double.parseDouble(price));
    }


    public static String getServiceDescription(Service service) {
        return service.getDescription();
    }
    public static Service getServiceFromServiceProvider(ServiceProvider serviceProvider){

            return serviceProvider.getServices().get(0);

    }

    public static String getServicePrice(Service service) {
        return String.valueOf(service.getPrice());
    }


    public static void respondToRequests(boolean choice,Request request,ServiceProvider choosenServiceProvider)  {
        if (choice) {
            choosenServiceProvider.getBookedDates().add(request.getEvent().getDate());
            List<ServiceProvider> serviceProviders = new ArrayList<>();
            serviceProviders.add(choosenServiceProvider);
            request.getEvent().addServices(serviceProviders);
        }
        choosenServiceProvider.getRequests().remove(request);

    }


    public static void changeServiceProviderService(ServiceProvider serviceProvider, Service service) {
        List<Service> list= new ArrayList<>();
        list.add(service);
        serviceProvider.setServices(list);
        serviceProvider.setPackageProvider(false);
    }

    public static void changePackageProviderServices(ServiceProvider serviceProvider, List<Service> services) {
        serviceProvider.setPackageProvider(true);
        serviceProvider.setServices(services);
    }
}