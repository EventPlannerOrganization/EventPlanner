package testControllers.AdminUserManagement;

import Exceptions.UserIsAlreadyExist;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;

import java.util.ArrayList;
import java.util.List;

import static controllers.AdminControl.getAllUsers;
import static helpers.PasswordChecker.listsContainSameElements;

public class TestShowAllUsers {
    @When("Data Base is already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.initializeRepositoryWithData();
    }
    @Then("printAllUsers")
    public void printAllUsers() {
        // Write code here that turns the phrase above into concrete actions
        List<String> expectedOuput = new ArrayList<>(List.of(new String[]{"Naser", "khalid", "Karim", "Nassar"}));
        List<String> actualOutput =getAllUsers();
        assert(listsContainSameElements(expectedOuput,actualOutput));
    }

    @Then("there is no users to print")
    public void thereIsNoUsersToPrint() {
        // Write code here that turns the phrase above into concrete actions
        List<String> actualOutput =getAllUsers();
        assert (actualOutput.isEmpty());
    }


}
