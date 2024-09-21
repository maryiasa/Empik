package com.empik.pages;

import com.empik.exceptions.CaptchaException;
import com.empik.exceptions.CaptchaNotFound;
import com.empik.utils.Waiters;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

@Log4j2
public class LogInPage extends Page {

    private static final Logger log = LogManager.getLogger(LogInPage.class);

    @FindBy(xpath = "//*[@id=\"user-login-form\"]/h2")
    private WebElement userLoginFormName;

    @FindBy(name = "j_username")
    private WebElement userEmailInput;

    @FindBy(className = "truncate")
    private WebElement userEmail;

    @FindBy(xpath = "//*[@id=\"user-login-form\"]/div[4]/button")
    private WebElement nextBtn;

    @FindBy(xpath = "//*[@id=\"user-login-form\"]/div[5]/button")
    private WebElement logInBtn;

    @FindBy(css = ".error.show")
    private WebElement emailAlert;

    @FindBy(xpath = "//iframe[@title = 'recaptcha challenge expires in two minutes']")
    private WebElement reCaptchaFrame;

    @FindBy(id = "user-password")
    private WebElement pwdInput;

    @FindBy(xpath = "//*[@class = 'error show']")
    private WebElement reCaptchaError;

    private String pageNameEmail = "Zaloguj się lub zarejestruj";
    private String pageNamePwd = "Witaj ponownie, zaloguj się";
    private String EmailAlertText = "Pole wymagane";

    public WebElement getLoginPageName() {
        return userLoginFormName;
    }

    public WebElement getEmail() {
        return userEmail;
    }

    public WebElement getEmailAlert() {
        return emailAlert;
    }

    public WebElement getUserEmailInput() {
        return userEmailInput;
    }

    public WebElement getPwdInput() {
        return pwdInput;
    }

    public WebElement getLogInBtn() {
        return logInBtn;
    }

    public WebElement getReCaptchaError() {
        return reCaptchaError;
    }

    public String getPageNameEmail() {
        return pageNameEmail;
    }

    public String getPageNamePwd() {
        return pageNamePwd;
    }

    public String getEmailAlertText() {
        return EmailAlertText;
    }

    public Page clickNextBtn() throws Exception {
        String mainWnd = getPageUrl();
        //throw exception if captcha is displayed
        try {
            nextBtn.click();
            Waiters.wait(7000);
            if (reCaptchaFrame.isDisplayed()) {
                throw new CaptchaException();
            } else throw new CaptchaNotFound();

        } catch (CaptchaNotFound e) {
            log.info(e.getMessage());

            if (Objects.equals(mainWnd, getPageUrl())) {
                return new LogInPage();
            } else return new RegistrationPage();

        } catch (CaptchaException e) {
            log.error(e.getMessage());
            throw new CaptchaException();
        }
    }

}
