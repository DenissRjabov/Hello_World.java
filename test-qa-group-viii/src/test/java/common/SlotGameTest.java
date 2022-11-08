package common;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotGameTest {

    static Common common = new Common();

    static SlotGameCommon slotCommon = new SlotGameCommon();

    static String browser = System.getProperty("web.browser.type");

    public static WebDriver driver;


    @BeforeAll
    public static void setUp(){
        driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html");
    }

    @Test
    public void winCombination111Test() {
       slotCommon.game("11100", "60",driver);
    }

    @Test
    public void winCombination1111Test() {
        slotCommon.game("11110", "80",driver);
    }

    @Test
    public void winCombination11111Test() {
        slotCommon.game("11111", "100",driver);
    }

    @AfterAll
    public static void closeBrowser(){
        driver.quit();
    }
}