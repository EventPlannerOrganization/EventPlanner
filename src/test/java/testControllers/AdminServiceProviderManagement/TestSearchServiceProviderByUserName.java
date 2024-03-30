package testControllers.AdminServiceProviderManagement;

import exceptions.UserIsAlreadyExist;
import controllers.AdminControl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.EventPlanner;
import models.ServiceProvider;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSearchServiceProviderByUserName {
    String userName;
    @Given("Data Base is already filled")
    public void dataBaseIsAlreadyFilled() throws UserIsAlreadyExist {
        // Write code here that turns the phrase above into concrete actions
        EventPlanner.initializeRepositoryWithData();
    }
    @When("admin enter  Username {string}")
    public void adminEnterUsername(String string) {
        // Write code here that turns the phrase above into concrete actions
             userName = string;
      }
    @Then("there is no users related with input string")
    public void thereIsNoUsersRelatedWithInputString() {

        assert(AdminControl.searchServiceProviders(userName)==null);

    }

    @Then("list of related usernames will appear {string}")
    public void listOfRelatedUsernamesWillAppear(String string) {
        List<String> expectedNamesList = new ArrayList<>(Arrays.asList(string.split(",")));
        List<ServiceProvider> result = AdminControl.searchServiceProviders(userName);
        List<String> actualNamesList=AdminControl.getUserNameOfServiceProviders(result);
        assert (listsContainSameContent(actualNamesList, expectedNamesList));

    }
    public static boolean listsContainSameContent(List<String> list1, List<String> list2) {
        // Sort both lists
        Collections.sort(list1);
        Collections.sort(list2);
        // Check if lists are equal
        return list1.equals(list2);
    }
}
