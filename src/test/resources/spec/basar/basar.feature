Feature: Shopping Cart
  
  Scenario Outline: add a article to cart
    Given Empty shopping cart
    When add article with basarnumber "<number>" and price "<price>" Euro
    Then total price should be "<total>"
    
  Examples:
    | number | price | total |
    | 100    | 10,50 | 10,50 |
    | 100    | 10    | 10,00 |
