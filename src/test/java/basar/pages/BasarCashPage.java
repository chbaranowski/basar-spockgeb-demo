package basar.pages;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.google.common.base.Predicate;

public class BasarCashPage extends LoadableComponent<BasarCashPage> {

    WebDriver driver;
    
    WebElement basarNumber;
    
    WebElement price;
    
    WebElement addCartItem;
    
    WebElement sum;
    
    WebElement description;
    
    public static BasarCashPage get(WebDriver driver) {
        return new BasarCashPage(driver).get();
    }

    public BasarCashPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @Override
    protected void load() {
        driver.get("http://localhost:8881/static/basar.html");
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals("Basar", driver.getTitle());
    }
  
    public BasarCashPage basarNumber(String value){
        basarNumber.clear();
        basarNumber.sendKeys(value);
        return this;
    }
    
    public BasarCashPage price(String value){
        price.clear();
        price.sendKeys(value);
        return this;
    }
    
    public BasarCashPage description(String value){
        description.clear();
        description.sendKeys(value);
        return this;
    }
    
    public BasarCashPage add() {
        final int cartCount = getCartCount();
        addCartItem.click();
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                int actualCartCount = getCartCount();
                if (actualCartCount > cartCount) {
                    return true;
                } else if(getErrorMessages().size() > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        return this;
    }
    
    public int getCartCount() {
        return driver.findElements(By.cssSelector("#cartBody tr")).size();
    }
    
    public String getSum() {
        return sum.getText();
    }

    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<String>();
        List<WebElement> msgElements = driver.findElements(By.cssSelector("#messageBox li"));
        for (WebElement webElement : msgElements) {
            result.add(webElement.getText());
        }
        return result;
    }
    

}
