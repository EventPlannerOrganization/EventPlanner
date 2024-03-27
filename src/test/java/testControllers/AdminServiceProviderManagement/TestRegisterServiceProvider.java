package testControllers.AdminServiceProviderManagement;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import controllers.SignUp;
import enumerations.ServiceType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestRegisterServiceProvider {
    private Authentication authentication;
    private Name name;
    private Address address;
    private ContactInfo contactInfo;
    private Service service;

    @When("Authentication is {string} {string}")
    public void authenticationIs(String username, String password) {
        this.authentication=new Authentication(username,password);
    }
    @When("Name is {string} {string} {string}")
    public void nameIs(String firstName, String middleName, String lastName) {
        this.name=new Name(firstName,middleName,lastName);
    }
    @When("ContactInfo is {string} {string}")
    public void contactInfoIs(String email, String phoneNumber) {
        this.contactInfo=new ContactInfo(email,phoneNumber);
    }
    @When("Address is {string} {string}")
    public void addressIs(String country, String city) {
        this.address=new Address(country,city);
    }
    @When("Service is {string} {string} {string}")
    public void serviceIs(String  serviceType,String servicePrice, String serviceDiscription) {
        this.service=new Service(ServiceType.valueOf(serviceType),Double.parseDouble(servicePrice),serviceDiscription);
    }
    @Then("Service Provider will sign up seccessfully")
    public void serviceProviderWillSignUpSeccessfully() throws UserIsAlreadyExist, WeakPasswordException {
        List<Service> services=new ArrayList<>();
        services.add(this.service);
        assertDoesNotThrow(() -> {
            SignUp.signUpServiceProvider(this.name,this.address,this.authentication,this.contactInfo,services);
            EventPlanner.getServiceProviderByUsername(this.authentication.getUsername());
        });
    }
}
