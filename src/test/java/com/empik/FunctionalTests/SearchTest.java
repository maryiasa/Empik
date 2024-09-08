package com.empik.FunctionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.Header;
import com.empik.pages.SearchPage;
import com.empik.utils.ConfigurationReader;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class SearchTest extends CommonTest {

    private static final Logger log = LogManager.getLogger(SearchTest.class);

    @Parameters
public String setUpTestValues(String key) {
    Map<String, String> testValues = new HashMap<>();
    testValues.put("psearch", ConfigurationReader.getTestValue(TestValues.PSEARCH.PSEARCH));
    testValues.put("nsearch", ConfigurationReader.getTestValue(TestValues.NSEARCH.NSEARCH));
    return testValues.get(key).toString().replaceAll("\"", "").toLowerCase();
}

    @Test
    public void searchItemFound() {
        String search = setUpTestValues("psearch");
        Header homePageHeader = new Header();
        SearchPage searchPage = homePageHeader.sendSearchKeys(search);
        String searchResult = searchPage.getElementText(searchPage.getSearchResultRow());
        String searchResultCount = searchPage.getElementText(searchPage.getSearchResultCountValue());
        Assert.assertTrue(search.equalsIgnoreCase(searchResult));
        Assert.assertNotEquals(searchResultCount, "0");
    }

    @Test
    public void searchItemNotFound() {
        String search = setUpTestValues("nsearch");
        Header homePageHeader = new Header();
        SearchPage searchPage = homePageHeader.sendSearchKeys(search);
        String searchResult = searchPage.getElementText(searchPage.getEmptySearchResultRow());
        Assert.assertTrue(searchResult.contains(search + "\n" +
                "nie możemy znaleźć wyników dla tego hasła."));

    }
}