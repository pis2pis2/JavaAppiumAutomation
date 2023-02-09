package tests.IOS;

import io.appium.java_client.AppiumDriver;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;

public class GetStartedTest extends iOSTestCase {

    public void testPassThroughWelcome(){
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();
        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();
    }
}
