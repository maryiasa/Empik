package com.empik.pages;

import com.empik.utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
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
