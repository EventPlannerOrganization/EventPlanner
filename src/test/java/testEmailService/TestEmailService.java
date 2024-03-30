package testEmailService;

import Email.EmailService;
import models.RegisteredEvent;
import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class TestEmailService {
    private EmailService mockEmailService;
    private RegisteredEvent registeredEvent;

    @Before
    public void setup()  {
        mockEmailService = mock(EmailService.class);
        registeredEvent = new RegisteredEvent();
        registeredEvent.setGuestsEmails(List.of("bahaalawneh07@gmail.com"));
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
        mockEmailService.sendResetPasswordCode("bahaalawneh07@gmail.com");
        verify(mockEmailService, times(1)).sendResetPasswordCode("bahaalawneh07@gmail.com");
    }

    @Test
    public void testSendEventInvitations() throws MessagingException, IOException {
        // Mock the necessary dependencies
        registeredEvent.setEventName("Test Event");
        mockEmailService.sendEventInvitations(registeredEvent);
        verify(mockEmailService, times(1)).sendEventInvitations(registeredEvent);


    }
}
