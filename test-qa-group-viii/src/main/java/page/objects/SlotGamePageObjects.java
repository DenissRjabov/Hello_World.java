package page.objects;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SlotGamePageObjects {
    private Map<String, String> data;
    private WebDriver driver;
    private Duration timeout = Duration.ofSeconds(15);

    private final String pageLoadedText = "Your task is to automate as many test cases as you can think of";

    private final String pageUrl = "/edu/Test_Task.html";

    @FindBy(id = "balance-value")
    @CacheLookup
    private WebElement qaAutomationEngineerTestTask;

    @FindBy(id = "testdata")
    @CacheLookup
    private WebElement setTestData;

    @FindBy(id = "spinButton")
    @CacheLookup
    private WebElement spin1Coin;

    public SlotGamePageObjects() {
    }

    public SlotGamePageObjects(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public SlotGamePageObjects(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public SlotGamePageObjects(WebDriver driver, Map<String, String> data, Duration timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Spin 1 Coin Button.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects clickSpin1CoinButton() {
        spin1Coin.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects fill() {
        setQaAutomationEngineerTestTaskNumberField();
        setSetTestDataTextField();
        return this;
    }

    /**
     * Set default value to Qa Automation Engineer Test Task Number field.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects setQaAutomationEngineerTestTaskNumberField() {
        return setQaAutomationEngineerTestTaskNumberField(data.get("QA_AUTOMATION_ENGINEER_TEST_TASK"));
    }

    /**
     * Set value to Qa Automation Engineer Test Task Number field.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects setQaAutomationEngineerTestTaskNumberField(String qaAutomationEngineerTestTaskValue) {
        qaAutomationEngineerTestTask.sendKeys(qaAutomationEngineerTestTaskValue);
        return this;
    }

    /**
     * Set default value to Set Test Data Text field.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects setSetTestDataTextField() {
        return setSetTestDataTextField(data.get("SET_TEST_DATA"));
    }

    /**
     * Set value to Set Test Data Text field.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects setSetTestDataTextField(String setTestDataValue) {
        setTestData.sendKeys(setTestDataValue);
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the SlotGame class instance.
     */
    public SlotGamePageObjects verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
