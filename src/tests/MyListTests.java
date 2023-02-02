package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUi;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        NavigationUi navigationUi = new NavigationUi(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        System.out.println(articleTitle);
        articlePageObject.addArticleToDefaultList();
        articlePageObject.closeArticle();
        searchPageObject.clickCancelButton();
        this.clickBack();
        this.clickBack();
        navigationUi.clickSavedButton();
        myListsPageObject.openFolderByName("Saved");
        myListsPageObject.swipeArticleTitleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticles() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        NavigationUi navigationUi = new NavigationUi(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String articleTitle1 = articlePageObject.getArticleTitle();
        System.out.println(articleTitle1);
        articlePageObject.addArticleToDefaultList();
        articlePageObject.closeArticle();
        searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        System.out.println(articleTitle2);
        articlePageObject.addArticleToDefaultList();
        articlePageObject.closeArticle();
        this.clickBack();
        this.clickBack();
        navigationUi.clickSavedButton();
        myListsPageObject.openFolderByName("Saved");
        myListsPageObject.swipeArticleTitleToDelete(articleTitle1);
        searchPageObject.clickByArticleWithSubstring("Island in Indonesia");
        articlePageObject.waitForTitleElement();
        String articleTitle2AfterSaved = articlePageObject.getArticleTitle();
        assertEquals("Ожидаемый и фактический заголовок 2-ой статьи не совпадают", articleTitle2, articleTitle2AfterSaved);
    }
}
