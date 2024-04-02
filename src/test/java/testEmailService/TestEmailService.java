package testEmailService;

import email.EmailService;
import exceptions.EventNotFound;
import exceptions.UserIsAlreadyExist;
import exceptions.UserNotFoundException;
import models.EventPlanner;
import models.RegisteredEvent;
import models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestEmailService {
    EmailService spyEmailService;

    @Before
    public void setup() throws FileNotFoundException {
        spyEmailService = Mockito.spy(new EmailService());
    }

    @Test
    public void testGenerateRandomCode()  {
        String code = EmailService.generateRandomString();
        assertNotNull(code);
        int generatedCode = Integer.parseInt(code);
        assertTrue(generatedCode >= 0 && generatedCode <= 9999);
    }

    @Test
    public void testSendResetPasswordCode() throws MessagingException, IOException {

        spyEmailService.sendResetPasswordCode("bahaalawneh07@gmail.com");

        verify(spyEmailService, times(1)).sendResetPasswordCode("bahaalawneh07@gmail.com");
    }

    @Test
    public void testSendEventInvitations() throws MessagingException, IOException, UserIsAlreadyExist, UserNotFoundException, EventNotFound {
        EventPlanner.initializeRepositoryWithData();
        User user = (User) EventPlanner.getUserByUsername("Naser");
        EventPlanner.setCurrentUser(user);
        RegisteredEvent registeredEvent = user.getEventByName("open day1");
        spyEmailService.sendEventInvitations(registeredEvent);
        verify(spyEmailService, times(1)).sendEventInvitations(registeredEvent);



    }
    @Test
    public void testSendEmail() throws MessagingException, IOException {
        spyEmailService.sendEmail("bahaalawneh07@gmail.com","email-body");
        verify(spyEmailService, times(1)).sendEmail("bahaalawneh07@gmail.com","email-body");
    }
    @Test
    public void testSendAdminDeleteUserEmail() throws MessagingException, IOException {
        String to = "bahaalawneh07@gmail.com";
        String userName = "baha";
        String path = "admin-delete-user";
        spyEmailService.sendAdminDeleteUserEmail(to, userName, path);
        verify(spyEmailService, times(1)).sendAdminDeleteUserEmail(to, userName, path);
    }
    @Test
    public void testSendAdminChangePasswordEmail() throws MessagingException, IOException {
        String to = "bahaalawneh07@gmail.com";
        String userName = "baha";
        String newPassword = "newPassword123";
        String path = "admin-delete-user";
        spyEmailService.sendAdminChangePasswordEmail(to, userName, newPassword, path);
        verify(spyEmailService, times(1)).sendAdminChangePasswordEmail(to, userName, newPassword, path);
    }


}
