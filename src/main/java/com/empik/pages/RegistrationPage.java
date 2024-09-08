package com.empik.pages;

import com.empik.utils.DriverManager;

import com.empik.utils.JSExecutorUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
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
        return JSExecutorUtil.getTextByID("registration-email");
    }
}
