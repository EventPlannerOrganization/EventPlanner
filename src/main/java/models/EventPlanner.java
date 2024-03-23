package models;

import Exceptions.UserIsAlreadyExist;
import Exceptions.UserNotFoundException;
import enumerations.ServiceType;
import enumerations.UserType;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private static final List<Person> users = new ArrayList<>();
    private static final Map<RegisteredEvent, User> eventUserMap = new HashMap<>(); // Map to associate RegisteredEvent with User
    public static List<Person> getUsersList(){
        return users;
    }
    public static  Map<RegisteredEvent, User>  getUsersEventsMap(){
        return eventUserMap;
    }


    private EventPlanner() {
    }

    private static Person currentUser=null;

    public static void addUser(Person user) throws UserIsAlreadyExist {
        if (users.contains(user))
            throw new UserIsAlreadyExist();
        users.add(user);
    }


    public static void removeUser(Person user) throws UserNotFoundException {
        if (!users.contains(user)) {
            throw new UserNotFoundException();
        }
    }
    public static Person checkUser(String username) throws UserNotFoundException {
        List<Person> result= users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();
        if (result.isEmpty())
            throw new UserNotFoundException();

        return result.get(0);
    }
    public static  boolean checkEmailIfExist(String email)  {
        List<Person> result= users.stream().filter(user -> user.getContactInfo().getEmail().equals(email)).toList();
        if (result.isEmpty())
          return false;

        return true;
    }
    public static  Person getUserByEmail(String email) throws UserNotFoundException {
        List<Person> result= users.stream().filter(user -> user.getContactInfo().getEmail().equals(email)).toList();
        if (result.isEmpty())
            throw new UserNotFoundException();

        return result.get(0);
    }
    public static Person getUserByUsername(String username) throws UserNotFoundException {
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        result = result.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();



        return result.get(0);
    }

    public static Person getServiceProviderByUsername(String username) throws UserNotFoundException {
        List<Person> result = users.stream().filter(user -> ServiceProvider.class.isAssignableFrom(user.getClass())).toList();
        result = result.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();

        if (result.isEmpty())
            throw new UserNotFoundException();



        return result.get(0);
    }
    public static Person getCurrentUser() {
        return currentUser;

    }
    public static void setCurrentUser(Person currentUser) {
        EventPlanner.currentUser = currentUser;

    }

    public  static  List<ServiceProvider> getServiceProviders()  {
        List<Person> result = users.stream().filter(user -> ServiceProvider.class.isAssignableFrom(user.getClass())).toList();
        List <ServiceProvider> services =new ArrayList<>();
        for(Person element :result){
            services.add((ServiceProvider) element);
        }
        return services;
    }
    public static List<ServiceProvider>getPakageProviders(){
        return getServiceProviders().stream().filter(ServiceProvider::isPackageProvider).toList();
    }

    public static double calculateTotalPriceForMultiProviders(List<ServiceProvider> serviceProviders)
    {
        double totalPrice=0;
        for(ServiceProvider element:serviceProviders)
            {
            totalPrice+=element.calculateServiceProviderPrice();
            }
        return totalPrice;
    }
    public  static  List<User> getUsers(){
        List<Person> result = users.stream().filter(user -> User.class.isAssignableFrom(user.getClass())).toList();
        List <User> users =new ArrayList<>();
        User uElement;
        for(Person element :result){
            uElement=(User)element;
            if(uElement.getUsertype().equals(UserType.USER))
                users.add((User) element);
        }
        return users;
    }
    public static void initializeRepositoryWithData() throws UserIsAlreadyExist {
       cleanRepositry();

        User user = new User(new Name("Naser", "Mohammad", "Abu-Safieh"),
                new Authentication("Naser", "m123"),
                new Address("Palestine", "Nablus"),
        new ContactInfo("s12199887@stu.najah.edu","0599715584")
              );

      EventPlanner.addUser(user);
      List<Service>services=new ArrayList<>();
      services.add(  new Service(ServiceType.DJ,3200,"tesing"));
      ServiceProvider serviceProvider = new ServiceProvider(new Name("mo","munir","shadid"),
              new Authentication("mohammad03","12345"),new Address("palestine","tulkarm"),
              new ContactInfo("3sfr3sfr@gmail.com","9412412"),
            services);
      EventPlanner.addUser(serviceProvider);


        List<Service>services2=new ArrayList<>();
        services2.add(  new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider2 = new ServiceProvider(new Name("baha","khalid","alawneh"),
                new Authentication("baha02","0000"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services2);
        EventPlanner.addUser(serviceProvider2);

        List<Service>services3=new ArrayList<>();
        services3.add(  new Service(ServiceType.Photography,3200,"tesing"));
        services3.add(new Service(ServiceType.Security,3200,"tesing"));
        ServiceProvider serviceProvider3 = new ServiceProvider(new Name("jamil","munir","shadid"),
                new Authentication("Ibrahim160","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services3);
        EventPlanner.addUser(serviceProvider3);

        List<Service>services4=new ArrayList<>();
        services3.add(  new Service(ServiceType.Cleaning,3200,"tesing"));
        ServiceProvider serviceProvider4 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("saleem04","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services3);
        EventPlanner.addUser(serviceProvider4);

        List<Service>services5=new ArrayList<>();
        services5.add(  new Service(ServiceType.Cleaning,3200,"tesing"));
        ServiceProvider serviceProvider5 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("hamid02","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                services5);
        EventPlanner.addUser(serviceProvider5);
        List<Service> service6=new ArrayList<>();
        service6.add(new Service(ServiceType.Cleaning,2000,"helllo"));
        ServiceProvider serviceProvider6 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("aliii","bbaa12"),new Address("palestine","tulkarm"),
                new ContactInfo("mo@gmail.com","9412412"),
                service6);
        EventPlanner.addUser(serviceProvider6);

        User user2 = new User(new Name("khalid", "Mohammad", "Abu-Safieh"),
                new Authentication("khalid", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );
            user2.setUsertype(UserType.ADMIN);
            EventPlanner.addUser(user2);

        User user3 = new User(new Name("sam", "Mohammad", "Abu-Safieh"),
                new Authentication("Karim", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );

        EventPlanner.addUser(user3);
        User user4 = new User(new Name("Nassar", "Mohammad", "Abu-Safieh"),
                new Authentication("Nassar", "123"),
                new Address("Palestine", "Nablus"),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );
        EventPlanner.addUser(user4);
        List<ServiceProvider> serviceProviders=new ArrayList<>();
        serviceProviders.add(serviceProvider);
        serviceProviders.add(serviceProvider2);
        LocalDate localDate=LocalDate.of(2024,9,10);
        List<String> emails=new ArrayList<>();
        emails.add("s12113028@stu.najah.edu");
        emails.add("3sfr3sfr@gmail.com");

        RegisteredEvent registeredEvent5=new RegisteredEvent("Wedding Celebration",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        user.getRegisteredEvents().add(registeredEvent5);
        eventUserMap.put(registeredEvent5,user);

        localDate=LocalDate.of(2024,8,10);
        List<String> emails1=new ArrayList<>();
        emails1.add("bahaalawneh07@gmail.com");

         List<ServiceProvider> prov=new ArrayList<>(serviceProviders);
         RegisteredEvent registeredEvent4=new RegisteredEvent("open day1",
                 prov,localDate,
                 calculateTotalPriceForMultiProviders(serviceProviders),
                 emails1);
        user.getRegisteredEvents().add(registeredEvent4);
        eventUserMap.put(registeredEvent4,user);

        localDate=LocalDate.of(2024,4,10);

        RegisteredEvent registeredEvent3=new RegisteredEvent("wedding party",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        user3.getRegisteredEvents().add(registeredEvent3);
        eventUserMap.put(registeredEvent3,user3);

        List<ServiceProvider> serviceProviders22=new ArrayList<>();
        serviceProviders.add(serviceProvider3);
        RegisteredEvent registeredEvent2=new RegisteredEvent("Birthday Bash",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails);
        user3.getRegisteredEvents().add(registeredEvent2);
        eventUserMap.put(registeredEvent2,user3);
        RegisteredEvent registeredEvent1=new RegisteredEvent("Food Festival",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails);
        user3.getRegisteredEvents().add(registeredEvent1);
        eventUserMap.put(registeredEvent1,user3);
        localDate=LocalDate.of(2024,8,10);

        RegisteredEvent registeredEvent=new RegisteredEvent("wedding party",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        for(ServiceProvider element:serviceProviders){
            element.getBookedDates().add(localDate);
        }
        registeredEvent.setLocation("jerusalem");
        user4.getRegisteredEvents().add(registeredEvent);
        eventUserMap.put(registeredEvent,user4);

    }

     public static List<ServiceProvider> getServiceProvidersNotBookedinThisDate(LocalDate date) {
         return EventPlanner.getServiceProviders().stream().filter(provider -> ! provider.getBookedDates().contains(date)).toList();
     }

    public static List<ServiceProvider> getServiceProviderByServiceType(ServiceType serviceType, LocalDate date) {
        return getServiceProvidersNotBookedinThisDate(date).stream().filter(provider -> provider.getServices().get(0).getServiceType().equals(serviceType)&&!provider.isPackageProvider()).toList();
    }
    public static void cleanRepositry() {
        users.clear();
    }
    public static void initializeRepositoryWithDataForTesting() throws UserIsAlreadyExist {
        EventPlanner.cleanRepositry();
        List<Service>serviceList= new ArrayList<Service>();
        serviceList.add(  new Service(ServiceType.DJ,1400,"Best Sound Quality And Music"));

        ServiceProvider serviceProvider = new ServiceProvider(new Name("Mohammed","Munir","Shadid"),
                new Authentication("moshadid","Mo2003@@"),
                new Address("Palestine","Tulkarem"),
                new ContactInfo("3sfr3sfr@gmail.com","0598772189"),
                serviceList);
        EventPlanner.addUser(serviceProvider);


        List<ServiceProvider>serviceProviderList = new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        User user1 = new User(new Name("Naser","Mohammed","Abu Safieh"),
                              new Authentication("naserabusafieh","Naser2003@"),
                              new Address("Palestine","Nablus"),
                                new ContactInfo("naserabusafia1@gmail.com","0597094028"));
        EventPlanner.addUser(user1);
        User user2 = new User(new Name("Baha","Khaled","Alawneh"),
                new Authentication("bahaalawneh","Baha2003@"),
                new Address("Palestine","Jenin"),
                new ContactInfo("bahaalawneh@gmail.com","0598223192"));
        EventPlanner.addUser(user2);

        User user3 = new User(new Name("Faiq","Fehmi","Bakri"),
                new Authentication("faiqBakri","Faiq2002@"),
                new Address("Palestine","Nablus"),
                new ContactInfo("Faiqbakri@gmail.com","0598995326"));
        EventPlanner.addUser(user3);

       List<String> emails = new ArrayList<>();
       emails.add("samihadman@gmail.com");
       emails.add("AliSurakji@gmail.com");

        LocalDate localDate1=LocalDate.of(2024,8,10);
      RegisteredEvent registeredEvent = new RegisteredEvent("Birthday",serviceProviderList,localDate1,1400,emails);
       user1.getRegisteredEvents().add(registeredEvent);

        LocalDate localDate2=LocalDate.of(2024,9,12);
        RegisteredEvent registeredEvent2 = new RegisteredEvent("Wedding",serviceProviderList,localDate2,1400,emails);
        user2.getRegisteredEvents().add(registeredEvent2);

        LocalDate localDate3=LocalDate.of(2023,9,12);
        RegisteredEvent registeredEvent3 = new RegisteredEvent("Party",serviceProviderList,localDate3,1400,emails);
        user3.getRegisteredEvents().add(registeredEvent3);

    }
}
