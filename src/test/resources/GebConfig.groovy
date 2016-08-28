import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.InternetExplorerDriverManager
import io.github.bonigarcia.wdm.PhantomJsDriverManager

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.ie.InternetExplorerDriver

waiting {
	timeout = 10
}

// Use Firefox as the default driver
driver = { new FirefoxDriver() }

// specify environment via -Dgeb.env=...
environments {
	
	chrome {
		ChromeDriverManager.getInstance().setup()
		driver = { new ChromeDriver() }
	}
	
	firefox {
		driver = { new FirefoxDriver() }
	}
	
	phantomJs {
		PhantomJsDriverManager.getInstance().setup();
		driver = { new PhantomJSDriver() }
	}
	
	ie {
		InternetExplorerDriverManager.getInstance().setup();
		driver = { new InternetExplorerDriver() }
	}
	
}