package models;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ServiceProvider extends Person {
    private List<Service> services = new ArrayList<>();
    private List<Date> bookedDates = null;
    private boolean isPackageProvider = false;

    public ServiceProvider(Name name, Authentication authentication, Address address, ContactInfo contactInfo, List<Service> service) {

        super(new Name(name.getfName(), name.getmName(), name.getlName())
                , new Authentication(authentication.getUsername(), authentication.getPassword()),
                new Address(address.getCountry(), address.getCity()),
                new ContactInfo(contactInfo.getEmail(), contactInfo.getPhoneNumber()));


        services.addAll(service);
        if (services.size() > 1) isPackageProvider = true;
        bookedDates=new ArrayList<>();
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Date> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<Date> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public boolean isPackageProvider() {
        return isPackageProvider;
    }

    public void setPackageProvider(boolean packageProvider) {
        isPackageProvider = packageProvider;
    }

    @Override
    public String toString() {
        String result;

        if (!isPackageProvider) {
            result =  "\n\tService Provider "+super.getName().toString()+"\n"+ services.get(0) + "\tbookedDates: " + bookedDates  ;
        }
        //this else statement not correct, this must print list of services...
        else{
            result =  "\tService Provider "+super.getName().toString()+"\n"+ services.get(0) + "\tbookedDates:  " + bookedDates  ;
        }
        return result;
    }
}