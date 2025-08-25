package com.framework.pages;

import com.framework.base.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;

public class HomePage extends CommonActions {
    private final By welcomeText = By.xpath("//div[@id='leftPanel']/p");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyWelcomeText(String text) {
        Assert.assertEquals(getText(welcomeText),text);
    }

}
