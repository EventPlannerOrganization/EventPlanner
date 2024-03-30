package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceProvider extends Person {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceProvider that)) return false;
        if (!super.equals(o)) return false;
        return isPackageProvider == that.isPackageProvider && Objects.equals(requests, that.requests) && Objects.equals(services, that.services) && Objects.equals(bookedDates, that.bookedDates)&&getAuthentication().getUsername().equals(that.getAuthentication().getUsername()) &&
                getContactInfo().getEmail().equals(that.getContactInfo().getEmail());

    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), requests, services, bookedDates, isPackageProvider);
    }

    public ServiceProvider() {

    }

    public List<Request> getRequests() {
        return requests;
    }

    private final List <Request> requests=new ArrayList<>();

    private List<Service> services = new ArrayList<>();
    private List<LocalDate> bookedDates = null;
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

    public List<LocalDate> getBookedDates() {
        return bookedDates;
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
            result =  "\n\tService Provider "+super.getName().toString()+"\n\t"+ services.getFirst();
        }
        //this else statement not correct, this must print list of services...
        else{
            StringBuilder pack=new StringBuilder("\tOffer Package items:\n");
            int counter=1;
            for(Service element:services){
                pack.append("\t");
                pack.append(counter);
                pack.append("- ");
                pack.append(element.toString());
                pack.append("\n");
                counter++;
            }
            result =  "\n\tService Provider "+super.getName().toString()+"\n"+ pack   ;
        }
        return result+"\n";
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