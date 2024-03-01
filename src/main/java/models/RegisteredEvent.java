package models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RegisteredEvent {
    private String eventName;
    private String location;
    private List<ServiceProvider> serviceProviders;
    private Date date;
    private double cost;
    private List<String> guestsEmails;

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RegisteredEvent(List<ServiceProvider> serviceProviders, Date date) {
        this.serviceProviders = serviceProviders;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public RegisteredEvent() {
    }



    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    public void addToCost(double added) {
        cost += added;
    }

    public List<String> getGuestsEmails() {
        return guestsEmails;
    }

    public void setGuestsEmails(List<String> guestsEmails) {
        this.guestsEmails = guestsEmails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredEvent that = (RegisteredEvent) o;
        return Double.compare(cost, that.cost) == 0 && Objects.equals(eventName, that.eventName) && Objects.equals(location, that.location) && Objects.equals(serviceProviders, that.serviceProviders) && Objects.equals(date, that.date) && Objects.equals(guestsEmails, that.guestsEmails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, location, serviceProviders, date, cost, guestsEmails);
    }
}
