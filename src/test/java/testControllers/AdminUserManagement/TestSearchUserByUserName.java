package testControllers.AdminUserManagement;

import controllers.AdminControl;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestSearchUserByUserName {

    private String searchInput;

    @When("admin enter username {string}")
    public void adminEnterUsername(String string) {
        // Write code here that turns the phrase above into concrete actions
        searchInput=string;
    }

    @Then("list of related usernames will appear {string}")
    public void list_of_related_usernames_will_appear(String string) {
        // Write code here that turns the phrase above into concrete actions
        List<String> expectedNamesList = new ArrayList<>(Arrays.asList(string.split(",")));
        List<User> result = AdminControl.searchUsers(searchInput);
        List<String> actualNamesList=AdminControl.getUserNameOfUsers(result);
        assert (listsContainSameContent(actualNamesList, expectedNamesList));

    }

    @Then("there is no users related with input string")
    public void thereIsNoUsersRelatedWithInputString() {
        // Write code here that turns the phrase above into concrete actions
        assert (AdminControl.searchUsers(searchInput).isEmpty());
    }


    public static boolean listsContainSameContent(List<String> list1, List<String> list2) {
        // Sort both lists
        Collections.sort(list1);
        Collections.sort(list2);
        // Check if lists are equal
        return list1.equals(list2);
    }
}
