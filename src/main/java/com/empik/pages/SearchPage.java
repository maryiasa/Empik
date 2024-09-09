package com.empik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends Header {

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

    @Override
    public String getElementText(WebElement element) {
        if (element == searchResultRow || element == emptySearchResult) {
            return element.getText().toString().split(": ")[1].toLowerCase();
        } else if (element == searchResultCount) {
            return element.getText().toString().split(" ")[1].toLowerCase();
        }
        return element.getText();
    }

}
