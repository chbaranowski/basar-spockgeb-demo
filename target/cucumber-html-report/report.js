$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("spec/basar/basar.feature");
formatter.feature({
  "id": "shopping-cart",
  "description": "",
  "name": "Shopping Cart",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "shopping-cart;add-a-article-to-cart",
  "description": "",
  "name": "add a article to cart",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "Empty shopping cart",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "add article with basarnumber \"100\" and price \"10,50\" Euro",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "size of the cart is 1",
  "keyword": "Then ",
  "line": 6
});
formatter.step({
  "name": "total price should be \"10,50\"",
  "keyword": "And ",
  "line": 7
});
formatter.match({
  "location": "CartStepdefs.Empty_shopping_cart()"
});
formatter.result({
  "duration": 198488000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "100",
      "offset": 30
    },
    {
      "val": "10,50",
      "offset": 46
    }
  ],
  "location": "CartStepdefs.add_article_with_basarnumber_and_price_Euro(String,String)"
});
formatter.result({
  "duration": 4159000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "CartStepdefs.size_of_the_cart_is(int)"
});
formatter.result({
  "duration": 4020000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10,50",
      "offset": 23
    }
  ],
  "location": "CartStepdefs.total_price_should_be(String)"
});
formatter.result({
  "duration": 2368000,
  "status": "passed"
});
});