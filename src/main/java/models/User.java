package models;

import Exceptions.EventAlreadyExist;
import enumerations.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Person {
    private UserType usertype;
    private List<RegisteredEvent> registeredEvents;
    private double totalCost;

    public User(Name name, Authentication authentication, Address address, ContactInfo contactInfo) {
        super(name, authentication, address, contactInfo);
        usertype = UserType.USER;
        registeredEvents = new ArrayList<>();
        totalCost = 0;
    }


    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }


    public void setRegisteredEvent(List<RegisteredEvent> registeredEvent) {

        this.registeredEvents = registeredEvent;
    }

    public List<RegisteredEvent> getRegisteredEvents() {
        return registeredEvents;
    }

    public void setRegisteredEvents(List<RegisteredEvent> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void addToTotalCost(double newCost) {
        this.totalCost += newCost;
    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usertype, registeredEvents, totalCost);
    }

    public boolean checkEventExisting(RegisteredEvent event) {
        List<RegisteredEvent> similars = this.registeredEvents.stream().filter(e -> e.getEventName().equals(event.getEventName())).toList();
return (!similars.isEmpty() ) ;
    }

}