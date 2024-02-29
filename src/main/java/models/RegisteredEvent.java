package models;

import java.util.Date;
import java.util.List;

public class RegisteredEvent {
    private List<ServiceProvider> serviceProviders;
    private Date date;

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

}
