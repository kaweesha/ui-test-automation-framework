package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import com.framework.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return ExcelReader.readSheet("TestData.xlsx", "Login");
    }

    @Test(dataProvider = "loginDataProvider")
    public void testValidLogin(String username, String password, String welcomeText) {
        loginPage.login(username, password);

        homePage.verifyWelcomeText(welcomeText);
        System.out.println("Login test executed with user: " + username);
    }
}
