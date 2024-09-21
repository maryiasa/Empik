package com.empik.functionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.Header;
import com.empik.pages.LogInPage;
import com.empik.pages.RegistrationPage;
import com.empik.utils.ConfigurationReader;
import com.empik.utils.Waiters;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends CommonTest {

    String email;
    String pwd;
    LogInPage logInPage;

    @BeforeMethod
    public void setUpLoginTest() {
        email = ConfigurationReader.getTestValue(TestValues.EMAIL.EMAIL);
        pwd = ConfigurationReader.getTestValue(TestValues.PWD.PWD);

        Header homePageHeader = new Header();
        String logInBtnText = homePageHeader.getElementText(homePageHeader.getLogInBtn());
        Assert.assertEquals(logInBtnText, homePageHeader.getLoginBtnText());
        logInPage = homePageHeader.clickBtnAndReturnLogInPage(homePageHeader.getLogInBtn());
        Assert.assertEquals(logInPage.getElementText(logInPage.getLoginPageName()), logInPage.getPageNameEmail());
    }
//delete this method after debug
    @Test
    public void positiveCheck() {
        logInPage.sendTestKeys(logInPage.getUserEmailInput(), email);
    }

    @Test
    public void negativeLogInUserRegistered() throws Exception {
        logInPage.sendTestKeys(logInPage.getUserEmailInput(), email);
        Waiters.wait(5000);
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getLoginPageName()), logInPage1.getPageNamePwd());
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getEmail()), "E-mail: " + email);

        logInPage1.sendTestKeys(logInPage.getPwdInput(), pwd);
        logInPage1.getLogInBtn().click();
        Assert.assertTrue(logInPage1.getReCaptchaError().isDisplayed());

    }

    @Test
    public void negativeLogInUserNotRegistered() throws Exception {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        logInPage.sendTestKeys(logInPage.getUserEmailInput(), email);
        Waiters.wait(3000);
        RegistrationPage registrationPage = (RegistrationPage) logInPage.clickNextBtn();
        Assert.assertEquals(registrationPage.getElementText(registrationPage.getUserRegistrationFormName()), registrationPage.getPageName());
        Assert.assertEquals(registrationPage.getTextByIDJSExecutor("registration-email"), email);
    }

    @Test
    public void negativeLogInEmptyEmail() throws Exception {
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getEmailAlert()), logInPage1.getEmailAlertText());
    }

}
