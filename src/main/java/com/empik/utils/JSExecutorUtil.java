package com.empik.utils;

import org.openqa.selenium.JavascriptExecutor;

public class JSExecutorUtil {

    public static String getTextByID(String name) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        return (String) executor.executeScript("return document.getElementById(\"" + name + "\").value");
    }

}