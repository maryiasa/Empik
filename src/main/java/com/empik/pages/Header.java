package com.empik.pages;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public WebElement getLogInBtn() {
        return logInBtn;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getCartBtn() {
        return cartBtn;
    }

}
