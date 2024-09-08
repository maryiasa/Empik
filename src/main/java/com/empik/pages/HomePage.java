package com.empik.pages;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    @FindBy(xpath = "(//*[@class = 'css-18n58r'])[1]")
    private WebElement allowCookiesBtn;

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElement getAllowCookiesBtn() {
        return allowCookiesBtn;
    }

}
