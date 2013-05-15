package basar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import controller.UserResource;

import runner.Webapp;

public class BasarTest {

    static Webapp webapp;
    
    WebDriver driver;

    @BeforeClass
    public static void startApp() throws Exception {
        webapp = new Webapp();
        webapp.start();
    }
    
    @AfterClass
    public static void stopApp() throws Exception {
        webapp.stop();
        webapp = null;
    }
    
    @Before
    public void setup() throws Exception {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<UserResource[]> entity = rest.getForEntity("http://localhost:8881/users/", UserResource[].class);
        UserResource[] users = entity.getBody();
        for (UserResource userResource : users) {
            rest.delete(userResource.getLink("self").getRel());
        }
        
        driver = new FirefoxDriver();
        driver.get("http://localhost:8881/static/sellers.html");
        driver.findElement(By.id("newUser")).click();
        driver.findElement(By.id("basarNumber")).sendKeys("100");
        driver.findElement(By.id("name")).sendKeys("Test");
        driver.findElement(By.id("lastname")).sendKeys("Tester");
        driver.findElement(By.id("email")).sendKeys("Test@Test,de");
        driver.findElement(By.id("saveUser")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if ("100".equals(driver.findElement(By.cssSelector("td")).getText())) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }
    
    @After
    public void cleanup() {
        driver.quit();
    }
    
    @Test
    public void addArticel() throws Exception {
        driver.get("http://localhost:8881/static/basar.html");
        driver.findElement(By.id("basarNumber")).clear();
        driver.findElement(By.id("basarNumber")).sendKeys("100");
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("price")).sendKeys("10,50");
        driver.findElement(By.id("addCartItem")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if ("10,50".equals(driver.findElement(By.id("sum")).getText())) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
        assertEquals("10,50", driver.findElement(By.id("sum")).getText());
    }
}
