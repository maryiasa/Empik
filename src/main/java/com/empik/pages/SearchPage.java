package com.empik.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends Header {

    private static final Logger log = LogManager.getLogger(SearchPage.class);
    @FindBy(css = ".js-searchHeader")
    private WebElement searchResultRow;

    @FindBy(className = "switcher__found")
    private WebElement searchResultCount;

    @FindBy(className = "empty-result-content__info")
    private WebElement emptySearchResult;

    @FindBy(xpath = "(//*[@class = 'search-list-item-hover'])[1]")
    private WebElement firstItemCard;

    @FindBy(xpath = "(//*[@class = 'ta-product-title'])[1]")
    private WebElement firstItemTitle;

    @FindBy(xpath = "(//*[@class = 'search-list-item-hover'])[4]")
    private WebElement forthItemCard;

    @FindBy(xpath = "(//*[@class = 'show-on-hover']/button)[1]")
    private WebElement addFirstItemToCartBtn;

    private String noResultText = "\nnie możemy znaleźć wyników dla tego hasła.";

    public WebElement getSearchResultRow() {
        return searchResultRow;
    }

    public WebElement getEmptySearchResultRow() {
        return emptySearchResult;
    }

    public WebElement getSearchResultCountValue() {
        return searchResultCount;
    }

    public WebElement getForthItemCard() {
        return forthItemCard;
    }

    public WebElement getFirstItemTitle() {
        return firstItemTitle;
    }

    public WebElement getAddFirstItemToCartBtn() {
        return addFirstItemToCartBtn;
    }

    public WebElement getFirstItemCard() {
        return firstItemCard;
    }

    public String getNoResultText() {
        return noResultText;
    }

    @Override
    public String getElementText(WebElement element) {
        if (element == searchResultRow) {
            return element.getText().split("Wyniki wyszukiwania dla: ")[1].toLowerCase();
        } else if (element == emptySearchResult) {
            return element.getText().split("Szukasz: ")[1].toLowerCase();
        } else if (element == searchResultCount) {
            return element.getText().split(" ")[1].toLowerCase();
        }
        return element.getText();
    }

}
