package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends Person {

    public ServiceProvider() {

    }

    public List<Request> getRequests() {
        return requests;
    }

    private final List <Request> requests=new ArrayList<>();

    private List<Service> services = new ArrayList<>();
    private List<LocalDate> bookedDates = null;
    private boolean isPackageProvider = false;

    public ServiceProvider(ServiceProvider cpy){
        super(new Name(cpy.getName().getfName(), cpy.getName().getmName(), cpy.getName().getlName())
                , new Authentication(cpy.getAuthentication().getUsername(), cpy.getAuthentication().getPassword()),
                new Address(cpy.getAddress().getCountry(), cpy.getAddress().getCity()),
                new ContactInfo(cpy.getContactInfo().getEmail(),cpy.getContactInfo().getPhoneNumber()));

        for(Service cpyService  : cpy.getServices()){
            Service service=new Service(cpyService.getServiceType(),cpyService.getPrice(),cpyService.getDescription());
            this.getServices().add(service);
        }
    }
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

    public List<LocalDate> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<LocalDate> bookedDates) {
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
            result =  "\nService Provider "+super.getName().toString()+"\n"+ services.get(0);
        }
        //this else statement not correct, this must print list of services...
        else{
            StringBuilder pack=new StringBuilder("Offer Package items:\n");
            int counter=1;
            for(Service element:services){
                pack.append(counter);
                pack.append("- ");
                pack.append(element.toString());
                pack.append("\n");
                counter++;
            }
            result =  "\n\tService Provider "+super.getName().toString()+"\n"+ pack   ;
        }
        return result;
    }

    public  double calculateServiceProviderPrice( ){
        double price=0;
        for (Service element:services){
            price+=element.getPrice();
        }
        return price;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }




}