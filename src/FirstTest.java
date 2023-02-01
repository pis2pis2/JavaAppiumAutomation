import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;




public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCheckInputText() {
        mainPageObject.waitForElementPresent(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Элемент 'Search Wikipedia' не найден");
        mainPageObject.assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Ожидаемый текст placeholder строки поиска не совпал с фактическим",
                "Search Wikipedia");

    }

    @Test
    public void testCancelSearchAnyArticles() {
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input line", 5);
        mainPageObject.waitForElementAndSendCase(By.id("org.wikipedia:id/search_plate"), "Java", "Cannot find input line two", 5);
        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_list"), "Cannot find result of search");
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find close button", 5);
        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"), "Result of search is present", 5);

    }

    @Test
    public void testCheckWordsInSearch() {
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input line", 5);
        mainPageObject.waitForElementAndSendCase(By.id("org.wikipedia:id/search_plate"), "Java", "Cannot find input line two", 5);
        mainPageObject.waitForElementsPresent((By.id("org.wikipedia:id/page_list_item_title")), "Cannot find results of search", 5);
        mainPageObject.assertElementsContainsText((By.id("org.wikipedia:id/page_list_item_title")), "Header of element is not contains expected text", "Java");
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find text",
                5);
        String searchText = "Linkin Park Discography";
        mainPageObject.waitForElementAndSendCase(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), searchText,
                "Cannot find text", 5);
    }

    @Test
    public void testSaveTwoArticles() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find text",
                5);
        mainPageObject.waitForElementAndSendCase(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java",
                "Cannot find text 'Java'", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Object-oriented programming language']"), "Cannot find text 'Object-oriented programming language'", 5);
        mainPageObject.waitForElementPresent(By.id("pcs-edit-section-title-description"),
                "Cannot find title of element");
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "Cannot find saveButton", 5);
        mainPageObject.clickBack();
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Island in Indonesia']"), "Cannot find text 'Island in Indonesia'", 5);
        mainPageObject.waitForElementPresent(By.id("pcs-edit-section-title-description"),
                "Cannot find title of element");
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/page_save"), "Cannot find saveButton", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot find navigate up button", 5);
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find close button", 5);
        mainPageObject.clickBack();
        mainPageObject.clickBack();
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='Saved']/android.widget.FrameLayout/android.widget.ImageView"), "Cannot find saved button", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text = 'Default list for your saved articles']"), "Cannot find text 'Default list for your saved articles'", 5);
        mainPageObject.waitForElementPresent(By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot find text 'Object-oriented programming language'");
        mainPageObject.waitForElementPresent(By.xpath("//*[@text = 'Island in Indonesia']"), "Cannot find saved article", 10);
        mainPageObject.swipeElementToLeft(By.xpath("//*[@text = 'Object-oriented programming language']"), "Cannot find saved article");
        mainPageObject.waitForElementNotPresent(By.xpath("//*[@text = 'Object-oriented programming language']"), "Cannot delete saved article", 10);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Island in Indonesia']"), "Cannot find text 'Island in Indonesia'", 5);
        String titleOfSecondArticle = mainPageObject.waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"), "text", "titleOfSecondArticle is not present in second article", 10);
        Assert.assertEquals("Ожидаемый и фактический заголовок 2-ой статьи не совпадают", "Island in Indonesia", titleOfSecondArticle);
    }

    @Test
    public void testAssertTitle() {
        mainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Cannot find text",
                5);
        mainPageObject.waitForElementAndSendCase(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Java",
                "Cannot find text 'Java'", 5);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'" +
                " and @text = 'Object-oriented programming language']"), "Cannot find text 'Object-oriented programming language'", 5);
        mainPageObject.assertElementPresent(By.id("pcs-edit-section-title-description"), "Фактический заголовок элемента не совпал с ожидаемым заголовком");


    }
}
