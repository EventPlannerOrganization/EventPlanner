package models;

import enumerations.ServiceType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ServiceProvider extends Person{
    private List <Service> services;
    private List<Date> bookedDates=null;

    public ServiceProvider(Name name,Authentication authentication,Address address,ContactInfo contactInfo,Service service) {

        super(new Name(name.getfName(), name.getmName(), name.getlName())
                ,new Authentication(authentication.getUsername(),authentication.getPassword()),
                  new Address(address.getCountry(), address.getCity()),
                  new ContactInfo(contactInfo.getEmail(),contactInfo.getPhoneNumber()));
        service=new Service(service.getServiceType(),service.getPrice(),service.getDescription());
        services=new ArrayList<>();
        services.add(service);
    }


}
