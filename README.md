# Spock and Geb Demo

The project contains some sample spock and geb tests. 

##  Selenium Webdriver Binaries Management
The Webdriver binaries are automatically downloaded by using a small library called WebDriverManager more details see <https://github.com/bonigarcia/webdrivermanager>.

## Run Tests

The spock geb tests can be run via gradle:

	./gradlew test
	
For running the tests in a specific browser invoke
- gradlew firefoxTest (default)
- gradlew chromeTest
- gradlew ieTest
- gradlew phantomJsTest

## Run Web Application

To run the demo web application invoke the Java main class:
	
	basar.BasarApplication
	
or run the the main class via gradle:

	./gradlew bootRun
	
When the demo web application is running, the web application provides two simple pages under
 - Management of sellers <http://localhost:8080/sellers.html>
 - Basar Cash Point <http://localhost:8080/basar.html>
  
## Demo Spock Geb Tests

The demo tests can be found in the folder src/test/groovy in the package basar.
There are three demo tests:
 - UI integration test - <https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/SellerSpec.groovy>
 - n-n system test - <https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/BasarSpec.groovy>
 - n-n system test as stepwise spock test - <https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/ComplexCartSpec.groovy>

## Slides

There are a few slides around the demo on slideshare:
 * <http://de.slideshare.net/tux2323/bdd-behavior-driven-development-webapps-mit-groovy-spock-und-geb>
 * <http://de.slideshare.net/tux2323/spock-and-geb-in-action>
 * <http://de.slideshare.net/tux2323/spock-and-geb-17517425>
 
## Links

- Spock Framework Reference Documentation
  <http://docs.spockframework.org/en/latest/>
- Geb
  <http://www.gebish.org/>
- WebDriverManager
  <https://github.com/bonigarcia/webdrivermanager>
