package views;



import controllers.EventsControl;
import enumerations.ServiceType;
import models.EventPlanner;
import models.Service;
import models.ServiceProvider;
import printers.MenusPrinter;

import java.util.*;
import java.util.logging.Logger;



public class EventsView {
    private static final Logger logger=Logger.getLogger(EventsView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private static double cost;
    private EventsView() {

    }
    public static void registerEventView(){
    List<ServiceProvider> providers;
    logger.info("To get started, please provide the following information: \n* Enter Event Name");
    String name=scanner.nextLine();
    logger.info("* Enter Date of your event \n - Day (1-31): ");
    int day=scanner.nextInt();
    logger.info(" - Month (1-12): ");
    int month=scanner.nextInt();
    logger.info("- Year : ");
    int year=scanner.nextInt();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date =calendar.getTime();
        providers = EventPlanner.getServiceProviders().stream().filter(provider -> ! provider.getBookedDates().contains(date)).toList();

        logger.info("Add Services:\n");
        List <ServiceProvider> addedProviders= addingProcess(providers,date);
        logger.info("Add guests :\n");
        List<String> guestsEmails =readeGuestsEmails();
        EventsControl.addEvent(date,name,addedProviders,cost,guestsEmails);
    }



    private static List<ServiceProvider> addingProcess(List<ServiceProvider> providers,Date date){
        cost=0;
        boolean again=true;
        List <ServiceProvider> addedProviders=new ArrayList<>();
        scanner.nextLine();
        while(again) {
            logger.info("Select one:\n");
            MenusPrinter.printServicesMenu();

            String serviceNum = scanner.nextLine();
            ServiceType serviceType = switch (serviceNum) {
                case "1" -> ServiceType.DJ;
                case "2" -> ServiceType.Photography;
                case "3" -> ServiceType.Security;
                case "4" -> ServiceType.Cleaning;
                default -> null;
            };
            List<ServiceProvider> filteredProvidersList = providers.stream().filter(provider -> provider.getServices().get(0).getServiceType().equals(serviceType)).toList();
            if (filteredProvidersList.isEmpty())
                logger.info("No Services Available:\nUnfortunately, there are no services available for the specified service type and time.\n");
            else {
                MenusPrinter.printServicesList(filteredProvidersList);
                int addedNumber = Integer.parseInt( scanner.nextLine());
                if(addedNumber<=filteredProvidersList.size()){
                filteredProvidersList.get(addedNumber-1).getBookedDates().add(date);
                for(Service e:filteredProvidersList.get(addedNumber-1).getServices()){//this loop will calculate the packeges by summing its services prices,and this must replace with another functionality ...
                    cost+=e.getPrice();
                }
                addedProviders.add(filteredProvidersList.get(addedNumber - 1));}
            }
            //this called Text block which begin with """, sonarLint need to useing it insted string
            logger.info("""
                    Do you want to add another service?
                    - Enter 'y' to add another service.
                    - Enter 'n' to finish and proceed.
                    """);
            again=againChecker();
        }
    return addedProviders;
    }


    private static boolean againChecker(){
        boolean again=true;
        String choice = scanner.nextLine();
        boolean wrongChoice = true;
        while (wrongChoice) {
            if (choice.equals("y")) {
                wrongChoice = false;
            } else if (choice.equals(("n"))) {
                again = false;
                wrongChoice = false;
            } else{
                logger.info("Invalid choice. Please enter either 'y' or 'n'.\n");
                 choice = scanner.nextLine();
            }
        }
        return again;
    }


    private static List<String> readeGuestsEmails(){
        List<String> guestsEmails=new ArrayList<>();
        logger.info("Enter the number of guests. You can adjust this number and modify the list as needed:\n");
        int serviceNum = Integer.parseInt(scanner.nextLine());
        logger.info("For each guest, please enter their email address:\n");
        for(int i=0;i<serviceNum;i++){
            String s="\n"+(i+1)+"- ";
            logger.info(s);
            String email = scanner.nextLine();
            guestsEmails.add(email);
        }

        return  guestsEmails;

    }
}
