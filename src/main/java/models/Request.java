package models;

public class Request {


    public Request(String userEmail, String serviceProviderEmail, String message, RegisteredEvent event) {
        this.userEmail = userEmail;
        this.serviceProviderEmail = serviceProviderEmail;
        this.message = message;
        this.event = event;
    }




    private String userEmail;
    private String serviceProviderEmail;

    public String getServiceProviderEmail() {
        return serviceProviderEmail;
    }

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
