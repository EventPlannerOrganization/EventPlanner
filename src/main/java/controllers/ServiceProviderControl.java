package controllers;






import Exceptions.EmptyList;



import enumerations.ServiceType;
import models.*;

import views.ServiceProviderView;



import java.time.LocalDate;
import java.util.*;


public class ServiceProviderControl {

    private ServiceProviderControl() {

    }
    public static List<Service> getServiceProviderServices(ServiceProvider serviceProvider){
        List<Service> serviceProvdierServices = new ArrayList<>();
        serviceProvdierServices.addAll(serviceProvider.getServices());
        return serviceProvdierServices;

    }


    public static void signout() {
        EventPlanner.setCurrentUser(null);
    }


    public static List<RegisteredEvent> getServiceProviderEvents(ServiceProvider serviceProvider) throws EmptyList {
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);
        if (filteredUsers.isEmpty()) {
            throw new EmptyList();
        }
          return getEventsRelatedWithServiceProvider(serviceProvider);
    }

    public static List<RegisteredEvent>  getServiceProviderUpComingEvents(ServiceProvider serviceProvider) throws EmptyList {

           List<RegisteredEvent> filterdEvents = getServiceProviderEvents(serviceProvider);
           return filterdEvents.stream().filter(registeredEvent -> registeredEvent.getDate().isAfter(LocalDate.now())).toList();


    }

    public static List<RegisteredEvent> getFilterdEvents(ServiceProvider serviceProvider,List<RegisteredEvent> reg) {

        return reg.stream().filter(event -> event.getServiceProviders().contains(serviceProvider)).toList();

    }

    public static List<User> getUsersRelatedWithServiceProvider(ServiceProvider serviceProvider) {

        List<User> allUsers = EventPlanner.getUsers();

        return   allUsers.stream()
                .filter(user -> user.getRegisteredEvents().stream()
                        .anyMatch(event -> event.getServiceProviders().contains(serviceProvider)))
                .toList();
    }

    public static List<RegisteredEvent> getEventsRelatedWithServiceProvider(ServiceProvider serviceProvider) {
        List<User> filteredUsers =getUsersRelatedWithServiceProvider(serviceProvider);// اول اشي اليوزرز اللي الهم علاقة بالسيرفرس بروفايدرر
        List<RegisteredEvent> serviceProvdierEvents = new ArrayList<>();
        for (User user : filteredUsers) {
            List<RegisteredEvent> filterd = getFilterdEvents(serviceProvider,user.getRegisteredEvents()); // بعدها الايفنتس اللي عند اليوزر اللي السيرفس بروفايدر اله علاقة فيها
            serviceProvdierEvents.addAll(filterd);
        }
            return serviceProvdierEvents;

    }




    public static void editServiceDescription(Service service,String descritpion){
        service.setDescription(descritpion);

    }

    public static void editServicePrice(Service service, String price) {
        service.setPrice(Double.parseDouble(price));
    }


    public static String getServiceDescription(Service service) {
        return service.getDescription();
    }
    public static Service getServiceFromServiceProvider(ServiceProvider serviceProvider){
        try {
            return serviceProvider.getServices().get(0);
        }
        catch(Exception e){
            return null;
        }
    }

    public static String getServicePrice(Service service) {
        return String.valueOf(service.getPrice());
    }

    public static boolean checkIfTheServiceAlreadyAdded(List<Service> serviceList,String choice) {
        Map <String,ServiceType> map =ServiceProviderView.hashmap();
        serviceList = serviceList.stream().filter(service -> service.getServiceType().equals(map.get(choice))).toList();
return !serviceList.isEmpty();
    }

    public static void respondToRequests(boolean choice,RegisteredEvent event,ServiceProvider choosenServiceProvider)  {
        if (choice) {
            choosenServiceProvider.getBookedDates().add(event.getDate());
            List<ServiceProvider> serviceProviders = new ArrayList<>();
            serviceProviders.add(choosenServiceProvider);
            event.addServices(serviceProviders);


        } else  {
            event.getServiceProviders().remove(choosenServiceProvider);
        }


    }


    public static void changeServiceProvdierService(ServiceProvider serviceProvider, Service service) {
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