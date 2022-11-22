package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotGameCommon {

    public static WebDriver driver;

    public static WebDriverWait wait;
    public static WebElement element;
    public static Integer balance;
    public static Integer finalBalance;
    public static Integer errorCount;

    private static final Logger log = LogManager.getLogger(SlotGameCommon.class);

    public int  game( String winCombination, String winAmount, WebDriver gameDriver, Boolean win) {

        driver = gameDriver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("spinButton")));

       log.info("Game for win combination {}", winCombination);

        errorCount = 0;

        balance = getBalance();

        // Pay table check
        if(win) {
            if (!driver.findElement(
                            By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win"
                                    + winCombination.replace("0", "")))
                    .isDisplayed()) {
                log.error("Error: Pay table in incorrect view");
                errorCount++;
            }
        }

        log.info("---------Game Started---------");
        log.info("Initial balance : {}", balance);

        driver.findElement(By.cssSelector("#testdata")).clear();
        driver.findElement(By.cssSelector("#testdata")).sendKeys(winCombination);
        driver.findElement(By.cssSelector("#spinButton")).click();

        // Win box check
        element = driver.findElement(By.id("winbox"));
        if(!element.isDisplayed() && win){
            log.error("Error: Win Box is not displayed");
            errorCount++;
        }
        else{
            if(win) {
                log.info("Win Box is displayed");
                if (element.getText().equals("Win " + winAmount + " coins")) {
                    log.info("Win Box text : {}", element.getText());
                } else {
                    log.error("Error Win Box text incorrect : {}", element.getText());
                    errorCount++;
                }
            }
        }

        //Check win combination play table

        char[] charArray = winCombination.toCharArray();
        errorCount = checkPlayTable(String.valueOf(charArray[0]),"1",errorCount, win);
        errorCount = checkPlayTable(String.valueOf(charArray[1]),"2",errorCount, win);
        errorCount = checkPlayTable(String.valueOf(charArray[2]),"3",errorCount, win);
        errorCount = checkPlayTable(String.valueOf(charArray[3]),"4",errorCount, win);
        errorCount = checkPlayTable(String.valueOf(charArray[4]),"5",errorCount, win);

        // Pay table check
        if(win) {
            try {
                if (!driver.findElement(
                                By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win" +
                                        winCombination.replace("0", "") + ".achievement"))
                        .isDisplayed()) {
                    log.error("Error: Pay table not updated according to win amount");
                    errorCount++;
                } else {
                    log.info("Pay table updated according to win amount");
                }
            } catch (Exception e) {
                log.error("Error: Pay table not updated according to win amount");
                log.error("More information : {}", e.getMessage());
                errorCount++;
            }
        }
        // Final balance check
        finalBalance = getBalance();
        if((balance + Integer.valueOf(winAmount) - 1 ) != finalBalance){
            log.error("Error: Final balance is incorrect");
            errorCount++;
        }

        log.info("Final balance   : {}", finalBalance);

        // Errors check
        log.info("The amount of errors is {}",errorCount);
        log.info("---------Game Stopped---------\n");

        return errorCount;

    }

    public int getBalance(){
        return Integer.parseInt(driver.findElement(By.cssSelector("#balance-value")).getAttribute("value"));
    }

    public int checkPlayTable(String winDigit, String reelNumber, Integer count, Boolean win){

        int output = count;

        String cssSelector = "div.notch.notch2.blinkme";

        if(winDigit.equals("0") || !win ) {
            cssSelector = "div.notch.notch2";
        }
        try{
            element = driver.findElement(By.cssSelector("#reel" + reelNumber + " > " + cssSelector));
            if(element.isDisplayed()) {
                element = driver.findElement(By.cssSelector("#reel" + reelNumber + " > " + cssSelector));
                if (element.getText().equals(winDigit)){
                    log.info("Digit in reel {} displayed correctly", reelNumber);
                }
                else {
                    log.info("Error digit in reel {} displayed with incorrect amount : {}",
                            reelNumber,
                            element.getText());
                    output++;
                }
            }
            else{
                log.error("Error digit in reel {} have incorrect css {}",
                        reelNumber,
                        element.getText());
                output++;
            }
        }catch (Exception e){
            log.error("Error digit in reel {} not blinked. More info {}", reelNumber);
            log.error("More info {}", e.getMessage());
            output++;
        }
        return output;
    }
}
