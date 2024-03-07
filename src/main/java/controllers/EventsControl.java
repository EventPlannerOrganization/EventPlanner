package controllers;

import Exceptions.EventAlreadyExist;
import Exceptions.EventNotFound;
import Exceptions.ServiceNotFoundException;
import Exceptions.UserNotFoundException;
import models.*;
import views.EventsView;


import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class EventsControl {
    private static final Logger logger = Logger.getLogger(EventsControl.class.getName());

    private EventsControl() {
    }
    public static void updateEventInformation(String eventName,String field,String value) throws EventNotFound {
        User user= (User) EventPlanner.getCurrentUser();
        if(!user.isThisEventExist(eventName)) {
            throw new EventNotFound();
        }
        if(field.equalsIgnoreCase("name")) {
            editEventName(user.getEventByName(eventName),value);
        }
        else if (field.equalsIgnoreCase("location")) {
            editEventLocation(user.getEventByName(eventName),value);
        }
    }
    public static void addEvent(LocalDate date, String name, List<ServiceProvider> serviceProviders, double cost, List<String> guestsEmails) throws EventAlreadyExist {
        RegisteredEvent registeredEvent =new RegisteredEvent(name,serviceProviders,date,cost,guestsEmails);
           User currentUser = (User) (EventPlanner.getCurrentUser());

              currentUser.checkEventExisting(name);
              currentUser.getRegisteredEvents().add(registeredEvent);
              currentUser.addToTotalCost(cost);



    }
    public static void editEventName(RegisteredEvent event,String newName) {
        event.setEventName(newName);
    }
    public static void editEventLocation(RegisteredEvent event,String newLocation) {
        event.setLocation(newLocation);
    }

    public static void editEventDate(RegisteredEvent event,String newDate) {
        event.setDate(LocalDate.parse(newDate));
    }

    public static void deleteService(RegisteredEvent event, ServiceProvider service) throws  ServiceNotFoundException {
        event.checkServiceProviderExisting(service);
        event.getServiceProviders().remove(service);
        service.getBookedDates().remove(event.getDate());
        event.subFromCost(service.getServices().get(0).getPrice());// note this does not include package provider

    }
    public static void addNewGuests(RegisteredEvent event,List<String> newGuests){
        event.getGuestsEmails().addAll(newGuests);
    }
    public static void deleteGuest(String guest,RegisteredEvent event){
    event.getGuestsEmails().remove(guest);

    }}
