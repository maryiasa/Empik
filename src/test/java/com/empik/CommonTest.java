package com.empik;

import com.empik.listeners.TestNGListener;
import com.empik.pages.HomePage;
import com.empik.utils.DriverManager;
import com.empik.utils.ScreenshotUtil;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Log4j2
@Listeners(TestNGListener.class)
public class CommonTest {

    @BeforeMethod
    public void setUpCommonTest() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.getAllowCookiesBtn().click();
    }

    @AfterMethod
    public void closeSession() {
        ScreenshotUtil.makeScreenshot();
        DriverManager.closeSession();
    }
}

