package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotGameDataDrivenTest {
    static Common common = new Common();

    private static final Logger log = LogManager.getLogger(SlotGameDataDrivenTest.class);

    static SlotGameCommon slotCommon = new SlotGameCommon();

    static String browser = System.getProperty("web.browser.type");

    public static WebDriver driver;

    public int errorCount;


    @BeforeAll
    public static void setUp(){

        driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html", 3);
    }

    @Test
    public void dataTest() {

        ArrayList<String []> dataInput =
            common.readCsvFile("src/test/resources/slotGameData.csv");
        int dataInputIndex = 1;
        while (dataInput.size() > dataInputIndex) {
            String [] dataSet = dataInput.get(dataInputIndex);
            errorCount = errorCount + slotCommon.game(
                    dataSet[0],
                    dataSet[1],
                    driver,
                    dataSet[2].equals("win"));
            dataInputIndex++;
        }
        assertEquals(0,errorCount);
    }

    @AfterAll
    public static void closeBrowser(){
        driver.quit();
    }
}
