package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
    title = "pcs-edit-section-title-description",
    pageSave = "org.wikipedia:id/page_save",
    navigateUpButton = "//android.widget.ImageButton[@content-desc='Navigate up']",
    searchCloseButton = "org.wikipedia:id/search_close_btn",
    footerElement = "//*[@resource-id='pcs-footer-container-readmore-heading'][@text='READ MORE']";

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(title), "Cannot find title article on page", 15);
    }

    public String getArticleTitle(){
       return this.waitForTitleElement().getText();
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(footerElement), "Cannot find the of article", 20);
    }

    public void addArticleToDefaultList(){
        this.waitForElementAndClick(By.id(pageSave), "Cannot find saveButton", 5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(navigateUpButton), "Cannot find navigate up button", 5);
        this.waitForElementAndClick(By.id(searchCloseButton), "Cannot find close button", 5);
    }


}
