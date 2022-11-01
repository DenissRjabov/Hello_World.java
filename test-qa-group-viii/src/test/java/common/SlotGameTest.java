package common;

import org.junit.jupiter.api.AfterAll;

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

    static String browser = System.getProperty("web.browser.type");

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement element;
    public static Integer balance;
    public static Integer finalBalance;
    public static Integer errorCount;

    @AfterAll
    public static void setUp(){
        //driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html");
    }

    @Test
    public void winCombination111Test()  {
        errorCount =0;
        driver = common.setUpDriver(browser,"http://asgag.eu/edu/Test_Task.html");

        balance = getBalance();

        // Pay table check
        if(!driver.findElement(
                By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win111"))
                .isDisplayed())
        {
            System.out.println("Error: Pay table in incorrect view");
            errorCount++;
        }

        System.out.println("---------Game Started---------");
        System.out.println("Initial balance : " + balance);

        driver.findElement(By.cssSelector("#testdata")).sendKeys("11100");
        driver.findElement(By.cssSelector("#spinButton")).click();

        // Win box check
        element = driver.findElement(By.id("winbox"));
        if(!element.isDisplayed()){
            System.out.println("Error: Win Box is not displayed");
            errorCount++;
        }
        else{
            System.out.println("Win Box is displayed");
             if(element.getText().equals("Win 60 coins")){
                 System.out.println("Win Box text : " + element.getText());
             }
             else{
                 System.out.println("Error Win Box text incorrect : " + element.getText());
                 errorCount++;
             }
        }

        // Pay table check
        if(!driver.findElement(
                By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win111.achievement"))
                .isDisplayed())
        {
            System.out.println("Error: Pay table not updated according to win amount");
            errorCount++;
        }
        else {
            System.out.println("Pay table updated according to win amount");
        }

        // Final balance check
        finalBalance = getBalance();
        if((balance + 59) != finalBalance){
            System.out.println("Error: Final balance is incorrect");
            errorCount++;
        }

        System.out.println("Final balance   : " + finalBalance);
        System.out.println("---------Game Stopped---------");

        // Errors check
        assertEquals(0,errorCount);

    }

    public int getBalance(){
        return Integer.parseInt(driver.findElement(By.cssSelector("#balance-value")).getAttribute("value"));
    }
    @AfterAll
    public static void closeBrowser(){
        driver.quit();
    }
}
