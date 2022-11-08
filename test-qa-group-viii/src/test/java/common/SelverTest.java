package common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SelverTest {

  public static WebDriver driver;
  public static WebDriverWait wait;

  @BeforeAll
  public static void startUp() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.get("https://www.selver.ee/");
    driver.manage().window().setSize(new Dimension(1936, 1048));
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  @Test
  public void test() throws InterruptedException {

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    driver.findElement(By.name("q")).click();
    driver.findElement(By.name("q")).sendKeys("Moet & Chandon Nectar Imperial 75 cl karbis");
    Thread.sleep(4000);
    if(driver.findElement(By.cssSelector("#klevuSearchSuggestUL > li > a > div.klevu-name-desc > div.klevu-name")).getText().equals("Moet & Chandon Nectar Imperial 75 cl karbis"))
    driver.findElement(By.cssSelector("#klevuSearchSuggestUL > li > a > div.klevu-name-desc > div.klevu-name")).click();


    driver.findElement(By.cssSelector("body")).click();
    Assertions.assertEquals("Moet & Chandon Nectar Imperial 75 cl karbis",driver.findElement(By.cssSelector(".ProductName")).getText());
    driver.findElement(By.cssSelector(".Product__button")).click();

    wait.until(ExpectedConditions.textToBe(By.cssSelector(".ProductNotification__info"), "1 tk ostukorvis"));

    driver.findElement(By.cssSelector(".Microcart__text")).click();

    {
      List<WebElement> elements = driver.findElements(By.linkText("Moet & Chandon Nectar Imperial 75 cl karbis"));
      assert(elements.size() > 0);
    }

    driver.findElement(By.cssSelector(".RemoveButton use")).click();
    Assertions.assertEquals("Ostukorvis pole tooteid.",driver.findElement(By.cssSelector(".empty-products__title")).getText());
  }

  @AfterAll
  public static void shutDown(){
     driver.quit();
  }

}
