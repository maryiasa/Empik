package com.empik.pages;

import com.empik.utils.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Page {

    @FindBy(className = "css-1gl8alp-name")
    private WebElement itemTitle;

    public CartPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebElement getItemTitle() {
        return itemTitle;
    }
}
