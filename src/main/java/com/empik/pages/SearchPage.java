package com.empik.pages;

import com.empik.pages.HomePage.HomePage;
import com.empik.utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class SearchPage extends HomePage {

    @FindBy(css = ".js-searchHeader")
    private WebElement searchResultRow;

    @FindBy(className = "switcher__found")
    private WebElement searchResultCount;

    @FindBy(className = "empty-result-content__info")
    private WebElement emptySearchResult;

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

    public String getSearchResult(WebElement element) {
        return element.getText().toString().split(": ")[1].toLowerCase();
    }

    public String getSearchResultCount(WebElement element) {
        return element.getText().toString().split(" ")[1].toLowerCase();
    }

}
