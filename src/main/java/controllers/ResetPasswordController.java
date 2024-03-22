package controllers;

import models.Person;

public class ResetPasswordController {
    public static void changePasswordForUser(Person person,String newPassword){
        person.getAuthentication().setPassword(newPassword);
    }
}
