package controllers;

import Exceptions.*;
import models.*;
import java.time.LocalDate;
import java.util.List;
import static controllers.AdminControl.getUserOfEvent;

public class EventsControl {

    private EventsControl() {
    }


    public static void addEvent(LocalDate date, String name, double cost, List<String> guestsEmails) throws EventAlreadyExist {
        RegisteredEvent registeredEvent = new RegisteredEvent(name,  date ,cost, guestsEmails);
        User currentUser = (User) (EventPlanner.getCurrentUser());

        currentUser.checkEventExisting(name);
        currentUser.getRegisteredEvents().add(registeredEvent);
        currentUser.addToTotalCost(cost);
        EventPlanner.getUsersEventsMap().put(registeredEvent,currentUser);

    }



    public static void editEventName(RegisteredEvent event, String newName) throws EventAlreadyExist, EventNotFoundException {
        User user=getUserOfEvent(event);
        user.checkEventExisting(newName);
        event.setEventName(newName);
    }

    public static void editEventLocation(RegisteredEvent event, String newLocation) {
        event.setLocation(newLocation);
    }

    public static void deleteService(RegisteredEvent event, ServiceProvider service) throws ServiceNotFoundException {
        event.checkServiceProviderExisting(service);
        event.getServiceProviders().remove(service);
        service.getBookedDates().remove(event.getDate());
        event.subFromCost(service.getServices().get(0).getPrice());// note this does not include package provider
    }

    public static void addServices(RegisteredEvent event, List<ServiceProvider> addedServices) throws AlreadyBookedDateException {
        List<ServiceProvider> list = addedServices.stream().filter(provider -> provider.getBookedDates().contains((event.getDate()))).toList();
        if (!list.isEmpty()) throw new AlreadyBookedDateException();
        event.addServices(addedServices);
    }

    public static void addNewGuests(RegisteredEvent event, List<String> newGuests) {
        event.getGuestsEmails().addAll(newGuests);
    }

    public static void deleteGuest(String guest, RegisteredEvent event) {
        event.getGuestsEmails().remove(guest);

    }
}
