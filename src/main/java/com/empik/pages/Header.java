package com.empik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends HomePage {

    @FindBy(css = ".empikNav__userText.ta-login-link")
    private WebElement logInBtn;

    @FindBy(xpath = "//*[@title = 'Zarejestruj się']")
    private WebElement registerBtn;

    @FindBy(className = "css-1sobvo3")
    private WebElement searchInput;

    @FindBy(id = "simple-dropdown4")
    private WebElement cartBtn;

    String registerBtnText = "Załóż je";
    String loginBtnText = "Witaj\nzaloguj się";

    public WebElement getLogInBtn() {
        return logInBtn;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getCartBtn() {
        return cartBtn;
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }

    public String getRegisterBtnText() {
        return registerBtnText;
    }

    public String getLoginBtnText() {
        return loginBtnText;
    }
}
