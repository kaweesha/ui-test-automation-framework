package com.framework.pages;

import com.framework.base.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.TestNG;

public class HomePage extends CommonActions {

    @FindBy(xpath = "//div[@id='leftPanel']/p")
    private WebElement welcomeText;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyWelcomeText(String text) {
        Assert.assertEquals(getText(welcomeText),text);
    }

}
