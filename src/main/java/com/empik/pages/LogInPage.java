package com.empik.pages;

import com.empik.exceptions.CaptchaException;
import com.empik.exceptions.CaptchaNotFound;
import com.empik.utils.DriverManager;
import com.empik.utils.Waiters;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

@Log4j2
public class LogInPage extends HomePage {

    private static final Logger log = LogManager.getLogger(LogInPage.class);

    @FindBy(xpath = "//*[@id=\"user-login-form\"]/h2")
    private WebElement userLoginFormName;

    @FindBy(name = "j_username")
    private WebElement userEmailInput;

    @FindBy(className = "truncate")
    private WebElement userEmail;

    @FindBy(xpath = "//*[@id=\"user-login-form\"]/div[4]/button")
    private WebElement nextBtn;

    @FindBy(css = ".error.show")
    private WebElement emailAlert;

    @FindBy(xpath = "//iframe[@title = 'recaptcha challenge expires in two minutes']")
    private WebElement reCaptchaFrame;

    public LogInPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElement getPageName() {
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

    public HomePage clickNextBtn() throws Exception {
        String mainWnd = DriverManager.getDriver().getCurrentUrl();
        nextBtn.click();
        Waiters.wait(7000);

        //throw exception if captcha is displayed
        try {
            if (reCaptchaFrame.isDisplayed()) {
                throw new CaptchaException();
            } else throw new CaptchaNotFound();

        } catch (CaptchaNotFound e) {
            log.info(e.getMessage());

            if (Objects.equals(mainWnd, DriverManager.getDriver().getCurrentUrl())) {
                return new LogInPage();
            } else return new RegistrationPage();

        } catch (CaptchaException e) {
            log.error(e.getMessage());
            throw new CaptchaException();
        }
    }

}
