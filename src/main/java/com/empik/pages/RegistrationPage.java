package com.empik.pages;

import com.empik.utils.JSExecutorUtil;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class RegistrationPage extends HomePage {

    @FindBy(xpath = "//div[1]/h2")
    private WebElement userRegistrationFormName;

    @FindBy(id = "registration-email")
    private WebElement userRegistrationEmailInput;

    public WebElement getUserRegistrationFormName() {
        return userRegistrationFormName;
    }

    public String getUserRegistrationEmail() {
        return JSExecutorUtil.getTextByID("registration-email");
    }
}
