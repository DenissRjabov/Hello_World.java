package common;

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


    public void game( String winCombination, String winAmount, WebDriver gameDriver) {

        driver = gameDriver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("spinButton")));

        System.out.println("Game for win combination " + winCombination);

        errorCount = 0;

        balance = getBalance();

        // Pay table check
        if(!driver.findElement(
                        By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win"
                                + winCombination.replace("0","")))
                .isDisplayed())
        {
            System.out.println("Error: Pay table in incorrect view");
            errorCount++;
        }

        System.out.println("---------Game Started---------");
        System.out.println("Initial balance : " + balance);

        driver.findElement(By.cssSelector("#testdata")).clear();
        driver.findElement(By.cssSelector("#testdata")).sendKeys(winCombination);
        driver.findElement(By.cssSelector("#spinButton")).click();

        // Win box check
        element = driver.findElement(By.id("winbox"));
        if(!element.isDisplayed()){
            System.out.println("Error: Win Box is not displayed");
            errorCount++;
        }
        else{
            System.out.println("Win Box is displayed");
            if(element.getText().equals("Win " + winAmount + " coins")){
                System.out.println("Win Box text : " + element.getText());
            }
            else{
                System.out.println("Error Win Box text incorrect : " + element.getText());
                errorCount++;
            }
        }

        //Check win combination play table

        char[] charArray = winCombination.toCharArray();
        errorCount = checkPlayTable(String.valueOf(charArray[0]),"1",errorCount);
        errorCount = checkPlayTable(String.valueOf(charArray[1]),"2",errorCount);
        errorCount = checkPlayTable(String.valueOf(charArray[2]),"3",errorCount);
        errorCount = checkPlayTable(String.valueOf(charArray[3]),"4",errorCount);
        errorCount = checkPlayTable(String.valueOf(charArray[4]),"5",errorCount);

        // Pay table check
        if(!driver.findElement(
                        By.cssSelector("#paytable > center > div:nth-child(1) > table > tbody > tr.win" +
                                winCombination.replace("0","") + ".achievement"))
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
        if((balance + Integer.valueOf(winAmount) - 1 ) != finalBalance){
            System.out.println("Error: Final balance is incorrect");
            errorCount++;
        }

        System.out.println("Final balance   : " + finalBalance);
        System.out.println("---------Game Stopped---------\n");

        // Errors check
        assertEquals(0,errorCount);

    }

    public int getBalance(){
        return Integer.parseInt(driver.findElement(By.cssSelector("#balance-value")).getAttribute("value"));
    }

    public int checkPlayTable(String winDigit, String reelNumber, Integer count){

        int output = count;

        String cssSelector = "div.notch.notch2.blinkme";

        if(winDigit.equals("0")) {
            cssSelector = "div.notch.notch2";
        }

        element = driver.findElement(By.cssSelector("#reel" + reelNumber + " > " + cssSelector));
        if(element.isDisplayed()) {
            if (element.getText().equals(winDigit)){
                System.out.println("Digit in reel " + reelNumber + " displayed correctly");
            }
            else {
                System.out.println("Error digit in reel " + reelNumber + " displayed with incorrect amount : " + element.getText());
                output++;
            }
        }
        else{
            System.out.println("Error digit in reel " + reelNumber + " have incorrect css " + element.getText());
            output++;
        }
        return output;
    }
}
