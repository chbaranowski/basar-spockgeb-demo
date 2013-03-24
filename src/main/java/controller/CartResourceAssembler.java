package controller;

import data.Position;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static domain.PriceUtils.formatPriceLongToString;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CartResourceAssembler extends ResourceAssemblerSupport<Position, CartResource> {

    public CartResourceAssembler() {
        super(ShoppingController.class, CartResource.class);
    }

    @Override
    public CartResource toResource(Position position) {
        CartResource resource = new CartResource();
        resource.basarNumber = position.getSeller().getBasarNumber();
        resource.description = position.getDescription();
        resource.price = formatPriceLongToString(position.getPrice());
        resource.add(linkTo(ShoppingController.class).slash("cart/"+position.getId()).withSelfRel());
        return resource;
    }

}
