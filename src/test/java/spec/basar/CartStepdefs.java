package spec.basar;

import cucumber.api.*;
import cucumber.api.java.en.*;


public class CartStepdefs {
    
    @Given("^Empty shopping cart$")
    public void Empty_shopping_cart() throws Throwable {
        throw new PendingException();
    }

    @When("^add article with basarnumber \"([^\"]*)\" and price \"([^\"]*)\" Euro$")
    public void add_article_with_basarnumber_and_price_Euro(String basarNumber, String price) throws Throwable {
        throw new PendingException();
    }

    @Then("^size of the cart is (\\d+)$")
    public void size_of_the_cart_is(int size) throws Throwable {
        throw new PendingException();
    }

    @Then("^total price should be \"([^\"]*)\"$")
    public void total_price_should_be(String expectedTotalPrice) throws Throwable {
        throw new PendingException();
    }
    
}
