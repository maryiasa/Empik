package com.empik;

import com.empik.listeners.TestNGListener;
import com.empik.pages.HomePage;
import com.empik.pages.Page;
import com.empik.utils.DriverManager;
import com.empik.utils.ScreenshotUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class CommonTest extends Page {

    @BeforeMethod
    public void setUpCommonTest() {
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickBtn(homePage.getAllowCookiesBtn());
    }

    @AfterMethod
    public void closeSession() {
        ScreenshotUtil.makeScreenshot();
        DriverManager.closeSession();
    }
}

