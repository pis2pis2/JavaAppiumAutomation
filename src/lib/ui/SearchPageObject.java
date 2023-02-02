package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            searchInitElement = "//*[contains(@text, 'Search Wikipedia')]",
            searchInput = "//*[contains(@text, 'Search Wikipedia')]",
            searchCancelButton = "org.wikipedia:id/search_close_btn",
            searchResultBySubstringTpl = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text = '{SUBSTRING}']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return searchResultBySubstringTpl.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */
    public void initSearchInput(){
        this.waitForElementPresent(By.xpath(searchInitElement), "Cannot find searchInitElement, before clicking", 5);
        this.waitForElementAndClick(By.xpath(searchInitElement), "Cannot find searchInitElement", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(searchCancelButton), "Cannot find searchCancelButton", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(searchCancelButton), "SearchCancelButton is present", 5);
    }

    public void clickCancelButton() {
        waitForElementAndClick(By.id(searchCancelButton), "Cannot click searchCancelButton", 5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendCase(By.xpath(searchInput), searchLine, "Cannot find searchLineElement", 5);
    }

    public void waitForSearchResultToAppear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Cannot find searchResult with substring " + substring);
    }

    public void waitForSearchResultToDisappear(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementNotPresent((By.xpath(searchResultXpath)), "SearchResult with substring " + substring + " is present", 15);
    }

    public void clickByArticleWithSubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Cannot find and click searchResult with substring " + substring, 10);
    }

}
