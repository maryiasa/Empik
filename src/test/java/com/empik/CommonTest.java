package com.empik;

import com.empik.listeners.TestNGListener;
import com.empik.utils.DriverManager;
import com.empik.utils.ScreenshotUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

@Listeners(TestNGListener.class)
public class CommonTest {

    @AfterMethod
    public void closeSession() {
        ScreenshotUtil.makeScreenshot();
        DriverManager.closeSession();
    }
}

