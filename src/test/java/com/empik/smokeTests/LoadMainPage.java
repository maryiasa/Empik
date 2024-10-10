package com.empik.smokeTests;

import com.empik.CommonTest;
import com.empik.Constants;
import com.empik.pages.HomePage;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class LoadMainPage extends CommonTest {

    private static final Logger log = LogManager.getLogger(LoadMainPage.class);

    @Test
    public void loadMainPage () {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getPageUrl(), Constants.HOME_URL);
    }
}
