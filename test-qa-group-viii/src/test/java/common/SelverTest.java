package common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SelverTest {

  public static ChromeDriver driver;

  @Test
  public void test() {
    System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://www.selver.ee/");
    driver.manage().window().setSize(new Dimension(1936, 1048));
    driver.findElement(By.name("q")).click();
    driver.findElement(By.name("q")).sendKeys("Moet & Chandon Nectar Imperial 75 cl karbis");
    {
      WebElement element = driver.findElement(By.cssSelector(".klevu-img-wrap > img"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    driver.findElement(By.cssSelector("body")).click();
    Assertions.assertEquals("Moet & Chandon Nectar Imperial 75 cl karbis",driver.findElement(By.cssSelector(".ProductName")).getText());
    driver.findElement(By.cssSelector(".Product__button")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      wait.until(ExpectedConditions.textToBe(By.cssSelector(".ProductNotification__info"), "1 tk ostukorvis"));
    }
    driver.findElement(By.cssSelector(".Microcart__text")).click();
    {
      List<WebElement> elements = driver.findElements(By.linkText("Moet & Chandon Nectar Imperial 75 cl karbis"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".RemoveButton use")).click();
    Assertions.assertEquals("Ostukorvis pole tooteid.",driver.findElement(By.cssSelector(".empty-products__title")).getText());
  }
}
