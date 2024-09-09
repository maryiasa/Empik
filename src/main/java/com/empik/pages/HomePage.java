package com.empik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(xpath = "(//*[@class = 'css-18n58r'])[1]")
    private WebElement allowCookiesBtn;

    public WebElement getAllowCookiesBtn() {
        return allowCookiesBtn;
    }

}
