package basar;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverAppTest {

  WebDriver driver;

  @Before
  public void setup() {
    driver = new ChromeDriver();
  }
  
  @Test
  public void manageUsers() {
    driver.get("http://localhost:8081/static/sellers.html");
    
    for (int i = 0; i < 1000; i++) {
    
      driver.findElement(By.id("newUser")).click();
      
      driver.findElement(By.id("basarNumber")).clear();
      driver.findElement(By.id("basarNumber")).sendKeys("100");
      driver.findElement(By.id("name")).clear();
      driver.findElement(By.id("name")).sendKeys("Christian");
      driver.findElement(By.id("lastname")).clear();
      driver.findElement(By.id("lastname")).sendKeys("Baranowski");
      driver.findElement(By.id("email")).clear();
      driver.findElement(By.id("email")).sendKeys("test@test.de");
      driver.findElement(By.id("saveUser")).click();
      
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='action' and @value='user-listed']")));
      assertEquals("100", driver.findElement(By.xpath("//tbody[@id='usersBody']/tr[1]/td[1]")).getText());
      
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));
      WebElement deleteBtn = driver.findElement(By.cssSelector("button.btn.deleteUserButton"));
      deleteBtn.click();
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='action' and @value='user-listed']")));
    }
  }
  
  @After
  public void cleanUp() {
    driver.quit();
  }
  
}
