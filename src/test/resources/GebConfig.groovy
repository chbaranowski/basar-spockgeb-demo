/*
    This is the Geb configuration file.
    See: http://www.gebish.org/manual/current/configuration.html
*/

import org.openqa.selenium.firefox.*

waiting {
    timeout = 10
}

driver = { 
	//  def firefox = new File("/path/FirefoxPortable.exe")
	//	FirefoxBinary binary = new FirefoxBinary(firefox);
	//  new FirefoxDriver(binary, new FirefoxProfile())
	new FirefoxDriver()
}

environments {
    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }
    }

}
