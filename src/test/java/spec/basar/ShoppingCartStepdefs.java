package spec.basar;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import runner.BasarApplication;
import runner.Webapp;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingCartStepdefs {
    
    Webapp webapp;
    
    WebDriver driver;
    
    @Before
    public void start() throws Exception {
        webapp = new Webapp(8881);
        webapp.start();
        driver = new FirefoxDriver();
    }
    
    @After
    public void stop() throws Exception {
        webapp.stop();
    }
    
    @Given("^empty shopping cart$")
    public void Empty_shopping_cart() throws Throwable {
        
    }
    
    @When("^add article with basarnumber \"([^\"]*)\" and price \"([^\"]*)\" Euro$")
    public void add_article_with_basarnumber_and_price_Euro(String basarNumber, String price) throws Throwable {
    }

    @Then("^total price should be \"([^\"]*)\"$")
    public void total_price_should_be(String expectedTotalPrice) throws Throwable {
    }
    
    public static class CartItem {
        String number;
        String price;
    }
    
    @When("^add artiles:$")
    public void add_artiles(List<CartItem> items) throws Throwable {
        for (CartItem cartItem : items) {
            add_article_with_basarnumber_and_price_Euro(cartItem.number, cartItem.price);
        }
    }
    
}
