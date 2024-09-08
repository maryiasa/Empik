package com.empik.pages.HomePage;

import com.empik.pages.LogInPage;
import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageHeader extends HomePage {

    @FindBy(css = ".empikNav__userText.ta-login-link")
    private WebElement logInBtn;

    public HomePageHeader() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public LogInPage clickLogInBtn() {
        logInBtn.click();
        return new LogInPage();
    }

    public String getLogInBtn() {
        return logInBtn.getText();
    }

}
