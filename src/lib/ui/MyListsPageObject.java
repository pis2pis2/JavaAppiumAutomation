package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    private static String getFolderXpathByName(String nameFolder) {
        return nameFolderTpl.replace("{nameFolder}", nameFolder);
    }

    private static String getSavedArticleXpathByTitle(String title) {
        return articleByTitleTpl.replace("{title}", title);
    }

    private static final String
            nameFolderTpl = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{nameFolder}']",
            articleByTitleTpl = "xpath://*[@text = '{title}']";

    public void openFolderByName(String nameFolder){
        this.waitForElementAndClick(getFolderXpathByName(nameFolder), "Cannot find folder by name " + nameFolder, 5);
    }

    public void swipeArticleTitleToDelete(String articleTitle){
        this.waitForArticleToAppearByTitle(articleTitle);
        this.swipeElementToLeft(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article by title " + articleTitle);
        this.waitForArticleToDisappearByTitle(articleTitle);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle){
        this.waitForElementNotPresent(getSavedArticleXpathByTitle(articleTitle), "Cannot delete saved article by title " + articleTitle, 10);
    }

    public void waitForArticleToAppearByTitle(String articleTitle){
        this.waitForElementPresent(getSavedArticleXpathByTitle(articleTitle), "Cannot find saved article by title " + articleTitle, 10);
    }
}
