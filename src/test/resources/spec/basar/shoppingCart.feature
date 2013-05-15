Feature: Shopping Cart

  Scenario Outline: add a article to cart
    Given empty shopping cart
    When add article with basarnumber "<number>" and price "<price>" Euro
    Then total price should be "<total>"

    Examples: 
      | number | price | total |
      | 100    | 10,50 | 10,50 |
      | 100    | 10    | 10,00 |
      
  Scenario: add a article with unknown basar number
    Given empty shopping cart
    When add article with basarnumber "200" and price "10,50" Euro
    Then the error message "basarNumber, Die Basarnummer existiert nicht." should be shown.

  Scenario: add more articles to the cart
    Given empty shopping cart
    When add artiles:
      | number | price |
      | 100    | 10,50 |
      | 100    | 10    |
    Then total price should be "20,50"
    
  Scenario: delete the first articles from cart
    Given empty shopping cart
    When add artiles:
      | number | price |
      | 100    | 10,50 |
      | 100    | 10    |
    And delete cart item number [1]
    Then total price should be "10,50"
    
  Scenario: delete the second articles from cart
    Given empty shopping cart
    When add artiles:
      | number | price |
      | 100    | 10,50 |
      | 100    | 10    |
    And delete cart item number [2]
    Then total price should be "10,00"
