package common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class SlotGameTest {

    static Common common = new Common();

    static String browser = System.getProperty("web.browser.type");

    public static WebDriver driver;

    @AfterAll
    public static void setUp(){
        driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html");

    }

    @Test
    public void test(){

    }

    @AfterAll
    public static void closeBrowser(){
        driver.quit();
    }
}
