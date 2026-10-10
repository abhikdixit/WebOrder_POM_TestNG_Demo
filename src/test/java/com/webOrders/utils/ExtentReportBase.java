package com.webOrders.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportBase {

    // Singleton instance of ExtentReports
    private static ExtentReports extent;

    /**
     * Creates and configures an ExtentReports instance.
     *
     * @param reportName Name of the report file.
     * @param browser    Browser name for the test execution.
     * @return Configured ExtentReports instance.
     */
    public static ExtentReports createExtentReport(String reportName, String browser) {
        if (extent == null) {
            // Define the report file path
            String path = System.getProperty("user.dir") + "/ExtentReport/" + reportName;

            // Initialize ExtentSparkReporter with the report path
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);

            // Initialize ExtentReports and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Set system information for the report
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", browser);
            extent.setSystemInfo("Tester", "Abhi");

            // Configure the report appearance
            reporter.config().setDocumentTitle("WebOrders Automation Report");
            reporter.config().setReportName("WebOrders Regression Execution");
            reporter.config().setTheme(Theme.DARK);
        }
        return extent;
    }

    /**
     * Captures a screenshot and saves it to the specified folder.
     *
     * @param driver        WebDriver instance.
     * @param screenshotName Name of the screenshot file.
     * @return Absolute path of the saved screenshot.
     * @throws Exception If an error occurs during screenshot capture.
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) throws Exception {
        // Generate a timestamp for the screenshot file
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Capture the screenshot as a file
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the folder path for saving screenshots
        String folder = System.getProperty("user.dir") + "/Screenshot_fail/";

        // Create the folder if it does not exist
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Define the destination file path
        String destination = folder + screenshotName + "_" + time + ".png";
        File finalFile = new File(destination);

        // Copy the screenshot file to the destination
        FileUtils.copyFile(source, finalFile);

        return destination;
    }
}