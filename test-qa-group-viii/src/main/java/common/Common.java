package common;

import com.opencsv.CSVReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;

public class Common {

    public  WebDriver driver;

    public static WebDriverWait wait;

    private static final Logger log = LogManager.getLogger(Common.class);

    public WebDriver setUpDriver(String webBrowser, String url , int seconds){
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        return driver;
    }

    public WebDriverWait waitWeb(WebDriver driver, int seconds){

        return new WebDriverWait(driver, Duration.ofSeconds(seconds));

    }
    public ArrayList<String[]> readCsvFile(String filePath){
        CSVReader reader = null;
        ArrayList<String []> output = new ArrayList<String[]>();

        try
        {
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader(filePath));
            String [] nextLine;
            //reads one line at a time

            while ((nextLine = reader.readNext()) != null)
            {   output.add(nextLine);
                for(String token : nextLine)
                {
                    log.info(token);
                }
                log.info("\n");

            }
        }
        catch (Exception e)
        {
           log.error("Input output problem. More info {}", e.getMessage());
        }
        return output;
    }

}
