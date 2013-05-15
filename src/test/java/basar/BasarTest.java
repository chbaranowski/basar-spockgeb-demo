package basar;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Function;

import controller.UserResource;

import runner.Webapp;

public class BasarTest {

    static Webapp webapp;
    
    static WebDriver driver;
    
    @BeforeClass
    public static void startApp() throws Exception {
        webapp = new Webapp();
        webapp.start();
        driver = new FirefoxDriver();
       
    }
    
    @AfterClass
    public static void stopApp() throws Exception {
        webapp.stop();
        webapp = null;
        driver.quit();
        driver = null;
    }
    
    @Before
    public void setup() throws Exception {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<UserResource[]> entity = rest.getForEntity("http://localhost:8881/users/", UserResource[].class);
        UserResource[] users = entity.getBody();
        for (UserResource userResource : users) {
            rest.delete(userResource.getLink("self").getHref());
        }
        UserResource testUser = new UserResource();
        testUser.setBasarNumber("100");
        MultiValueMap<String, String> userForm = new LinkedMultiValueMap<String, String>();
        userForm.add("basarNumber", "100");
        userForm.add("email", "");
        userForm.add("lastname", "");
        userForm.add("name", "");
        rest.postForObject("http://localhost:8881/users/", userForm, UserResource.class);
        driver.manage().deleteAllCookies();
    }
    
    @Test
    public void addArticel() throws Exception {
        driver.get("http://localhost:8881/static/basar.html");
        driver.findElement(By.id("basarNumber")).clear();
        driver.findElement(By.id("basarNumber")).sendKeys("100");
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("price")).sendKeys("10,50");
        driver.findElement(By.id("addCartItem")).click();
        
        Wait<WebDriver> wait = new WebDriverWait(driver, 3);
        WebElement sum = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement sum = driver.findElement(By.id("sum"));
                if(sum.getText().equals("10,50")){
                    return sum;
                } else {
                    return null;
                }
            }
        });
        assertEquals("10,50", sum.getText());
    }
}
