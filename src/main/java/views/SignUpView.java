package views;

import Exceptions.UserIsAlreadyExist;

import Exceptions.WeakPasswordException;
import controllers.SignUp;
import enumerations.ServiceType;
import helpers.ChoiceChecker;
import helpers.PasswordChecker;
import models.*;
import printers.MenusPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger=Logger.getLogger(SignUpView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private SignUpView() {

    }
    public static void signUpView() throws UserIsAlreadyExist, WeakPasswordException {

List<Service> list = new ArrayList<>();
        MenusPrinter.printSignUpAsMenu();
        String signUpAs=scanner.nextLine();
        while (!ChoiceChecker.isOneOrTwo(signUpAs)) {
            logger.info("Enter Valid Choice: ");
            signUpAs=scanner.nextLine();

        }
        logger.info("Enter Your first name: ");
        String fname=scanner.nextLine();
        logger.info("Enter Your middle name: ");
        String mname=scanner.nextLine();
        logger.info("Enter Your last name: ");
        String lname=scanner.nextLine();
        logger.info("Enter Your Country: ");
        String country=scanner.nextLine();
        logger.info("Enter city: ");
        String city=scanner.nextLine();
        logger.info("Enter Your email: ");
        String email=scanner.nextLine();
        logger.info("Enter Your password: ");
        String password=scanner.nextLine();
        while (!PasswordChecker.isStrongPassword(password)) {
            logger.info("re enter Your password: ");
            password=scanner.nextLine();
        }
        logger.info("Enter Your phone number: ");
        String phone=scanner.nextLine();
        if(signUpAs.equals("2")) {
            logger.info("if you want to become Package Provider Enter 1 ,Else Enter 2 ");
            String choiceBetweenPackageOrNormal = scanner.nextLine();
            while (!ChoiceChecker.isOneOrTwo(choiceBetweenPackageOrNormal)) {
                logger.info("Enter Valid Choice: ");
                choiceBetweenPackageOrNormal =scanner.nextLine();

            }
            if(choiceBetweenPackageOrNormal.equals("2")) {

                list = serviceList("1");


            }
            else if(choiceBetweenPackageOrNormal.equals("1")){
                MenusPrinter.printServicesMenu();
                logger.info("Enter Number of Services (Greater Than One): ");
                String numberOfServices= scanner.nextLine();
                while(!ChoiceChecker.isValidNumberOfServices(numberOfServices)){
                    logger.info("Enter A Valid Number of Services Greater Than One: ");
                    numberOfServices= scanner.nextLine();

                }
                list = serviceList(numberOfServices);
            }

            SignUp.signUpServiceProvider(new Name(fname,mname,lname)
                    ,new Address(country,city)
                    ,new Authentication(email,password)
                    ,new ContactInfo(email,phone)
                    ,list);


        } else if (signUpAs.equals("1")) {
            SignUp.signUpUser(new Name(fname,mname,lname)
                    ,new Address(country,city)
                    ,new Authentication(email,password)
                    ,new ContactInfo(email,phone)
                    );

        }

    }
    public static List<Service> serviceList(String numberOfService){
        List <Service> list = new ArrayList<>();
        for(int i =0;i<Integer.parseInt(numberOfService);i++){
        MenusPrinter.printServicesMenu();
            logger.info("Enter The Number Of The Service You Want To Provide : ");

            String servicenum=scanner.nextLine();
            while (!ChoiceChecker.isValidNumberOfServices(servicenum)){
                 servicenum=scanner.nextLine();
                logger.info("Enter Valid Number For The Service : ");

            }
        ServiceType serviceType = switch (servicenum) {
            case "1" -> ServiceType.DJ;
            case "2" -> ServiceType.Photography;
            case "3" -> ServiceType.Security;
            case "4" -> ServiceType.Cleaning;
            default -> null;
        };
        logger.info("Enter price for this service: ");
        double price=Double.parseDouble(scanner.nextLine());
        logger.info("description about the service: ");
        String discription=scanner.nextLine();
        list.add(new Service(serviceType,price,discription));
    }
        return list;
    }
}
