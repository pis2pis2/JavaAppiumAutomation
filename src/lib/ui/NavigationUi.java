package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUi extends MainPageObject{
    public NavigationUi(AppiumDriver driver) {
        super(driver);
    }
    private static final String
    savedButton = "//android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout/android.widget.ImageView";

    public void clickSavedButton(){
        this.waitForElementAndClick(By.xpath(savedButton), "Cannot find saved button", 5);
    }
}
