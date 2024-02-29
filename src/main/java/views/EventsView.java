package views;



import controllers.EventsControl;
import enumerations.ServiceType;
import models.EventPlanner;
import models.Person;
import models.ServiceProvider;
import printers.MenusPrinter;

import java.util.*;
import java.util.logging.Logger;



public class EventsView {
    private static final Logger logger=Logger.getLogger(EventsView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);

    public void registerEventView(){
        List<ServiceProvider> providers;
    logger.info("To get started, please provide the following information: \n* Enter Event Name");
    String name=scanner.nextLine();
    logger.info("* Enter Date of your event \n - Day (1-31): ");
    int day=scanner.nextInt();
    logger.info(" - Month (1-12): ");
    int month=scanner.nextInt();
    logger.info("- Year (e.g., 2003): ");
    int year=scanner.nextInt();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month+1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date =calendar.getTime();
        providers = EventPlanner.getServiceProviders().stream().filter(provider -> ! provider.getBookedDates().contains(date)).toList();

        logger.info("Add Services:\n");
        boolean again=false;
        do {
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
            //List <ServiceProvider> result = providers.stream().filter(provider -> provider.getServices().getFirst().getServiceType().equals(serviceType)).toList();




        }while (again);
        EventsControl.addEvent(date,name);

    }


}
