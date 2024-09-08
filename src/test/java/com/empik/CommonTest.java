package com.empik;

import com.empik.listeners.TestNGListener;
import com.empik.pages.HomePage.HomePage;
import com.empik.utils.DriverManager;
import com.empik.utils.ScreenshotUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class CommonTest {

    @BeforeMethod
    public void setUp() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickCookies();
    }

    @AfterMethod
    public void closeSession() {
        ScreenshotUtil.makeScreenshot();
        DriverManager.closeSession();
    }
}

