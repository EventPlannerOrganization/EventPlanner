package models;

import java.util.Objects;

public class Request {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(userEmail, request.userEmail) && Objects.equals(serviceProviderEmail, request.serviceProviderEmail) && Objects.equals(message, request.message) && Objects.equals(event, request.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEmail, serviceProviderEmail, message, event);
    }

    public Request(String userEmail, String serviceProviderEmail, String message, RegisteredEvent event) {
        setUserEmail(userEmail);
        setServiceProviderEmail(serviceProviderEmail);
        setMessage(message);
        this.event = event;
    }


    private String userEmail;

    public String getServiceProviderEmail() {
        return serviceProviderEmail;
    }

    private String serviceProviderEmail;


    public void setServiceProviderEmail(String serviceProviderEmail) {
        this.serviceProviderEmail = serviceProviderEmail;
    }

    private String message;
    private final RegisteredEvent event;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisteredEvent getEvent() {
        return event;
    }


}
