package com.empik.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends Page {

    @FindBy(className = "css-1gl8alp-name")
    private WebElement itemTitle;

    public WebElement getItemTitle() {
        return itemTitle;
    }
}
