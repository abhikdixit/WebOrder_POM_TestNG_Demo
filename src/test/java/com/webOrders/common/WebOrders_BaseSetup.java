//package com.webOrders.common;
//
//        import java.io.File;
//        import java.io.IOException;
//        import java.text.SimpleDateFormat;
//        import java.time.Duration;
//        import java.util.Date;
//
//        import org.apache.commons.io.FileUtils;
//        import org.openqa.selenium.*;
//        import org.openqa.selenium.chrome.ChromeDriver;
//        import org.openqa.selenium.edge.EdgeDriver;
//        import org.openqa.selenium.firefox.FirefoxDriver;
//        import org.testng.annotations.*;
//
//        import com.aventstack.extentreports.*;
//        import com.aventstack.extentreports.reporter.*;
//        import com.aventstack.extentreports.reporter.configuration.Theme;
//
//        import io.github.bonigarcia.wdm.WebDriverManager;
//
//        public class WebOrders_BaseSetup {
//            // Shared WebDriver instance for all tests
//            protected static WebDriver driver;
//
//            // ExtentReports instance for generating test reports
//            private static ExtentReports extent;
//
//            // ThreadLocal to manage ExtentTest instances for parallel execution
//            private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//            // Path to store screenshots
//            private String screenshotPath = System.getProperty("user.dir") + "\\SCREENSHOTS";
//
//            // Getter for WebDriver instance
//            public static WebDriver getDriver() {
//                return driver;
//            }
//
//            // Getter for ExtentReports instance
//            public static ExtentReports getExtent() {
//                return extent;
//            }
//
//            // Getter for the current ExtentTest instance
//            public static ExtentTest getTest() {
//                return test.get();
//            }
//
//            // Setter for ExtentReports instance
//            public static void setExtent(ExtentReports extentReport) {
//                extent = extentReport;
//            }
//
//            // Setter for the current ExtentTest instance
//            public static void setTest(ExtentTest extentTest) {
//                test.set(extentTest);
//            }
//
//            // Removes the current ExtentTest instance from ThreadLocal
//            public static void removeTest() {
//                test.remove();
//            }
//
//            // Initializes the Extent Report before tests
//            @BeforeTest
//            @Parameters({"browserType"})
//            public void setUpReport(String browserType) {
//                // Generate a timestamp for unique report file names
//                String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
//                String reportPath = System.getProperty("user.dir") + "\\test-output\\WebOrders_" + timestamp + ".html";
//
//                // Configure ExtentSparkReporter for report generation
//                ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
//                ExtentReports extentReports = new ExtentReports();
//                extentReports.attachReporter(reporter);
//
//                // Add system information to the report
//                extentReports.setSystemInfo("OS", System.getProperty("os.name"));
//                extentReports.setSystemInfo("Browser", browserType);
//                extentReports.setSystemInfo("Tester", "Abhi");
//
//                // Configure the appearance of the report
//                reporter.config().setDocumentTitle("WebOrders Automation Report");
//                reporter.config().setReportName("WebOrders Test Execution");
//                reporter.config().setTheme(Theme.DARK);
//
//                // Save the ExtentReports instance
//                setExtent(extentReports);
//
//                System.out.println("Extent Report Created : " + reportPath);
//            }
//
//            // Initializes the browser before each test class
//            @BeforeClass
//            @Parameters({"browserType", "appURL"})
//            public void initializeBrowser(String browserType, String appURL) {
//                // Select the browser type based on the parameter
//                switch (browserType.toLowerCase()) {
//                    case "chrome":
//                        //WebDriverManager.chromedriver().setup(); // Setup ChromeDriver
//                        driver = new ChromeDriver(); // Initialize Chrome browser
//                        break;
//                    case "edge":
//                        //WebDriverManager.edgedriver().setup(); // Setup EdgeDriver
//                        driver = new EdgeDriver(); // Initialize Edge browser
//                        break;
//                    case "firefox":
//                        //WebDriverManager.firefoxdriver().setup(); // Setup FirefoxDriver
//                        driver = new FirefoxDriver(); // Initialize Firefox browser
//                        break;
//                    default:
//                        //throw new RuntimeException("Invalid Browser : " + browserType); // Handle invalid browser input
//                        driver = new EdgeDriver(); // Initialize Edge browser
//                }
//
//                // Maximize the browser window
//                driver.manage().window().maximize();
//
//                // Set implicit wait timeout for locating elements
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//                // Navigate to the application URL
//                driver.get(appURL);
//            }
//
//            // Captures a screenshot and saves it to the specified path
//            public String captureScreenshot(String testName) throws IOException {
//                // Create the screenshot folder if it doesn't exist
//                File folder = new File(screenshotPath);
//                if (!folder.exists()) {
//                    folder.mkdirs();
//                }
//
//                // Take a screenshot and save it to the destination path
//                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                File destination = new File(screenshotPath + "\\" + testName + "_" + System.currentTimeMillis() + ".png");
//                FileUtils.copyFile(source, destination);
//
//                // Return the absolute path of the screenshot
//                return destination.getAbsolutePath();
//            }
//
//            // Closes the browser after all tests in the class
//            @AfterClass
//            public void tearDown() {
//                if (driver != null) {
//                    driver.quit(); // Quit the browser
//                }
//            }
//
//            // Flushes the Extent Report after the test suite
//            @AfterSuite
//            public void flushReport() {
//                if (getExtent() != null) {
//                    getExtent().flush(); // Write the report to the file
//                    System.out.println("Extent Report Generated");
//                }
//            }
//        }

package com.webOrders.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class WebOrders_BaseSetup {

    protected static WebDriver driver;

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private String screenshotPath = System.getProperty("user.dir") + File.separator + "SCREENSHOTS";

    public static WebDriver getDriver() {
        return driver;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setExtent(ExtentReports extentReport) {
        extent = extentReport;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void removeTest() {
        test.remove();
    }

    @BeforeTest
    public void setUpReport() {

        String browser = ConfigReader.getProperty("browser");

        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

        String reportPath = System.getProperty("user.dir")
                + File.separator
                + "test-output"
                + File.separator
                + "WebOrders_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        ExtentReports extentReports = new ExtentReports();

        extentReports.attachReporter(reporter);

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Browser", browser);
        extentReports.setSystemInfo("Tester", "Abhi");

        reporter.config().setDocumentTitle("WebOrders Automation Report");
        reporter.config().setReportName("WebOrders Test Execution");
        reporter.config().setTheme(Theme.DARK);

        setExtent(extentReports);

        System.out.println("Extent Report Created : " + reportPath);
    }

    @BeforeClass
    public void initializeBrowser() {

        String browser = ConfigReader.getProperty("browser");
        String appURL = ConfigReader.getProperty("appURL");

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));

        int implicitWait = Integer.parseInt(
                ConfigReader.getProperty("implicitWait"));

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":

                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            case "firefox":

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("Invalid Browser : " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(implicitWait));

        driver.get(appURL);

        System.out.println("---------------------------------------");
        System.out.println("Browser   : " + browser);
        System.out.println("Headless  : " + headless);
        System.out.println("Application URL : " + appURL);
        System.out.println("---------------------------------------");
    }

    public String captureScreenshot(String testName) throws IOException {

        File folder = new File(screenshotPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destination = new File(
                screenshotPath
                        + File.separator
                        + testName
                        + "_"
                        + System.currentTimeMillis()
                        + ".png");

        FileUtils.copyFile(source, destination);

        return destination.getAbsolutePath();
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @AfterSuite
    public void flushReport() {

        if (getExtent() != null) {
            getExtent().flush();
            System.out.println("Extent Report Generated");
        }
    }
}