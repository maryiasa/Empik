package com.empik.pages.HomePage;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "(//*[@class = 'css-18n58r'])[1]")
    private WebElement allowCookiesBtn;

    public HomePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void open() {
        DriverManager.getDriver().get("https://www.empik.com/");
    }

    public void clickCookies() {
        allowCookiesBtn.click();
    }

}
