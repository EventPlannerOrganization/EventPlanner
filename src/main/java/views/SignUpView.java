package views;

import Exceptions.UserIsAlreadyExist;

import controllers.SignUp;
import enumerations.ServiceType;
import helpers.PasswordChecker;
import models.*;
import printers.MenusPrinter;

import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger=Logger.getLogger(SignUpView.class.getName());
    private static final Scanner scanner=new Scanner(System.in);
    private SignUpView() {

    }
    public static void signUpView() throws UserIsAlreadyExist {
        MenusPrinter.printSignUpAsMenu();
        String signUpAs=scanner.nextLine();
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
            MenusPrinter.printServicesMenu();
            String servicenum=scanner.nextLine();
            ServiceType serviceType = switch (servicenum) {
                case "1" -> ServiceType.DJ;
                case "2" -> ServiceType.Photography;
                case "3" -> ServiceType.Security;
                case "4" -> ServiceType.Cleaning;
                default -> null;
            };
            logger.info("Enter its price: ");
            String price=scanner.nextLine();
            logger.info("description about the service: ");
            String discription=scanner.nextLine();
            SignUp.signUpServiceProvider(new Name(fname,mname,lname)
            ,new Address(country,city)
            ,new Authentication(email,password)
            ,new ContactInfo(email,phone)
            ,new Service(serviceType,Double.parseDouble(price),discription));


        } else if (signUpAs.equals("1")) {
            SignUp.signUpUser(new Name(fname,mname,lname)
                    ,new Address(country,city)
                    ,new Authentication(email,password)
                    ,new ContactInfo(email,phone)
                    );

        }

    }
}
