package email;

import io.YmlHandler;
import models.EventPlanner;
import models.RegisteredEvent;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Properties;


public class EmailService {
    private static final String DYNAMIC_TEXT_PLACEHOLDER = "{{dynamic_text_placeholder}}";
    private static final String HTML_EXTENSION = ".html";
    private static final String HTML_RESOURCES_PATH = "src/main/resources/html/";
    private static final String HTML_MIME_TYPE = "text/html";
    private static final SecureRandom random = new SecureRandom();
    private static final String SUBJECT = "Verification Code";
    private final String from;
    private final String password;
    private String body;
    private String newPassword;

    public EmailService() throws FileNotFoundException {
        from = YmlHandler.getValue("email-config", "fromEmail");
        password = YmlHandler.getValue("email-config", "password");
        body = "";
        newPassword = "";
    }



    private void setBody(String path) throws IOException {
        body = Files.readString(Paths.get(HTML_RESOURCES_PATH + path + HTML_EXTENSION));
        newPassword = generateRandomString();
        body = body.replace(DYNAMIC_TEXT_PLACEHOLDER, newPassword);
    }


    private Properties defineProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.ssl.checkserveridentity", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return prop;
    }

    public Session createSession() {
        return Session.getInstance(defineProperties(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }

    public void sendEmail(String to, String path) throws MessagingException, IOException {
        Message message = new MimeMessage(createSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(SUBJECT);

        setBody(path);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, HTML_MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    public void sendAdminDeleteUserEmail(String to, String userName, String path) throws MessagingException, IOException {
        Message message = new MimeMessage(createSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Account Deletion Notification");

        body = Files.readString(Paths.get(HTML_RESOURCES_PATH + path + HTML_EXTENSION));
        body = body.replace(DYNAMIC_TEXT_PLACEHOLDER, "Dear " + userName + ",");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, HTML_MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }


    public void sendAdminChangePasswordEmail(String to, String userName, String newChangePassword, String path) throws MessagingException, IOException {
        Message message = new MimeMessage(createSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Password Change Notification");

        body = Files.readString(Paths.get(HTML_RESOURCES_PATH + path + HTML_EXTENSION));
        body = body.replace(DYNAMIC_TEXT_PLACEHOLDER, "Dear " + userName);
        body = body.replace(DYNAMIC_TEXT_PLACEHOLDER, "New Password: " + newChangePassword);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, HTML_MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

    public static String generateRandomString() {
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 4; i++)
            randomStr.append(generateRandomDigit());
        return randomStr.toString();
    }

    private static int generateRandomDigit() {
        return random.nextInt(10);
    }

    public String sendResetPasswordCode(String to) throws MessagingException, IOException {
        Message message = new MimeMessage(createSession());
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Password Change Notification");

        body = Files.readString(Paths.get(HTML_RESOURCES_PATH+ "Reset-Password" + HTML_EXTENSION));

        String code = generateRandomString();
        body = body.replace(DYNAMIC_TEXT_PLACEHOLDER, "Code : " + code);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, HTML_MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
        return code;
    }

    public void sendEventInvitations(RegisteredEvent registeredEvent) throws MessagingException, IOException {
        String emailTemplate = Files.readString(Paths.get(HTML_RESOURCES_PATH+"invitation-body"+HTML_EXTENSION));

        for (String guestEmail : registeredEvent.getGuestsEmails()) {
            Message message = new MimeMessage(createSession());

            message.setFrom(new InternetAddress(from));
            String[] tokens = guestEmail.split("@");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(guestEmail));

            message.setSubject("Invitation to " + registeredEvent.getEventName());

            Multipart multipart = getMultipart(registeredEvent, emailTemplate, tokens);
            message.setContent(multipart);

            Transport.send(message);
        }
    }

    private static Multipart getMultipart(RegisteredEvent registeredEvent, String emailTemplate, String[] tokens) throws MessagingException {
        String body = emailTemplate.replace("{{guest name}}", tokens[0]).replace("{{eventName}}", registeredEvent.getEventName())
                .replace("{{eventDate}}", String.valueOf(registeredEvent.getDate()))
                .replace("{{event owner}}", EventPlanner.getCurrentUser().getName().getfName() + EventPlanner.getCurrentUser().getName().getlName());


        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, HTML_MIME_TYPE);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        return multipart;
    }


}
