import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("app", "C:\\Users\\Pavel_Stepanov\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find text", 5);
        waitForElementAndSendCase(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java", "Cannot find text", 5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Object-oriented programming language']"), "Cannot find text",
                15);

    }

    @Test
    public void testCancelSearch() {
       waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find text", 5);
       waitForElementAndSendCase(By.id("org.wikipedia:id/search_plate"), "Java", "Cannot find text", 5);
       waitForElementAndClear(By.id("org.wikipedia:id/search_plate"), "Cannot find text", 5);
//       waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find text", 5);
       waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Cannot find text", 5);

    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find text",
                5);
        waitForElementAndSendCase(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java",
                "Cannot find text", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Object-oriented programming language']"), "Cannot find text", 5);
        WebElement titleElement = waitForElementPresent(By.id("pcs-edit-section-title-description"),
                "Cannot find text");
        String articleHeader = titleElement.getAttribute("text");
        Assert.assertEquals("Ожидаемое название статьи не соответствует актуальному",
                "Object-oriented programming language", articleHeader);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage){
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendCase(By by, String value, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }
}
