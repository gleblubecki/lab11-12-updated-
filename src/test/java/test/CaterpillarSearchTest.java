package test;

import model.Product;
import org.testng.annotations.Test;
import page.CaterpillarHomePage;
import page.CaterpillarSearchResultPage;
import service.TestDataReader;
import org.testng.Assert;
import service.ProductCreator;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CaterpillarSearchTest extends CommonConditions {
    public static String SEARCH_QUERY_FOR_COMMON_RESULTS = TestDataReader.getTestData("testdata.search_query_for_common_results");
    public static String INVALID_SEARCH_QUERY = TestDataReader.getTestData("testdata.invalid_search_query");
    public static String SEARCH_QUERY_FOR_SPECIFIC_SEARCH = TestDataReader.getTestData("testdata.search_query_for_specific_search");
    public static String SUBSTRING_OF_SEARCHED_ITEMS = TestDataReader.getTestData("testdata.substring_of_searched_items");
    public static String EMPTY_SEARCH_RESULT_MESSAGE = TestDataReader.getTestData("testdata.empty_search_result_message");

    @Test
    public void handleCommonSearchResultTest() {
        List<String> textOfSearchedItems = new CaterpillarHomePage(driver)
                .openPage()
                .searchForTerms(SEARCH_QUERY_FOR_COMMON_RESULTS)
                .getSearchedItemsText();

        assertThat(textOfSearchedItems, everyItem(containsString(SUBSTRING_OF_SEARCHED_ITEMS)));
    }

    @Test
    public void handleIncorrectSearchQueryResultTest() {
        String searchResultMessage = new CaterpillarHomePage(driver)
                .openPage()
                .searchForTerms(INVALID_SEARCH_QUERY)
                .getEmptySearchResultMessage();

        Assert.assertTrue(searchResultMessage.contains(EMPTY_SEARCH_RESULT_MESSAGE),
                "The empty result message was not shown!");
    }

    @Test
    public void handleSpecificSearchResultTest() throws InterruptedException {
        Product testProduct = ProductCreator.withCredentialsFromSpecificProperty();

        CaterpillarSearchResultPage catSearchResultPage = new CaterpillarHomePage(driver)
                .openPage()
                .searchForTerms(SEARCH_QUERY_FOR_SPECIFIC_SEARCH)
                .selectFilterProductSize();

        Thread.sleep(1000L);

        String obtainedProductName = catSearchResultPage
                .getProductName();
        String obtainedProductPrice = catSearchResultPage
                .getProductPrice();

        assertThat(obtainedProductName, is(equalTo(testProduct.getName())));
    }

}
