package com.empik.utils;

import com.empik.enums.PropertiesValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver createDriver(String browserName) {
        if (browserName.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            URL url = null;
            try {
                url = new URL(ConfigurationReader.getProperty(PropertiesValues.SELENIUM_URL));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return new RemoteWebDriver(url, chromeOptions);

        } else if (browserName.equals("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--start-maximized");
            URL url = null;
            try {
                url = new URL("http://localhost:4444");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return new RemoteWebDriver(url, firefoxOptions);
        } else {
            return null;
        }
    }

}
