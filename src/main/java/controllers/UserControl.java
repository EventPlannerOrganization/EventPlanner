package controllers;

import Email.EmailService;
import Exceptions.EmptyList;
import models.EventPlanner;
import models.RegisteredEvent;
import models.Request;
import models.ServiceProvider;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class UserControl {
    private UserControl() {

    }

    public static void sendRequestToServiceProvider(ServiceProvider serviceProvider, LocalDate date, RegisteredEvent event) throws IOException, MessagingException {
        String message = "hi " + serviceProvider.getName().getfName() + ", you have a request from " + EventPlanner.getCurrentUser().getName() + "to have your service in his event " + "\nat: " + date;
        serviceProvider.addRequest(new Request(EventPlanner.getCurrentUser().getContactInfo().getEmail(), serviceProvider.getContactInfo().getEmail(), message, event));
        EmailService emailService = new EmailService();
//        emailService.sendEmail(serviceProvider.getContactInfo().getEmail(),"request-body");
    }
}
