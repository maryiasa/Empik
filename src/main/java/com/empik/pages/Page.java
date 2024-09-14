package com.empik.pages;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    private final String homePageUrl = "https://www.empik.com/";
    private final String registrationPageUrl = "https://www.empik.com/rejestracja?continue=%2F";

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public String getRegistrationPageUrl() {
        return registrationPageUrl;
    }

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
        DriverManager.getDriver().get(getHomePageUrl());
    }

    public String getPageUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public LogInPage clickBtnAndReturnLogInPage(WebElement element) {
        element.click();
        return new LogInPage();
    }

    public void sendTestKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    public RegistrationPage clickBtnAndReturnRegistrationPage(WebElement element) {
        element.click();
        return new RegistrationPage();
    }

    public CartPage clickBtnAndReturnCartPage(WebElement element) {
        element.click();
        return new CartPage();
    }

    public SearchPage sendKeysAndReturnSearchPage(WebElement element, String keys) {
        element.sendKeys(keys);
        element.submit();
        return new SearchPage();
    }

}
