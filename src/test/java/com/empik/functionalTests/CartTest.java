package com.empik.functionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.CartPage;
import com.empik.pages.Header;
import com.empik.pages.SearchPage;
import com.empik.utils.ActionsUtil;
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
public class CartTest extends CommonTest {

    private static final Logger log = LogManager.getLogger(CartTest.class);

    @Parameters
    public String setUpCartTestValues(String key) {
        Map<String, String> testValues = new HashMap<>();
        testValues.put("psearch", ConfigurationReader.getTestValue(TestValues.PSEARCH.PSEARCH));
        return testValues.get(key).replaceAll("\"", "").toLowerCase();
    }

    @Test
    public void addItemToCart() {
        String search = setUpCartTestValues("psearch");
        Header homePageHeader = new Header();
        SearchPage searchPage = homePageHeader.sendKeysAndReturnSearchPage(homePageHeader.getSearchInput(), search);
        String itemInfoSearch = searchPage.getElementText(searchPage.getFirstItemTitle());
        ActionsUtil.scroll(searchPage.getForthItemCard());
        ActionsUtil.hoverAndClick(searchPage.getFirstItemCard(), searchPage.getAddFirstItemToCartBtn());
        CartPage cartPage = searchPage.clickBtnAndReturnCartPage(searchPage.getCartBtn());
        Assert.assertEquals(itemInfoSearch, cartPage.getElementText(cartPage.getItemTitle()));

    }
}
