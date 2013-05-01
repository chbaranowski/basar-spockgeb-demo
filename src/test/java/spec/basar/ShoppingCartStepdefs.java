package spec.basar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import controller.CartResource;
import controller.ShoppingCart;
import controller.ShoppingController;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import data.User;
import domain.Basar;

public class ShoppingCartStepdefs {
    
    @Autowired
    ShoppingController shoppingController;
    
    @Autowired
    ShoppingCart shoppingCart;
    
    @Autowired
    Basar basar;
    
    @Before
    public void before() {
        User testUser = basar.findByBasarNumber("100");
        if(testUser == null) {
            testUser = new User();
            testUser.setBasarNumber("100");
            basar.saveUser(testUser);
        }
    }
    
    @Given("^empty shopping cart$")
    public void Empty_shopping_cart() throws Throwable {
        shoppingCart.clear();
    }
    
    @When("^add article with basarnumber \"([^\"]*)\" and price \"([^\"]*)\" Euro$")
    public void add_article_with_basarnumber_and_price_Euro(String basarNumber, String price) throws Throwable {
        CartResource cartItem = new CartResource();
        cartItem.setBasarNumber(basarNumber);
        cartItem.setPrice(price);
        shoppingController.addItemToCart(cartItem );
    }

    @Then("^total price should be \"([^\"]*)\"$")
    public void total_price_should_be(String expectedTotalPrice) throws Throwable {
        assertEquals(expectedTotalPrice, shoppingController.getShoppingCart().getSum());
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