package controllers;

import exceptions.UserIsAlreadyExist;

import exceptions.WeakPasswordException;
import helpers.PasswordChecker;
import models.*;

import java.util.List;

public class SignUp {
    private SignUp() {

    }
    public static void signUpServiceProvider(Name name, Address address, Authentication authentication, ContactInfo contactInfo, List<Service> service) throws UserIsAlreadyExist, WeakPasswordException {
      if(!PasswordChecker.isStrongPassword(authentication.getPassword())) {
          throw new WeakPasswordException();
      }
       ServiceProvider serviceProvider=new ServiceProvider(name,authentication,address,contactInfo,service);
        EventPlanner.addUser(serviceProvider);


    }

    public static void signUpUser(Name name, Address address, Authentication authentication, ContactInfo contactInfo) throws UserIsAlreadyExist, WeakPasswordException {
        if(!PasswordChecker.isStrongPassword(authentication.getPassword()))
            throw new WeakPasswordException();
        User user=new User(name,authentication,address,contactInfo);
        EventPlanner.addUser(user);
    }
}
