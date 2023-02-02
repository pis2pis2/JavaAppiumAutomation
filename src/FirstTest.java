import lib.CoreTestCase;
import lib.ui.*;
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

}
