package com.framework.pages;

import com.framework.base.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonActions {

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }
}
