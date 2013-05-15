package basar.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class UserEditPage {

    WebElement basarNumber;

    WebElement name;

    WebElement lastname;

    WebElement email;

    WebElement saveUser;

    WebDriver driver;

    int userCount;

    public UserEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        userCount = driver.findElements(By.cssSelector("#usersBody tr")).size();
    }

    public UserEditPage basarNumber(String value) {
        basarNumber.clear();
        basarNumber.sendKeys(value);
        return this;
    }

    public UserEditPage name(String value) {
        name.clear();
        name.sendKeys(value);
        return this;
    }

    public UserEditPage lastname(String value) {
        lastname.clear();
        lastname.sendKeys(value);
        return this;
    }

    public UserEditPage email(String value) {
        email.clear();
        email.sendKeys(value);
        return this;
    }

    public UserPage save() {
        saveUser.click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(1, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, Integer>() {
            public Integer apply(WebDriver driver) {
                int actualUserCount = driver.findElements(By.cssSelector("#usersBody tr")).size();
                if (actualUserCount > userCount) {
                    return actualUserCount;
                } else {
                    return null;
                }
            }
        });
        return new UserPage(driver);
    }

}
