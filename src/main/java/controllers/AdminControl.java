package controllers;

import email.EmailService;
import exceptions.EmptyList;
import exceptions.EventAlreadyExist;
import exceptions.EventNotFoundException;
import exceptions.ServiceNotFoundException;
import models.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static controllers.ServiceProviderControl.getServiceProviderUpComingEvents;
import static helpers.StringOperations.mergeThreeStrings;


public class AdminControl {
    private AdminControl() {

    }


    public static List<String> getAllUsers() {
        return getUserNameOfUsers(EventPlanner.getUsers());
    }

    public static List<String> getAllServiceProviders() {
        return getUserNameOfServiceProviders(EventPlanner.getServiceProviders());
    }

    public static List<String> getEventsForUser(User user) {
        List<String> events;
        events = getEventNameOfUsers(user.getRegisteredEvents());
        return events;
    }

    public static List<String> getEventNameOfUsers(List<RegisteredEvent> events) {
        List<String> eventsNames = new ArrayList<>();
        List<RegisteredEvent> sortedEvents = sortListOfEvents(events);
        for (RegisteredEvent event : sortedEvents) {
            eventsNames.add(mergeThreeStrings(event.getEventName(), event.getDate().toString(), event.getLocation()));
        }
        return eventsNames;
    }


    public static List<RegisteredEvent> getAllEvents() {
        List<RegisteredEvent> events = new ArrayList<>();
        for (User user : EventPlanner.getUsers()) {
            events.addAll(user.getRegisteredEvents());
        }

        return sortListOfEvents(events);
    }

    public static List<String> getAllEventsNames() {
        return getEventNameOfUsers(getAllEvents());
    }

    public static List<RegisteredEvent> sortListOfEvents(List<RegisteredEvent> events) {
        return events.stream().sorted(Comparator.comparing(RegisteredEvent::getDate)).toList();
    }

    public static List<String> getServicesForServiceProvider(ServiceProvider serviceProvider) {
        List<String> events = new ArrayList<>();
        for (Service service : serviceProvider.getServices()) {
            events.add(service.toString());
        }
        return events;
    }

    public static List<String> getBookedDatasForServiceProvider(ServiceProvider serviceProvider) {
        List<String> events = new ArrayList<>();
        for (LocalDate date : serviceProvider.getBookedDates()) {
            events.add(date.toString());
        }
        return events;
    }

    public static List<User> searchUsers(String searchTerm) {
        List<User> searchResults = new ArrayList<>();
        String searchTermLowerCase = searchTerm.toLowerCase(); // Convert search term to lowercase

        for (User user : EventPlanner.getUsers()) {
            String userNameLowerCase = user.getAuthentication().getUsername().toLowerCase(); // Convert username to lowercase
            if (userNameLowerCase.contains(searchTermLowerCase)) { // Partial match check
                searchResults.add(user);
            }
        }

        return searchResults;
    }

    public static List<String> getUserNameOfUsers(List<User> users) {
        List<String> userNames = new ArrayList<>();
        for (User element : users) {
            userNames.add(element.getAuthentication().getUsername());
        }
        return userNames;
    }

    public static List<String> getUserNameOfServiceProviders(List<ServiceProvider> serviceProviders) {
        List<String> userNames = new ArrayList<>();
        for (ServiceProvider element : serviceProviders) {
            userNames.add(element.getAuthentication().getUsername());
        }
        return userNames;
    }

    public static void deleteServiceProvider(ServiceProvider deletedServiceProvider) throws EmptyList, ServiceNotFoundException, MessagingException, IOException {
        List<RegisteredEvent> serviceProviderEvents = getServiceProviderUpComingEvents(deletedServiceProvider);
        for (RegisteredEvent event : serviceProviderEvents) {
            EventsControl.deleteService(event, deletedServiceProvider);
        }
        deleteUser(deletedServiceProvider);

    }

    public static void deleteUser(Person deletedUser) throws IOException, MessagingException {
        EmailService emailForDeletedUserFromAdmin = new EmailService();
        emailForDeletedUserFromAdmin.sendAdminDeleteUserEmail(deletedUser.getContactInfo().getEmail(), deletedUser.getAuthentication().getUsername(), "admin-delete-user");
        EventPlanner.getUsersList().remove(deletedUser);


    }

    public static void resetPassword(Person user, String newPassword) {

        user.getAuthentication().setPassword(newPassword);


    }

    public static List<ServiceProvider> searchServiceProviders(String searchTerm) {
        List<ServiceProvider> searchResults = new ArrayList<>();
        String searchTermLowerCase = searchTerm.toLowerCase(); // Convert search term to lowercase

        for (ServiceProvider user : EventPlanner.getServiceProviders()) {
            String userNameLowerCase = user.getAuthentication().getUsername().toLowerCase(); // Convert username to lowercase
            if (userNameLowerCase.contains(searchTermLowerCase)) { // Partial match check
                searchResults.add(user);
            }
        }
        if (searchResults.isEmpty()) searchResults = null;
        return searchResults;
    }

    public static void addEventForUser(User user, RegisteredEvent newEvent) throws EventAlreadyExist {
        user.checkEventExisting(newEvent.getEventName());
        user.getRegisteredEvents().add(newEvent);
        user.addToTotalCost(newEvent.getCost());
    }

    public static List<RegisteredEvent> searchEvents(String searchTerm) {
        List<RegisteredEvent> searchResults = new ArrayList<>();
        String searchTermLowerCase = searchTerm.toLowerCase(); // Convert search term to lowercase

        for (RegisteredEvent event : AdminControl.getAllEvents()) {
            String userNameLowerCase = event.getEventName(); // Convert event to lowercase
            if (userNameLowerCase.contains(searchTermLowerCase)) { // Partial match check
                searchResults.add(event);
            }
        }
        if (searchResults.isEmpty()) searchResults = null;
        return searchResults;
    }

    public static void deleteEvent(RegisteredEvent event) throws ServiceNotFoundException, EventNotFoundException {
        if (event != null) {
            User user = getUserOfEvent(event);
            List<ServiceProvider> serviceProviders = event.getServiceProviders();
            int x = serviceProviders.size();
            for (int i = 0; i < x; i++) {
                EventsControl.deleteService(event, serviceProviders.get(0));
            }
            user.getRegisteredEvents().remove(event);
            user.setTotalCost(user.getTotalCost()-event.getCost());
            EventPlanner.getUsersEventsMap().remove(event);
        }

    }


    public static User getUserOfEvent(RegisteredEvent event) throws EventNotFoundException {
        List<User> allUsers = EventPlanner.getUsers();
        for (User user : allUsers) {
            for (RegisteredEvent element : user.getRegisteredEvents()) {
                if (event == element) return user;
            }
        }
        throw new EventNotFoundException();

    }
}