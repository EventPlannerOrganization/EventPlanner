package controllers;

import Exceptions.EmptyList;
import Exceptions.ServiceNotFoundException;
import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;

import enumerations.ServiceType;
import models.*;
import printers.MenusPrinter;
import views.ServiceProviderView;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderControl {
    private static final Logger logger = Logger.getLogger(ServiceProviderControl.class.getName());
    private static final Scanner scanner=new Scanner(System.in);

    private ServiceProviderControl() {

    }
    public static List<Service> getServiceProviderServices(ServiceProvider serviceProvider){
        List<Service> serviceProvdierServices = new ArrayList<>();
        for (int i = 0; i < serviceProvider.getServices().size(); i++) {
            serviceProvdierServices.add(serviceProvider.getServices().get(i));
        }
        return serviceProvdierServices;

    }
    public static void showServiceProviderServices(ServiceProvider serviceProvider) {
        List<Service> serviceProvdierServices = getServiceProviderServices(serviceProvider);
        List<String> serviceProviderServiceString=new ArrayList<>() ;
        for (int i = 0; i < serviceProvider.getServices().size(); i++) {
            String st1 = "Service info : \n";
            String st = st1 + serviceProvdierServices.get(i).toString() + "\n -------------------------------------------";
            serviceProviderServiceString.add(st);

        }
        MenusPrinter.printListofStringWithNumbers(serviceProviderServiceString, "\"Here is Your Service/s:\"");

    }

    public static void signout() {
        EventPlanner.setCurrentUser(null);
    }


    public static List<RegisteredEvent> getServiceProviderEvents() throws EmptyList {
        ServiceProvider serviceProvider = (ServiceProvider) EventPlanner.getCurrentUser();
        List<User> filteredUsers = getUsersRelatedWithServiceProvider(serviceProvider);

        if (filteredUsers.isEmpty()) {
            throw new EmptyList();
        }
          return getEventsRelatedWithServiceProvider((ServiceProvider) EventPlanner.getCurrentUser());
    }
    public static void showServiceProviderEvents(){
        try {
            printList(getServiceProviderEvents());
        }
            catch (EmptyList e){
            logger.info("You Don't  Have Any Events ");
            }
    }
    public static List<RegisteredEvent>  getServiceProviderUpComingEvents() throws EmptyList {

           List<RegisteredEvent> filterdEvents = getServiceProviderEvents();
           return filterdEvents.stream().filter(registeredEvent -> registeredEvent.getDate().isAfter(LocalDate.now())).toList();


    }
    public static void showServiceProviderUpcomingEvents() {
        try {
            String string = new StringBuilder().append("Invalid Input").append("\n If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B ").toString();
            while (1<2){
                printList(getServiceProviderUpComingEvents());
                logger.info("If you Want To Discard Event ,Enter Event Number \n To Go Back Enter B ");
                String choice = scanner.nextLine();
                while (!(choice.equals("B")||choice.equals("b")||Integer.parseInt(choice) <= getServiceProviderUpComingEvents().size())) {
                    logger.info(string);
                    choice = scanner.nextLine();

                }
                if (choice.equals("B") || choice.equals("b")) {
                    ServiceProviderView.providerMenu();
                } else  {
                    EventsControl.deleteService(getServiceProviderUpComingEvents().get(Integer.parseInt(choice) - 1), (ServiceProvider) EventPlanner.getCurrentUser());
                    printList(getServiceProviderUpComingEvents());
                }

            }

        } catch(EmptyList | UserIsAlreadyExist | ServiceNotFoundException|WeakPasswordException emptyList ) {
            logger.info("You Don't  Have Any Events ");

        }
    }

    public static List<RegisteredEvent> getFilterdEvents(List<RegisteredEvent> reg) {

        return reg.stream().filter(event -> event.getServiceProviders().contains(EventPlanner.getCurrentUser())).toList();

    }

    public static List<User> getUsersRelatedWithServiceProvider(ServiceProvider serviceProvider) {

        List<User> allUsers = EventPlanner.getUsers();
        return allUsers.stream()
                .filter(user -> user.getRegisteredEvents().stream()
                        .anyMatch(event -> event.getServiceProviders().contains(serviceProvider)))
                .toList();
    }

    public static List<RegisteredEvent> getEventsRelatedWithServiceProvider(ServiceProvider serviceProvider) {
        List<User> filteredUsers =getUsersRelatedWithServiceProvider(serviceProvider);
        List<RegisteredEvent> serviceProvdierEvents = new ArrayList<>();
        for (int i = 0; i < filteredUsers.size(); i++) {
            User user = filteredUsers.get(i);
            List<RegisteredEvent> filterd = getFilterdEvents(user.getRegisteredEvents());
            for (int j = 0; j < filterd.size(); j++) {
                serviceProvdierEvents.add(filterd.get(j));
            }
        }
            return serviceProvdierEvents;

    }
    public  static List<String> makeStringListOfEvents(List <RegisteredEvent>filterdEvents ){
        List<String> serviceProvdierEvents = new ArrayList<>();
        for(int i = 0;i<filterdEvents.size();i++) {
            String st1 = "Service info : \n";
            String events = st1 + filterdEvents.get(i).toString2() + "\n -------------------------------------------";
            serviceProvdierEvents.add(events);
        }
        return serviceProvdierEvents;
    }
    public static void printList(List<RegisteredEvent>filterdEvents){
        List<String>  serviceProvdierEvents= makeStringListOfEvents(filterdEvents);
        MenusPrinter.printListofStringWithNumbers(serviceProvdierEvents, "Here is Your Event/s:");
    }

    public static boolean checkIfitsCurrentService(Service service,String choice) {
        HashMap<String,ServiceType> map = new HashMap<>();
        map.put("1", ServiceType.DJ);
        map.put("2",ServiceType.Photography);
        map.put("3",ServiceType.Security);
        map.put("4",ServiceType.Cleaning);
        try {
            return map.get(choice).equals(service.getServiceType());
        }
        catch (Exception e){
            return false;
        }
    }

    public static void editServiceType(Service service, ServiceType serviceType) {
        service.setServiceType(serviceType);
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
        HashMap<String,ServiceType> map = new HashMap<>();
        map.put("1", ServiceType.DJ);
        map.put("2",ServiceType.Photography);
        map.put("3",ServiceType.Security);
        map.put("4",ServiceType.Cleaning);
serviceList = serviceList.stream().filter(service -> service.getServiceType().equals(map.get(choice))).toList();
return !serviceList.isEmpty();
    }
}