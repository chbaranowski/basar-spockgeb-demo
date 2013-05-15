package spec.basar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import runner.Webapp;
import basar.pages.BasarCashPage;
import controller.UserResource;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingCartStepdefs {
    
    static Webapp webapp;
    
    static WebDriver driver;
    
    @Before
    public void start() throws Exception {
        if(driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    driver.close();
                    driver.quit();
                    driver = null;
                }
            });
        }
        if(webapp == null) {
            webapp = new Webapp();
            webapp.start();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    webapp.stop();
                    webapp = null;
                }
            });
        }
        
        RestTemplate rest = new RestTemplate();
        ResponseEntity<UserResource[]> entity = rest.getForEntity("http://localhost:8881/users/", UserResource[].class);
        UserResource[] users = entity.getBody();
        for (UserResource userResource : users) {
            rest.delete(userResource.getLink("self").getHref());
        }
        UserResource testUser = new UserResource();
        testUser.setBasarNumber("100");
        MultiValueMap<String, String> userForm = new LinkedMultiValueMap<String, String>();
        userForm.add("basarNumber", "100");
        userForm.add("email", "");
        userForm.add("lastname", "");
        userForm.add("name", "");
        rest.postForObject("http://localhost:8881/users/", userForm, UserResource.class);
        
    }
    
    @Given("^empty shopping cart$")
    public void Empty_shopping_cart() throws Throwable {
        BasarCashPage basarPage = new BasarCashPage(driver).get();
        basarPage.storno();
        
    }
    
    @When("^add article with basarnumber \"([^\"]*)\" and price \"([^\"]*)\" Euro$")
    public void add_article_with_basarnumber_and_price_Euro(String basarNumber, String price) throws Throwable {
        new BasarCashPage(driver)
            .basarNumber(basarNumber)
            .price(price)
            .add();
    }
    
    @When("^delete cart item number \\[(\\d+)\\]$")
    public void delete_article(int number) throws Throwable {
        new BasarCashPage(driver).deleteCartItem(number);
    }

    @Then("^total price should be \"([^\"]*)\"$")
    public void total_price_should_be(String expectedTotalPrice) throws Throwable {
        assertEquals(expectedTotalPrice, new BasarCashPage(driver).getSum());
    }
    
    @Then("^the error message \"([^\"]*)\" should be shown.$")
    public void the_error_message_should_be_shown(String msg) throws Throwable {
        assertEquals(msg, new BasarCashPage(driver).getErrorMessages().get(0));
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
