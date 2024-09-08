package com.empik.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JSExecutorUtil {
    public static String getTextByID(WebDriver driver, String name) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        return (String) executor.executeScript("return document.getElementById(\"" + name + "\").value");
    }
}