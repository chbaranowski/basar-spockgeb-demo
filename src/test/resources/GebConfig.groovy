waiting {
	timeout = 10
}

driver = {
	// Setup Firefox Driver
	new org.openqa.selenium.firefox.FirefoxDriver()
	
	// Setup Chrome Driver
	// System.setProperty('webdriver.chrome.driver', '/Users/developer/tools/chromedriver/chromedriver')
	// new org.openqa.selenium.chrome.ChromeDriver() 
	
	// Setup IE Driver
	// System.setProperty("webdriver.ie.driver", new File("C:/dev/Selenium/iexploredriver.exe").getAbsolutePath())
	// new org.openqa.selenium.ie.InternetExplorerDriver()
	
}