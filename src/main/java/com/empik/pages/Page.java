package com.empik.pages;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    public Page() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public void clickBtn(WebElement element) {
        element.click();
    }

    public void open() {
        DriverManager.getDriver().get("https://www.empik.com/");
    }

    public LogInPage clickBtnAndReturnLogInPage(WebElement element) {
        element.click();
        return new LogInPage();
    }

    public CartPage clickBtnAndReturnCartPage(WebElement element) {
        element.click();
        return new CartPage();
    }

    public LogInPage sendUserEmailKeys(WebElement element, String keys) {
        element.sendKeys(keys);
        return new LogInPage();
    }

    public SearchPage sendKeysAndReturnSearchPage(WebElement element, String keys) {
        element.sendKeys(keys);
        element.submit();
        return new SearchPage();
    }

}
