package com.empik.functionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.Header;
import com.empik.pages.SearchPage;
import com.empik.utils.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchTest extends CommonTest {

    String psearch;
    String nsearch;
    Header homePageHeader;

    @BeforeMethod
    public void setUpSearchTestValues() {
        psearch = ConfigurationReader.getTestValue(TestValues.PSEARCH.PSEARCH);
        nsearch = ConfigurationReader.getTestValue(TestValues.NSEARCH.NSEARCH);
        homePageHeader = new Header();
    }

    @Test
    public void searchItemFound() {
        SearchPage searchPage = homePageHeader.sendKeysAndReturnSearchPage(homePageHeader.getSearchInput(), psearch);

        String searchResult = searchPage.getElementText(searchPage.getSearchResultRow());
        String searchResultCount = searchPage.getElementText(searchPage.getSearchResultCountValue());

        Assert.assertTrue(psearch.equalsIgnoreCase(searchResult));
        Assert.assertNotEquals(searchResultCount, "0");
    }

    @Test
    public void searchItemNotFound() {
        SearchPage searchPage = homePageHeader.sendKeysAndReturnSearchPage(homePageHeader.getSearchInput(), nsearch);

        String searchResult = searchPage.getElementText(searchPage.getEmptySearchResultRow());

        Assert.assertTrue(searchResult.contains(nsearch + searchPage.getNoResultText()));

    }
}