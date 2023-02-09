package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
    title = "id:pcs-edit-section-title-description",
    pageSave = "id:org.wikipedia:id/page_save",
    navigateUpButton = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    searchCloseButton = "id:org.wikipedia:id/search_close_btn",
    footerElement = "xpath://*[@resource-id='pcs-footer-container-readmore-heading'][@text='READ MORE']";

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(title, "Cannot find title article on page", 15);
    }

    public String getArticleTitle(){
       return this.waitForTitleElement().getText();
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(footerElement, "Cannot find the of article", 20);
    }

    public void addArticleToDefaultList(){
        this.waitForElementAndClick(pageSave, "Cannot find saveButton", 5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(navigateUpButton, "Cannot find navigate up button", 5);
    }

    public void assertArticleTitlePresent(){
        this.assertElementPresent(title, "Заголовок статьи отсутствует, хотя должен присутствовать");
    }


}
