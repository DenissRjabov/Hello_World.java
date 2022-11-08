package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Common {

    public  WebDriver driver;

    public static WebDriverWait wait;

    public WebDriver setUpDriver(String webBrowser, String url){
        if(webBrowser == null) webBrowser ="chrome";
        switch (webBrowser) {
            case ("fireFox"):
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case ("edge"):
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1936, 1048));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public WebDriverWait waitWeb(WebDriver driver, int seconds){

        return new WebDriverWait(driver, Duration.ofSeconds(seconds));

    }

}
