package controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import domain.BasarNumberConstraint;

public class CartResource extends ResourceSupport {
    
    @NotEmpty
    @BasarNumberConstraint(message="Die Basarnummer existiert nicht.")
    String basarNumber;
    
    @NotEmpty
    String price;

    String description;

    public String getBasarNumber() {
        return basarNumber;
    }

    public void setBasarNumber(String basarNumber) {
        this.basarNumber = basarNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
