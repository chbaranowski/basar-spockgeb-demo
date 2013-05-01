Feature: Shopping Cart

  Scenario Outline: add a article to cart
    Given empty shopping cart
    When add article with basarnumber "<number>" and price "<price>" Euro
    Then total price should be "<total>"

    Examples: 
      | number | price | total |
      | 100    | 10,50 | 10,50 |
      | 100    | 10    | 10,00 |

  Scenario: add more articles to the cart
    Given empty shopping cart
    When add artiles:
      | number | price |
      | 100    | 10,50 |
      | 100    | 10    |
    Then total price should be "20,50"
