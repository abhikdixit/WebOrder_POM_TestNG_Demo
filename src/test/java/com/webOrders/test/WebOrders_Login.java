package com.webOrders.test;

		import org.openqa.selenium.WebDriver;
		import org.testng.annotations.BeforeClass;
		import org.testng.annotations.Test;

		import com.webOrders.common.WebOrders_BasePage;
		import com.webOrders.common.WebOrders_BaseSetup;
		import com.webOrders.home.WebOrders_HomePage;
		import com.webOrders.home.WebOrders_SignInPage;

		public class WebOrders_Login extends WebOrders_BaseSetup {
		    // Page Objects
		    WebOrders_SignInPage signIn;
		    WebOrders_HomePage homePage;
		    WebOrders_BasePage basePage;
		    // WebDriver instance
		    WebDriver driver;

		    // Setup method to initialize WebDriver and base page before tests
		    @BeforeClass
		    public void setUp() {
		        driver = getDriver(); // Get WebDriver instance
		        basePage = new WebOrders_BasePage(driver); // Initialize base page
		    }

		    // Test to validate login functionality and verify homepage text
		    @Test(description = "Login Validation")
		    public void loginTest() throws Exception {
		        // Perform login
		        signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		        // Verify homepage text after login
		        signIn.verifySignInPageText();
		        // Logout from the application
		        basePage.logout();
		    }

		    // Test to validate URL after login
		    @Test(description = "URL Validation")
		    public void urlTest() throws Exception {
		        // Perform login
		        signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
		        // Verify URL after login
		        signIn.verifySignInPageURL();
		        // Logout from the application
		        basePage.logout();
		    }
		}