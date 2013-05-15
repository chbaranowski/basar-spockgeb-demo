package basar.pages;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class UserPage extends LoadableComponent<UserPage> {

    WebDriver driver;
    
    WebElement newUser;
    
    public static UserPage get(WebDriver driver) {
        return new UserPage(driver).get();
    }
    
    public UserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @Override
    protected void load() {
        driver.get("http://localhost:8881/static/sellers.html");
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Basar Sellers", driver.getTitle());
    }
    
    public UserEditPage newUser() {
        newUser.click();
        return new UserEditPage(driver);
    }
    
    public int userCount() {
        return driver.findElements(By.cssSelector("#usersBody tr")).size();
    }
    
}
