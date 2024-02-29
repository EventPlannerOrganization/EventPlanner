package models;

import enumerations.ServiceType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ServiceProvider extends Person{
    private List <Service> services=new ArrayList<>();
    private List<Date> bookedDates=null;
    private boolean isPackageProvider=false;

    public ServiceProvider(Name name,Authentication authentication,Address address,ContactInfo contactInfo,List<Service> service) {

        super(new Name(name.getfName(), name.getmName(), name.getlName())
                ,new Authentication(authentication.getUsername(),authentication.getPassword()),
                  new Address(address.getCountry(), address.getCity()),
                  new ContactInfo(contactInfo.getEmail(),contactInfo.getPhoneNumber()));


        services.addAll(service);
        if(services.size()>1) isPackageProvider=true;
    }

    public boolean isPackageProvider() {
        return isPackageProvider;
    }

    public void setPackageProvider(boolean packageProvider) {
        isPackageProvider = packageProvider;
    }
}
