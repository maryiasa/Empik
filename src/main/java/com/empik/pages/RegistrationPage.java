package com.empik.pages;

import com.empik.utils.DriverManager;
import com.empik.pages.HomePage.HomePage;

import com.empik.utils.JSExecutorUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends HomePage {

    @FindBy(xpath = "//div[1]/h2")
    private WebElement userRegistrationFormName;

    @FindBy(id = "registration-email")
    private WebElement userRegistrationEmailInput;

    public RegistrationPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public String getPageName() {
        return userRegistrationFormName.getText();
    }

    public String getUserRegistrationEmail() {
        return JSExecutorUtil.getTextByID(DriverManager.getDriver(), "registration-email");
    }
}
