# Basar Spock and Geb Demo

The project contains some spock and geb demo test cases.
To run the tests you need the [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/home) installed. 

## Run all Tests

The spock geb tests can be run via gradle:

	./gradlew test

## Run the Demo Application

To run the demo Basar application invoke the Java main class:
	
	runner.BasarApplication
	
or run the the main class via gradle:

	./gradlew bootRun
	
When the demo webapp is running, the webapp provides two simple pages under
 - http://localhost:8080/sellers.html
 - http://localhost:8080/basar.html
 
## Web Drivers Setup

To use chrome the chrome driver must be installed see
https://sites.google.com/a/chromium.org/chromedriver/
 
To use IE as browser the ie driver must be installed see
https://code.google.com/p/selenium/wiki/InternetExplorerDriver 
 
## Demo Spock Geb Tests

The demo tests can be found in the folder src/test/groovy in the package basar.
There are three demo tests:
 - UI integration test - https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/SellerSpec.groovy
 - n-n system test - https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/BasarSpec.groovy
 - n-n system test as stepwise test - https://github.com/tux2323/basar-spockgeb-demo/blob/master/src/test/groovy/basar/ComplexCartSpec.groovy

## Slides

There are a few slides for the demo on slideshare:
 * http://de.slideshare.net/tux2323/bdd-behavior-driven-development-webapps-mit-groovy-spock-und-geb
 * http://de.slideshare.net/tux2323/spock-and-geb-in-action
 * http://de.slideshare.net/tux2323/spock-and-geb-17517425
 
## Links

- Spock
  http://spockframework.org
- Spock GitHub
  https://github.com/spockframework
- Spock Framework Reference Documentation
  http://docs.spockframework.org/en/latest/
- Geb
  http://www.gebish.org/
- GebBind
  https://github.com/tux2323/gebbind
