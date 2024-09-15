package com.empik.functionalTests;

import com.empik.CommonTest;
import com.empik.enums.TestValues;
import com.empik.pages.Header;
import com.empik.pages.RegistrationPage;
import com.empik.utils.ActionsUtil;
import com.empik.utils.ConfigurationReader;
import com.empik.utils.Waiters;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends CommonTest {

    String testEmail;
    String testPwd;
    String testPhone;
    RegistrationPage registrationPage;

    @BeforeMethod
    public void setUpRegistrationTest() {
        testEmail = ConfigurationReader.getTestValue(TestValues.EMAIL.EMAIL);
        testPwd = ConfigurationReader.getTestValue(TestValues.PWD.PWD);
        testPhone = ConfigurationReader.getTestValue(TestValues.PHONE.PHONE);

        Header homePageHeader = new Header();
        Waiters.wait(1000);
        ActionsUtil.hover(homePageHeader.getLogInBtn());

        Assert.assertEquals(homePageHeader.getRegisterBtn().getText(), homePageHeader.getRegisterBtnText());
        registrationPage = homePageHeader.clickBtnAndReturnRegistrationPage(homePageHeader.getRegisterBtn());
    }

    @Test
    public void negativeRegistrationNewUser() throws Exception {
        Assert.assertTrue(registrationPage.getPageUrl().contains(getRegistrationPageUrl()));
        Assert.assertTrue(registrationPage.getUserRegistrationFormName().getText().equals(registrationPage.getPageName()));

        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String pwd = faker.internet().password(8, 14, true, false, true);

        registrationPage.sendTestKeys(registrationPage.getUserRegistrationEmailInput(), email);
        registrationPage.sendTestKeys(registrationPage.getUserRegistrationPwdInput(), pwd);
        registrationPage.sendTestKeys(registrationPage.getUserRegistrationPhoneInput(), testPhone);

        registrationPage.getRegulationsAndPrivacyPolicyCheckbox().click();
        registrationPage.clickRecaptchaCheckbox();
        //registrationPage.getSignUpBtn().click();

    }

    @Test
    public void negativeRegistrationWithExistUserData() throws Exception {
        Assert.assertTrue(registrationPage.getPageUrl().contains(getRegistrationPageUrl()));
        Assert.assertTrue(registrationPage.getUserRegistrationFormName().getText().equals(registrationPage.getPageName()));

        registrationPage.sendTestKeys(registrationPage.getUserRegistrationEmailInput(), testEmail);
        registrationPage.sendTestKeys(registrationPage.getUserRegistrationPwdInput(), testPwd);
        registrationPage.sendTestKeys(registrationPage.getUserRegistrationPhoneInput(), testPhone);

        registrationPage.getRegulationsAndPrivacyPolicyCheckbox().click();
        registrationPage.clickRecaptchaCheckbox();
        registrationPage.getSignUpBtn().click();

        Assert.assertTrue(registrationPage.getErrorInfoBox().isDisplayed());
        Assert.assertTrue(registrationPage.getElementText(registrationPage.getErrorInfoBox()).equals(registrationPage.getRegistrationAlert()));

    }
}
