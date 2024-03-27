package testControllers.AdminUserManagement;

import Exceptions.UserIsAlreadyExist;
import Exceptions.WeakPasswordException;
import controllers.SignUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestRegisterNewUser {
    private Authentication authentication;
    private Name name;
    private Address address;
    private ContactInfo contactInfo;
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
    @Then("User will sign up seccessfully")
    public void userWillSignUpSeccessfully() throws UserIsAlreadyExist, WeakPasswordException {
        assertDoesNotThrow(() -> {
            SignUp.signUpUser(this.name,this.address,this.authentication,this.contactInfo);
            EventPlanner.getUserByUsername(this.authentication.getUsername());
        });
    }
}
