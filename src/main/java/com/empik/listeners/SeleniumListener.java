package com.empik.listeners;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Arrays;

@Log4j2
public class SeleniumListener implements WebDriverListener {

    private static final Logger log = LogManager.getLogger(SeleniumListener.class);

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.beforeSendKeys(element, keysToSend);
        log.info("beforeSendKeys: " + Arrays.toString(keysToSend));
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        log.info("afterSendKeys: " + Arrays.toString(keysToSend));
    }

   @Override
    public void beforeClick(WebElement element) {
       WebDriverListener.super.beforeClick(element);
       Allure.step("beforeClick: " + element.getText());
       log.info("beforeClick: " + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        Allure.step("afterClick: " + element.toString().split("->")[1] + " is clicked");
        log.info("afterClick: "  + element.toString().split("->")[1] + " is clicked");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        WebDriverListener.super.afterQuit(driver);
        log.info("afterQuit: driver is quit. Session is closed");
    }
}
