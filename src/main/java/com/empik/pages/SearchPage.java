package com.empik.pages;

import com.empik.utils.ActionsUtil;
import com.empik.utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
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

    public SearchPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

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

    public String getElementText(WebElement element) {
        if (element == searchResultRow || element == emptySearchResult) {
            return element.getText().toString().split(": ")[1].toLowerCase();
        } else if (element == searchResultCount) {
            return element.getText().toString().split(" ")[1].toLowerCase();
        }
        return element.getText();
    }


    public void addFirstItemToCart() {
        ActionsUtil.hoverAndClick(firstItemCard, addFirstItemToCartBtn);
    }
}
