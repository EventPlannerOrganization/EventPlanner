package models;

import Exceptions.ServiceNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisteredEvent {
    private String eventName;
    private String location;
    private List<ServiceProvider> serviceProviders;
    private LocalDate date;
    private double cost;
    private List<String> guestsEmails;

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public RegisteredEvent(String eventName, List<ServiceProvider> serviceProviders, LocalDate date, double cost, List<String> guestsEmails) {
        this.eventName = eventName;
        this.serviceProviders = serviceProviders;
        this.date = date;
        this.cost = cost;
        this.guestsEmails = guestsEmails;
    }
    public RegisteredEvent(String eventName, LocalDate date, double cost, List<String> guestsEmails) {
        this.eventName = eventName;
        this.date = date;
        setCost(cost);
        this.guestsEmails = guestsEmails;
        serviceProviders=new ArrayList<>();
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
        return Double.compare(cost, that.cost) == 0 &&
                Objects.equals(eventName, that.eventName) &&
                Objects.equals(location, that.location) &&
                Objects.equals(date, that.date) &&
                Objects.equals(guestsEmails, that.guestsEmails);

    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, location, date, cost, guestsEmails);
    }

    @Override
    public String toString() {

        return
                "\u001B[1m"+"\u001B[32m"+"Event Name: " + eventName +"\u001B[0m"+
                "\u001B[34m"+"\nServices: \n" + getServicesDetails() +
                "Date: " + date +
                "\nTotal Cost: " + cost +
                "\nGuests List: \n" + guestsEmails +"\n\n"+"\u001B[0m";
    }


public String toString2(){
        return
                "\nEvent Name : "+ eventName+
                 "\n Date : " +date +
                  "\n Guests List :\n"+guestsEmails;
}

    private String getServicesDetails() {
        StringBuilder services = new StringBuilder();
        int count = 1;
        if (serviceProviders != null) { // Check if serviceProviders is not null
            for (ServiceProvider element : serviceProviders) {
                if (element != null) { // Check if the element is not null
                    services.append("\t");
                    services.append(count);
                    services.append("- ");
                    services.append(element);
                    count++;
                }
            }
       }
        return services.toString();
    }
    public void addServices(List<ServiceProvider> addedServices) {
        getServiceProviders().addAll(addedServices);
        for(ServiceProvider element:addedServices){
            this.cost+=element.getServices().get(0).getPrice(); //note this does not include packeges providers
            element.getBookedDates().add(this.getDate());

    }}
    public void subFromCost(double deletedCost){
        this.cost-=deletedCost;
    }
    public void checkServiceProviderExisting(ServiceProvider deletedProvider) throws ServiceNotFoundException {
        if(!serviceProviders.contains(deletedProvider))
         {
            throw new ServiceNotFoundException();
        }


    }
}
