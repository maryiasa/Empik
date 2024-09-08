package com.empik.FunctionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.Header;
import com.empik.pages.LogInPage;
import com.empik.pages.RegistrationPage;
import com.empik.utils.ConfigurationReader;
import com.empik.utils.Waiters;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class LoginTest extends CommonTest {

    @Parameters
    public String setUpTestValues(String key) {
        Map<String, String> testValues = new HashMap<>();
        testValues.put("email", ConfigurationReader.getTestValue(TestValues.EMAIL.EMAIL));
        return testValues.get(key).toString().replaceAll("\"", "");
    }

    @BeforeMethod
    public void setUpLoginTest() {
        Header homePageHeader = new Header();
        String logInBtnText = homePageHeader.getElementText(homePageHeader.getLogInBtn());
        Assert.assertEquals(logInBtnText, "Witaj\n" +
                "zaloguj się");
        homePageHeader.clickBtnAndReturnLogInPage(homePageHeader.getLogInBtn());
    }

    @Test
    public void negativeLogInUserNotRegistered() throws Exception {
        LogInPage logInPage = new LogInPage();
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        logInPage.sendUserEmailKeys(logInPage.getUserEmailInput(), email);
        Waiters.wait(3000);
        RegistrationPage registrationPage = (RegistrationPage) logInPage.clickNextBtn();
        Assert.assertEquals(registrationPage.getElementText(registrationPage.getUserRegistrationFormName()), "Rejestracja");
        Assert.assertEquals(registrationPage.getUserRegistrationEmail(), email);
    }

    @Test
    public void negativeLogInUserRegistered() throws Exception {
        String email = setUpTestValues("email");
        LogInPage logInPage = new LogInPage();
        logInPage.sendUserEmailKeys(logInPage.getUserEmailInput(), email);
        Waiters.wait(5000);
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getPageName()), "Witaj ponownie, zaloguj się");
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getEmail()), "E-mail: " + email);
    }

    @Test
    public void negativeLogInEmptyEmail() throws Exception {
        LogInPage logInPage = new LogInPage();
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getElementText(logInPage1.getEmailAlert()), "Pole wymagane");
    }

}
