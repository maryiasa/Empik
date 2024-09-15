package com.empik.pages;

import com.empik.exceptions.CaptchaException;
import com.empik.utils.Waiters;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class RegistrationPage extends HomePage {

    private static final Logger log = LogManager.getLogger(RegistrationPage.class);

    @FindBy(xpath = "//div[1]/h2")
    private WebElement userRegistrationFormName;

    @FindBy(id = "registration-email")
    private WebElement userRegistrationEmailInput;

    @FindBy(id = "registration-password")
    private WebElement userRegistrationPwdInput;

    @FindBy(id = "registration-phone")
    private WebElement userRegistrationPhoneInput;

    @FindBy(xpath = "(//*[@class= 'fake-checkbox'])[1]")
    private WebElement regulationsAndPrivacyPolicyCheckbox;

    @FindBy(xpath = "//*[@title = 'reCAPTCHA']")
    private WebElement reCaptchaFrame;

    @FindBy(xpath = "//*[@title = 'recaptcha challenge expires in two minutes']")
    private WebElement reCaptchaImageFrame;

    @FindBy(className = "recaptcha-checkbox-border")
    private WebElement recaptchaCheckbox;

    @FindBy(className = "register-submit")
    private WebElement signUpBtn;

    @FindBy(xpath = "//*[@class = 'cartInfoBox typeError']")
    private WebElement errorInfoBox;

    private String pageName = "Rejestracja";
    private String registrationAlert = "Podany adres e-mail jest już zarejestrowany. Przejdź do strony logowania";

    public WebElement getUserRegistrationFormName() {
        return userRegistrationFormName;
    }

    public WebElement getUserRegistrationEmailInput() {
        return userRegistrationEmailInput;
    }

    public WebElement getUserRegistrationPwdInput() {
        return userRegistrationPwdInput;
    }

    public WebElement getUserRegistrationPhoneInput() {
        return userRegistrationPhoneInput;
    }

    public WebElement getRegulationsAndPrivacyPolicyCheckbox() {
        return regulationsAndPrivacyPolicyCheckbox;
    }

    public WebElement getSignUpBtn() {
        return signUpBtn;
    }

    public WebElement getReCaptchaFrame() {
        return reCaptchaFrame;
    }

    public WebElement getErrorInfoBox() {
        return errorInfoBox;
    }

    public String getPageName() {
        return pageName;
    }

    public String getRegistrationAlert() {
        return registrationAlert;
    }

    public RegistrationPage clickRecaptchaCheckbox() throws Exception {
        try {
            switchToFrame(reCaptchaFrame);
            recaptchaCheckbox.click();
            switchToDefaultFrame();
            Waiters.wait(1000);

            if (reCaptchaImageFrame.isDisplayed()) {
                log.info("captcha block");
                throw new CaptchaException();
            } else {
                return new RegistrationPage();
            }

        } catch (CaptchaException e) {
            log.info(e.getMessage());
            throw new CaptchaException();
        }
    }

}
