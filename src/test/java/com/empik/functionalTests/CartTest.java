package com.empik.functionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.CartPage;
import com.empik.pages.Header;
import com.empik.pages.SearchPage;
import com.empik.utils.ActionsUtil;
import com.empik.utils.ConfigurationReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CartTest extends CommonTest {

    String search;
    Header homePageHeader;

    @BeforeMethod
    public void setUpCartTestValues() {
        search = ConfigurationReader.getTestValue(TestValues.PSEARCH.PSEARCH);
        homePageHeader = new Header();
    }

    @Test
    public void addItemToCart() {
        SearchPage searchPage = homePageHeader.sendKeysAndReturnSearchPage(homePageHeader.getSearchInput(), search);
        String itemInfoSearch = searchPage.getElementText(searchPage.getFirstItemTitle());
        ActionsUtil.scroll(searchPage.getForthItemCard());
        ActionsUtil.hoverAndClick(searchPage.getFirstItemCard(), searchPage.getAddFirstItemToCartBtn());
        CartPage cartPage = searchPage.clickBtnAndReturnCartPage(searchPage.getCartBtn());
        Assert.assertEquals(itemInfoSearch, cartPage.getElementText(cartPage.getItemTitle()));

    }
}
