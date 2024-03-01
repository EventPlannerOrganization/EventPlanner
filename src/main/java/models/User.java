package models;

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
    }


    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public List<RegisteredEvent> getRegisteredEvent() {
        return registeredEvents;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(totalCost, user.totalCost) == 0 && usertype == user.usertype && Objects.equals(registeredEvents, user.registeredEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usertype, registeredEvents, totalCost);
    }
}

