package basar;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import runner.Webapp;

import com.google.common.base.Function;

import controller.UserResource;

public class BasarTest {

    static Webapp webapp;
    
    static WebDriver driver;
    
    @BeforeClass
    public static void startApp() throws Exception {
        webapp = new Webapp();
        webapp.start();
        DesiredCapabilities cab = new DesiredCapabilities();
        driver = new PhantomJSDriver(cab);
    }
    
    @AfterClass
    public static void stopApp() throws Exception {
        webapp.stop();
        webapp = null;
        driver.quit();
        driver = null;
    }
    
    @Rule
    public TestWatcher takesScreenshotRule = new TestWatcher() {
        
        @Override
        protected void failed(Throwable e, Description description) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("build", "screendump.png"));
            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
        };
    
    };
    
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
