package com.empik.pages;

import com.empik.Constants;
import com.empik.utils.DriverManager;
import com.empik.utils.JSExecutorUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    public Page() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void open() {
        DriverManager.getDriver().get(Constants.HOME_URL);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getPageUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public String getTextByIDJSExecutor(String value) {
        return JSExecutorUtil.getTextByID(value);
    }

    public void switchToFrame(WebElement element) {
        DriverManager.getDriver().switchTo().frame(element);
    }

    public void switchToDefaultFrame() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public void sendTestKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    public LogInPage clickBtnAndReturnLogInPage(WebElement element) {
        element.click();
        return new LogInPage();
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
