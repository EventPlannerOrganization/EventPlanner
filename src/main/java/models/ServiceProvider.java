package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends Person {
    private List<Service> services = new ArrayList<>();
    private List<LocalDate> bookedDates = null;
    private boolean isPackageProvider = false;
    private double price;

    public ServiceProvider(Name name, Authentication authentication, Address address, ContactInfo contactInfo, List<Service> service,double price) {

        super(new Name(name.getfName(), name.getmName(), name.getlName())
                , new Authentication(authentication.getUsername(), authentication.getPassword()),
                new Address(address.getCountry(), address.getCity()),
                new ContactInfo(contactInfo.getEmail(), contactInfo.getPhoneNumber()));

        this.price=price;
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
            result =  "\n\tService Provider "+super.getName().toString()+"\n"+ services.get(0)+"\n\tPrice: "+price+"$"   ;
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
            result =  "\n\tService Provider "+super.getName().toString()+"\n"+ pack + "\n\tPrice: "+price+"$"  ;
        }
        return result;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}