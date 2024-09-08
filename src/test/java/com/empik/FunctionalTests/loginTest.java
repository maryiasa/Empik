package com.empik.FunctionalTests;

import com.empik.CommonTest;
import com.empik.pages.HomePage.HomePageHeader;
import com.empik.pages.LogInPage;
import com.empik.pages.RegistrationPage;
import com.empik.utils.Waiters;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class loginTest extends CommonTest {

    @BeforeMethod
    public void setUpLoginTest() {
        HomePageHeader homePageHeader = new HomePageHeader();
        homePageHeader.open();
        homePageHeader.clickCookies();
        String logInBtnText = homePageHeader.getLogInBtn();
        Assert.assertEquals(logInBtnText, "Witaj\n" +
                "zaloguj się");
        homePageHeader.clickLogInBtn();
    }

    @Test
    public void negativeLogInUserNotRegistered() throws Exception {
        LogInPage logInPage = new LogInPage();
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        logInPage.sendUserEmailKeys(email);
        Waiters.wait(3000);
        RegistrationPage registrationPage = (RegistrationPage) logInPage.clickNextBtn();
        Assert.assertEquals(registrationPage.getPageName(), "Rejestracja");
        Assert.assertEquals(registrationPage.getUserRegistrationEmail(), email);
    }

    @Test
    public void negativeLogInUserRegistered() throws Exception {
        LogInPage logInPage = new LogInPage();
        String email = "alexey.saganovich.pl@gmail.com";
        logInPage.sendUserEmailKeys(email);
        Waiters.wait(5000);
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getPageName(), "Witaj ponownie, zaloguj się");
        Assert.assertEquals(logInPage1.getEmail(), "E-mail: " + email);
    }

    @Test
    public void negativeLogInEmptyEmail() throws Exception {
        LogInPage logInPage = new LogInPage();
        LogInPage logInPage1 = (LogInPage) logInPage.clickNextBtn();
        Assert.assertEquals(logInPage1.getEmailAlert(), "Pole wymagane");
    }

}
