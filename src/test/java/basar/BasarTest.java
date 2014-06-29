package basar;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import runner.Webapp;
import basar.pages.BasarCashPage;
import controller.UserResource;

public class BasarTest {

    static Webapp webapp;
    
    static WebDriver driver;
    
    Wait<WebDriver> wait;

    @BeforeClass
    public static void startApp() throws Exception {
        webapp = new Webapp();
        webapp.start();
        driver = new ChromeDriver();
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
        BasarCashPage basarPage = 
              BasarCashPage.get(driver)
                      .basarNumber("100")
                      .price("10,50")
                      .description("Rad")
                  .add()
                      .basarNumber("100")
                      .price("20")
                  .add();
        assertEquals("30,50", basarPage.getSum());
    }
    
    @Test
    public void addArticel_UnknownBasarNumber() throws Exception {
        BasarCashPage basarPage = 
                BasarCashPage.get(driver)
                    .basarNumber("200")
                    .price("20")
                    .add();
        assertEquals("basarNumber, Die Basarnummer existiert nicht.", basarPage.getErrorMessages().get(0));
    }
    
    @Test
    public void addArticel_NoPrice() throws Exception {
        BasarCashPage basarPage = 
                BasarCashPage.get(driver)
                    .basarNumber("100")
                    .add();
        assertEquals("price, kann nicht leer sein", basarPage.getErrorMessages().get(0));
    }
    
}
