package common;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SlotGameTest {

    static Common common = new Common();

    static SlotGameCommon slotCommon = new SlotGameCommon();

    static String browser = System.getProperty("web.browser.type");

    public static WebDriver driver;


    @BeforeAll
    public static void setUp(){

        driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html", 3);
    }

    @Test
    @Order(1)
    public void winCombination111Test() {

        assertEquals(0,slotCommon.game("11100", "60",driver,true));
    }

    @Test
    @Order(2)
    public void winCombination1111Test() {

        assertEquals(0,slotCommon.game("11110", "80",driver, true));
    }

    @Test
    @Order(3)
    public void winCombination11111Test() {

        assertEquals(0,slotCommon.game("11111", "100",driver, true));
    }

    @Test
    @Order(4)
    public void winCombination222Test() {

        assertEquals(0,slotCommon.game("22200", "120",driver, true));
    }

    @Test
    @Order(5)
    public void winCombination2222Test()
    {

        assertEquals(0,slotCommon.game("22220", "160",driver, true));
    }

    @Test
    @Order(6)
    public void winCombination22222Test()
    {
        assertEquals(0,slotCommon.game("22222", "200",driver,true));
    }

    @Test
    @Order(16)
    public void gameWithCombination12300Test() {

        assertEquals(0,slotCommon.game("12300", "0",driver, false));
    }

    @Test
    @Order(7)
    public void winCombination333Test() {

        assertEquals(0,slotCommon.game("33300", "180",driver, true));
    }
    @Test
    @Order(8)
    public void winCombination3333Test() {

        assertEquals(0,slotCommon.game("33330", "240",driver, true));
    }

    @Test
    @Order(9)
    public void winCombination33333Test() {

        assertEquals(0,slotCommon.game("33333", "240",driver, true));
    }
    @Test
    @Order(10)
    public void winCombination444Test() {

        assertEquals(0,slotCommon.game("44400", "240",driver, true));
    }
    @Test
    @Order(11)
    public void winCombination4444Test() {

        assertEquals(0,slotCommon.game("44440", "320",driver, true));
    }
    @Test
    @Order(12)
    public void winCombination44444Test() {

        assertEquals(0,slotCommon.game("44444", "400",driver, true));
    }
    @Test
    @Order(13)
    public void winCombination555Test() {

        assertEquals(0,slotCommon.game("55500", "300",driver, true));
    }
    @Test
    @Order(14)
    public void winCombination5555Test() {

        assertEquals(0,slotCommon.game("55550", "400",driver, true));
    }
    @Test
    @Order(15)
    public void winCombination55555Test() {

        assertEquals(0,slotCommon.game("55555", "500",driver, true));
    }

    @AfterAll
    public static void closeBrowser(){
        driver.quit();
    }
}
