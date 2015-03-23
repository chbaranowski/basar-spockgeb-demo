package basar.web;

import static basar.domain.PriceUtils.formatPriceLongToString;
import static basar.domain.PriceUtils.formatPriceToLong;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import basar.data.Position;
import basar.domain.Basar;

@RestController
@RequestMapping(value = "/shopping/", produces = "application/json")
public class ShoppingController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    Basar basar;
    
    @Autowired
    ShoppingCart shoppingCart;
    
    @Autowired
    CartResourceAssembler cartResourceAssembler;

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus addItemToCart(@Validated CartResource resource) {
        Position position = basar.createPosition(
                resource.getBasarNumber(),
                formatPriceToLong(resource.getPrice()),
                resource.getDescription());
        shoppingCart.addPosition(position);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public ShoppingCartResource getShoppingCart() {
        ShoppingCartResource resource = new ShoppingCartResource();
        resource.setCartItems(cartResourceAssembler.toResources(shoppingCart.getPositions()));
        resource.setSum(formatPriceLongToString(shoppingCart.sum()));
        resource.setTotal(formatPriceLongToString(basar.getTotal()));
        return resource;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus storno() {
        shoppingCart.clear();
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus buy() {
        Iterable<Position> positions = shoppingCart.getPositions();
        basar.buy(positions);
        shoppingCart.clear();
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpStatus deleteCartItem(@PathVariable Long id) {
        shoppingCart.removePosition(id);
        return HttpStatus.OK;
    }

}
