package com.webOrders.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.webOrders.common.WebOrders_BaseSetup;

public class Listeners implements ITestListener {

    /**
     * Triggered when a test starts.
     * Creates a new ExtentTest instance for the test.
     *
     * @param result Test result object containing test details.
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = WebOrders_BaseSetup.getExtent().createTest(result.getMethod().getMethodName());
        WebOrders_BaseSetup.setTest(extentTest);
        System.out.println("Test Started: " + result.getName());
    }

    /**
     * Triggered when a test passes.
     * Logs the success status and captures a screenshot.
     *
     * @param result Test result object containing test details.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = WebOrders_BaseSetup.getTest();
        if (test != null) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
            captureScreenshot(result.getName());
        }
    }

    /**
     * Triggered when a test fails.
     * Logs the failure status, captures a screenshot, and logs the exception.
     *
     * @param result Test result object containing test details.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = WebOrders_BaseSetup.getTest();
        if (test != null) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED", ExtentColor.RED));
            test.fail(result.getThrowable());
            captureScreenshot(result.getName());
        }
    }

    /**
     * Triggered when a test is skipped.
     * Logs the skipped status and the reason for skipping.
     *
     * @param result Test result object containing test details.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = WebOrders_BaseSetup.getTest();
        if (test != null) {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.YELLOW));
            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            }
        }
    }

    /**
     * Captures a screenshot for the given test name.
     * Adds the screenshot to the ExtentTest instance.
     *
     * @param testName Name of the test for which the screenshot is captured.
     */
    private void captureScreenshot(String testName) {
        try {
            WebOrders_BaseSetup baseSetup = new WebOrders_BaseSetup();
            String screenshotPath = baseSetup.captureScreenshot(testName);
            WebOrders_BaseSetup.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            WebOrders_BaseSetup.getTest().warning("Screenshot capture failed: " + e.getMessage());
        }
    }

    /**
     * Triggered when all tests in the suite finish.
     * Flushes the ExtentReports instance to generate the report.
     *
     * @param context Test context object containing suite details.
     */
    @Override
    public void onFinish(ITestContext context) {
        if (WebOrders_BaseSetup.getExtent() != null) {
            WebOrders_BaseSetup.getExtent().flush();
            System.out.println("Extent Report Generated Successfully");
        }
    }
}