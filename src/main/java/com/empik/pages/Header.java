package com.empik.pages;

import com.empik.utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class Header extends HomePage {

    @FindBy(css = ".empikNav__userText.ta-login-link")
    private WebElement logInBtn;

    @FindBy(className = "css-1sobvo3")
    private WebElement searchInput;

    @FindBy(id = "simple-dropdown4")
    private WebElement cartBtn;

    public Header() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public LogInPage clickLogInBtn() {
        logInBtn.click();
        return new LogInPage();
    }

    public String getLogInBtn() {
        return logInBtn.getText();
    }

    public SearchPage sendSearchKeys(String search) {
        searchInput.sendKeys(search);
        searchInput.submit();
        return new SearchPage();
    }

    public CartPage clickCartBtn() {
        cartBtn.click();
        return new CartPage();
    }
}
