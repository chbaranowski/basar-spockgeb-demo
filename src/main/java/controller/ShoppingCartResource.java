package controller;

import java.io.Serializable;

public class ShoppingCartResource implements Serializable {

    private static final long serialVersionUID = 1L;

    String sum;

    String total;

    Iterable<CartResource> cartItems;

    public Iterable<CartResource> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Iterable<CartResource> cartItems) {
        this.cartItems = cartItems;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
