package com.empik.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtil {

    public static void hoverAndClick(WebElement hoverElement, WebElement clickElement) {
        Actions builder = new Actions(DriverManager.getDriver());
        builder.moveToElement(hoverElement).perform();
        Waiters.wait(500);
        builder.moveToElement(clickElement).click().perform();
    }

    public static void hover(WebElement hoverElement) {
        Actions builder = new Actions(DriverManager.getDriver());
        builder.moveToElement(hoverElement).perform();
        Waiters.wait(1000);
    }

    public static void scroll(WebElement scrollElement) {
        Actions builder = new Actions(DriverManager.getDriver());
        builder.scrollToElement(scrollElement).perform();
    }
}
