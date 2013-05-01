Feature: Shopping Cart

  Scenario: add a article to cart
    Given Empty shopping cart
    When add article with basarnumber "100" and price "10,50" Euro
    Then size of the cart is 1
    And total price should be "10,50"
