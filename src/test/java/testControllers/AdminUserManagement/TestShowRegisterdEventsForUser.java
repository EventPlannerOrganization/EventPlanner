package testControllers.AdminUserManagement;

import Exceptions.UserNotFoundException;
import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestShowRegisterdEventsForUser {
    User user;
    List<String> expectedEventsName;
    List<String> expectedEventsDates;
    @When("Admin Want To Display Events for User {string}")
    public void adminWantToDisplayEventsForUser(String string) throws UserNotFoundException {
       user = (User) EventPlanner.getUserByUsername(string);
    }
    @When("User Has {string}{string}")
    public void userHas(String string, String string2) {
      expectedEventsName = new ArrayList<>(Arrays.asList(string.split(",")));
      expectedEventsDates = new ArrayList<>(Arrays.asList(string2.split(",")));


    }
    @Then("The User Events Will Display Correctly")
    public void theUserEventsWillDisplayCorrectly() {
       List<String> actual= AdminControl.getEventsForUser(user);
       for (int i = 0; i<actual.size();i++){
           assert (actual.get(i).contains(expectedEventsName.get(i)));
           assert (actual.get(i).contains(expectedEventsName.get(i)));

       }

    }
}
