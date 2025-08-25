package com.framework.utils;

import com.framework.base.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔹 Starting Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== TEST SUITE STARTED ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== TEST SUITE FINISHED ===");
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) return new byte[0];
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
