package models;

import enumerations.ServiceType;

public class Service {
    private ServiceType serviceType;
    private double price;

    private String description;


    public Service(ServiceType serviceType,double price, String description) {
        this.serviceType = serviceType;
        this.description = description;
        this.price=price;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }






    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {

        return    "\tType: " +serviceType   + "\n\tDescription: " + description ;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
