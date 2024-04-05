package models;

import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
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
    public static final String NASER = "Naser";
    public static final String MOHAMMAD = "Mohammad";
    public static final String PALESTINE = "Palestine";
    public static final String NABLUS = "Nablus";
    public static final String TESING = "tesing";
    public static final String MAIL = "3sfr3sfr@gmail.com";
    public static final String TULKARM = "tulkarm";
    public static final String BBAA_12 = "bbaa12";

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

    public static Person checkUser(String username) throws UserNotFoundException {
        List<Person> result= users.stream().filter(user -> user.getAuthentication().getUsername().equals(username)).toList();
        if (result.isEmpty())
            throw new UserNotFoundException();

        return result.get(0);
    }
    public static  boolean checkEmailIfExist(String email)  {
        List<Person> result= users.stream().filter(user -> user.getContactInfo().getEmail().equals(email)).toList();
        return !result.isEmpty();
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
    public static void signout() {
        EventPlanner.setCurrentUser(null);
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

        User user = new User(new Name(NASER, MOHAMMAD, "Abu-Safiehh"),
                new Authentication(NASER, "Naser123$"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("s12113028@stu.najah.edu ","05997155844")
        );

        User user9 = new User(new Name("Mariam", "Mohammaqd", "Jamil"),
                new Authentication("Mariam03", "Mariam123$"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("s12199887@stu.najah.eduu","05997155845")
        );
        user9.setUsertype(UserType.ADMIN);
        EventPlanner.addUser(user9);


        EventPlanner.addUser(user);
        List<Service>services=new ArrayList<>();
        services.add(  new Service(ServiceType.DJ,3200, TESING));
        ServiceProvider serviceProvider = new ServiceProvider(new Name("mo","munir","shadiid"),
                new Authentication("mohammad03","Mohammad123$"),new Address(PALESTINE,"tulkarrm"),
                new ContactInfo(MAIL,"9412414244"),
                services);
        EventPlanner.addUser(serviceProvider);

        List<Service>services00=new ArrayList<>();
        services00.add(  new Service(ServiceType.VENUE,3200, "Tulkarm venue"));
        ServiceProvider vinueProvider = new ServiceProvider(new Name("Karam",MOHAMMAD,"khallid"),
                new Authentication("Karam03","Mohammad123$"),new Address(PALESTINE,"tulkarrm"),
                new ContactInfo(MAIL,"94124142"),
                services00);
        EventPlanner.addUser(vinueProvider);




        List<Service>services2=new ArrayList<>();
        services2.add(  new Service(ServiceType.SECURITY,3200,TESING));
        ServiceProvider serviceProvider2 = new ServiceProvider(new Name("baha","khaliid","alawneh"),
                new Authentication("baha02","0000"),new Address(PALESTINE, TULKARM),
                new ContactInfo("moh@gmail.com","94124142"),
                services2);
        EventPlanner.addUser(serviceProvider2);

        List<Service>services3=new ArrayList<>();
        services3.add(  new Service(ServiceType.PHOTOGRAPHY,3200,TESING));
        services3.add(new Service(ServiceType.SECURITY,3200,TESING));
        ServiceProvider serviceProvider3 = new ServiceProvider(new Name("jamiil","munir","shaddid"),
                new Authentication("Ibrahim160", BBAA_12),new Address(PALESTINE,TULKARM),
                new ContactInfo("moha@gmail.com","94124123"),
                services3);
        EventPlanner.addUser(serviceProvider3);

        services3.add(  new Service(ServiceType.CLEANING,3200,TESING));
        ServiceProvider serviceProvider4 = new ServiceProvider(new Name("jaamil",MOHAMMAD,"shadiid"),
                new Authentication("saleem04",BBAA_12),new Address(PALESTINE,TULKARM),
                new ContactInfo("moham@gmail.com","94312412"),
                services3);
        EventPlanner.addUser(serviceProvider4);

        List<Service>services5=new ArrayList<>();
        services5.add(  new Service(ServiceType.CLEANING,3200,TESING));
        ServiceProvider serviceProvider5 = new ServiceProvider(new Name("jammil",MOHAMMAD,"shaAdid"),
                new Authentication("hamid02",BBAA_12),new Address(PALESTINE,TULKARM),
                new ContactInfo("mo@gmail.com","941241222"),
                services5);
        EventPlanner.addUser(serviceProvider5);
        List<Service> service6=new ArrayList<>();
        service6.add(new Service(ServiceType.CLEANING,2000,"helllo"));
        ServiceProvider serviceProvider6 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("aliii",BBAA_12),new Address("palestine",TULKARM),
                new ContactInfo("mo@gmail.com","9412412"),
                service6);
        EventPlanner.addUser(serviceProvider6);

        User user2 = new User(new Name("khalid", MOHAMMAD, "Abu-Safieeh"),
                new Authentication("khalid", "123"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("s12113028@stu.najah.edu","059971558324")
        );
        EventPlanner.addUser(user2);

        User user3 = new User(new Name("sam", MOHAMMAD, "Abu-Safiieh"),
                new Authentication("Karim", "123"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("s12199887@stu.najah.edu","059971558234")
        );

        EventPlanner.addUser(user3);
        User user4 = new User(new Name("Nasssar", MOHAMMAD, "Abu-Safieh"),
                new Authentication("Nassar", "123"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("s12199887@stu.najah.edu","0599715584")
        );
        EventPlanner.addUser(user4);
        User user5 = new User(new Name("Nassar", MOHAMMAD, "Abu-Safieh"),
                new Authentication("ramii", "123"),
                new Address(PALESTINE, NABLUS),
                new ContactInfo("ramii@stu.najah.edu","0599715584")
        );
        EventPlanner.addUser(user5);
        List<ServiceProvider> serviceProviders=new ArrayList<>();
        serviceProviders.add(serviceProvider);
        serviceProviders.add(serviceProvider2);
        LocalDate localDate=LocalDate.of(2024,9,10);
        List<String> emails=new ArrayList<>();
        emails.add("s12113028@stu.najah.edu");
        emails.add(MAIL);

        RegisteredEvent registeredEvent5=new RegisteredEvent("Wedding Celebration",
                serviceProviders,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        registeredEvent5.setLocation("Gaza Grand Hall");
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
        registeredEvent4.setLocation("Tulkarm Terrace");

        user.getRegisteredEvents().add(registeredEvent4);
        eventUserMap.put(registeredEvent4,user);

        localDate=LocalDate.of(2024,4,10);
        List<ServiceProvider> prov1=new ArrayList<>(serviceProviders);

        RegisteredEvent registeredEvent3=new RegisteredEvent("wedding party",
                prov1,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        registeredEvent3.setLocation("Birzeit Ballroom");

        user2.getRegisteredEvents().add(registeredEvent3);
        eventUserMap.put(registeredEvent3,user3);

        List<ServiceProvider> serviceProviders22=new ArrayList<>();
        serviceProviders.add(serviceProvider3);
        serviceProviders22.add(serviceProvider4);
        RegisteredEvent registeredEvent2=new RegisteredEvent("Birthday Bash",
                serviceProviders22,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails);
        registeredEvent2.setLocation("Qalqilya Quarters");

        user3.getRegisteredEvents().add(registeredEvent2);
        eventUserMap.put(registeredEvent2,user3);
        List<ServiceProvider> prov2=new ArrayList<>(serviceProviders);

        RegisteredEvent registeredEvent1=new RegisteredEvent("Food Festival",
                prov2,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders22),
                emails);
        registeredEvent1.setLocation("Illar Hills");

        user3.getRegisteredEvents().add(registeredEvent1);
        eventUserMap.put(registeredEvent1,user3);
        localDate=LocalDate.of(2024,8,10);
        List<ServiceProvider> prov3=new ArrayList<>(serviceProviders);

        RegisteredEvent registeredEvent=new RegisteredEvent("wedding party",
                prov3,localDate,
                calculateTotalPriceForMultiProviders(serviceProviders),
                emails);
        registeredEvent.setLocation("null");

        for(ServiceProvider element:serviceProviders){
            element.getBookedDates().add(localDate);
        }
        user4.getRegisteredEvents().add(registeredEvent);
        eventUserMap.put(registeredEvent,user4);
        localDate=LocalDate.of(2024,4,10);
        List<ServiceProvider> serviceProviderList=new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        serviceProviderList.add(serviceProvider5);
        RegisteredEvent registeredEvent6=new RegisteredEvent("Bash",
                serviceProviderList,localDate,
                calculateTotalPriceForMultiProviders(serviceProviderList),
                emails);
        registeredEvent6.setLocation("illar");

        user5.getRegisteredEvents().add(registeredEvent6);
        for(ServiceProvider element:serviceProviderList){
            element.getBookedDates().add(localDate);
        }

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
        List<Service>serviceList= new ArrayList<>();
        serviceList.add(  new Service(ServiceType.DJ,1400,"Best Sound Quality And Music"));

        ServiceProvider serviceProvider = new ServiceProvider(new Name("Mohammed","Munir","Shadid"),
                new Authentication("moshadid","Mo2003@@"),
                new Address(PALESTINE,"Tulkarem"),
                new ContactInfo(MAIL,"0598772189"),
                serviceList);
        EventPlanner.addUser(serviceProvider);


        List<ServiceProvider>serviceProviderList = new ArrayList<>();
        serviceProviderList.add(serviceProvider);
        User user1 = new User(new Name(NASER,"Mohammed","Abu Safieh"),
                              new Authentication("naserabusafieh","Naser2003@"),
                              new Address(PALESTINE,NABLUS),
                                new ContactInfo("naserabusafia1@gmail.com","0597094028"));
        EventPlanner.addUser(user1);
        User user2 = new User(new Name("Baha","Khaled","Alawneh"),
                new Authentication("bahaalawneh","Baha2003@"),
                new Address(PALESTINE,"Jenin"),
                new ContactInfo("bahaalawneh@gmail.com","0598223192"));
        EventPlanner.addUser(user2);

        User user3 = new User(new Name("Faiq","Fehmi","Bakri"),
                new Authentication("faiqBakri","Faiq2002@"),
                new Address(PALESTINE,NABLUS),
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
        ServiceProvider serviceProvider7 = new ServiceProvider(new Name("jamil","mohammad","shadid"),
                new Authentication("osamah",BBAA_12),new Address("palestine",TULKARM),
                new ContactInfo("osamah@gmail.com","9412412"),
                serviceList);
        EventPlanner.addUser(serviceProvider7);

    }
}
