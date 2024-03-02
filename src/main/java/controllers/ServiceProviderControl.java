package controllers;

import models.EventPlanner;
import models.ServiceProvider;
import views.ServiceProviderView;


import java.util.Scanner;
import java.util.logging.Logger;

public class ServiceProviderControl {
    private static final Logger logger = Logger.getLogger(ServiceProviderControl.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    private ServiceProviderControl(){

    }
    public static void showServiceProviderServices(){
        ServiceProvider currentprovider = (ServiceProvider) EventPlanner.getCurrentUser();
        for(int i = 0 ; i <currentprovider.getServices().size();i++) {
            String st = currentprovider.getServices().get(i).toString();
            logger.info(st);
        }
        logger.info("To Return Back Enter B");
      String choice =  scanner.nextLine();
      while (!(choice.equals("B")||choice.equals("b"))){
          logger.info("To Return Back Enter B");
          choice =  scanner.nextLine();
      }
        ServiceProviderView.providerMenu();
    }
    public static void  signout(){
        EventPlanner.setCurrentUser(null);
    }


}
