package com.framework.base;

import com.framework.pages.HomePage;
import com.framework.pages.LoginPage;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeSuite
    public void setupSuite() {
        driver = DriverManager.getDriver();
        String url = ConfigReader.getProperty("base.url");
        driver.get(url);
        System.out.println("Navigated to: " + url);

        initPages();
        createAllureEnvironmentFile();
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("Logging out...");
        DriverManager.quitDriver();
    }

    private void initPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    private void createAllureEnvironmentFile() {
        try {
            Properties props = new Properties();
            props.setProperty("Test Environment", System.getProperty("env", "dev"));
            props.setProperty("Base URL", ConfigReader.getProperty("base.url"));
            props.setProperty("Browser", ConfigReader.getProperty("browser"));
            props.setProperty("Explicit Wait: ", ConfigReader.getProperty("explicit.wait"));

            File file = new File("target/allure-results/environment.properties");
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                props.store(writer, "Allure Environment Properties");
            }
            System.out.println("✅ Allure environment.properties created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
