package controllers;

import Exceptions.EventAlreadyExist;
import models.*;
import views.EventsView;


import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class EventsControl {
    private static final Logger logger = Logger.getLogger(EventsControl.class.getName());

    private EventsControl() {
    }
    public static void addEvent(LocalDate date, String name, List<ServiceProvider> serviceProviders, double cost, List<String> guestsEmails) throws EventAlreadyExist {
        RegisteredEvent registeredEvent =new RegisteredEvent(name,serviceProviders,date,cost,guestsEmails);
           User currentUser = (User) (EventPlanner.getCurrentUser());

              currentUser.checkEventExisting(name);
              currentUser.getRegisteredEvents().add(registeredEvent);
              currentUser.addToTotalCost(cost);



    }
    public static void editEventName(RegisteredEvent event,String newName){
        event.setEventName(newName);
    }

    public static void deleteService(RegisteredEvent event, ServiceProvider service){
        event.getServiceProviders().remove(service);
        service.getBookedDates().remove(event.getDate());
        event.subFromCost(service.getServices().get(0).getPrice());// note this does not include package provider

    }
    public static void addNewGuests(RegisteredEvent event){
        List <String> newGuests= EventsView.readeGuestsEmails();
        event.getGuestsEmails().addAll(newGuests);
    }
    public static void deleteGuest(String guest,RegisteredEvent event){
    event.getGuestsEmails().remove(guest);

    }}
