package com.empik.listeners;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Log4j2
public class SeleniumListener implements WebDriverListener {

    private static final Logger log = LogManager.getLogger(SeleniumListener.class);

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.beforeSendKeys(element, keysToSend);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
    }

    @Override
    public void afterClick(WebElement element) {
        Allure.step(element.toString().split("->")[1] + " is clicked");
        log.info(element.toString().split("->")[1] + " is clicked");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        WebDriverListener.super.afterQuit(driver);
    }
}
