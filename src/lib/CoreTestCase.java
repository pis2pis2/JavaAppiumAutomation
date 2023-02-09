package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriver(capabilities);
//        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        this.rotateScreenPortrait();

    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void runAppInBackground(){
        driver.runAppInBackground(Duration.ofSeconds(2));
    }

    protected void clickBack() {
        driver.navigate().back();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("app", "C:\\Users\\Pavel_Stepanov\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "/Users/Pavel_Stepanov/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
            capabilities.setCapability("orientation", "PORTRAIT");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriver(DesiredCapabilities capabilities) throws Exception{
        String platform = System.getenv("PLATFORM");
        if (platform.equals(PLATFORM_ANDROID)){
            driver = new AndroidDriver(new URL(AppiumUrl), capabilities);

        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumUrl), capabilities);
        } else {
            throw new Exception("Cannot get driver. Platform value " + platform);
        }
        return driver;
    }

}
