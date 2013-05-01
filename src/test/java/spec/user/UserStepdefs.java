package spec.user;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.User;
import domain.Basar;

import static org.junit.Assert.*;

public class UserStepdefs {
    
    @Autowired
    Basar basar;

    @Given("^a basar with no users$")
    public void a_basar_with_no_users() throws Throwable {
        Iterable<User> users = basar.findAllUsers();
        for (User user : users) {
            basar.deleteUserWithId(user.getId());
        }
    }

    @When("^create a user with number \"([^\"]*)\" and name \"([^\"]*)\"$")
    public void create_a_user_with_number_and_name(String number, String name) throws Throwable {
        User user = new User();
        user.setBasarNumber(number);
        user.setName(name);
        basar.saveUser(user);
    }

    @Then("^the count of the basar users should be (\\d+)$")
    public void the_count_of_the_basar_users_should_be(int expectedCount) throws Throwable {
        int actualUserCount = 0;
        Iterable<User> users = basar.findAllUsers();
        for (User user : users) {
            actualUserCount++;
        }
        assertEquals(expectedCount, actualUserCount);
    }
    
}
