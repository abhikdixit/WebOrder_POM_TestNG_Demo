package com.webOrders.test;

	import org.openqa.selenium.WebDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.webOrders.common.WebOrders_BasePage;
	import com.webOrders.common.WebOrders_BaseSetup;
	import com.webOrders.home.WebOrders_HomePage;
	import com.webOrders.home.WebOrders_SignInPage;
	import com.webOrders.home.WebOrders_ViewAllOrdersPage;

	public class WebOrder_ViewAllOrders_Test extends WebOrders_BaseSetup {

	    private WebDriver driver;
	    private WebOrders_BasePage basePage;
	    private WebOrders_ViewAllOrdersPage viewAllOrderPage;

	    // Test data
	    private final String name = "Charles Dodgeson";
	    private final String product = "MyMoney";

	    // Setup method to initialize WebDriver and login before tests
	    @BeforeClass
	    public void setup() throws Exception {
	        driver = getDriver(); // Get WebDriver instance
	        basePage = new WebOrders_BasePage(driver); // Initialize base page

	        // Login to the application
	        WebOrders_SignInPage signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
	        signIn.verifySignInPageText(); // Verify login page text

	        // Navigate to "View All Orders" page
	        WebOrders_HomePage homePage = new WebOrders_HomePage(driver);
	        viewAllOrderPage = homePage.clickOnAllOrdersTab();
	    }

	    // Test to verify "Check All" functionality
	    @Test(priority = 1, description = "Verify Check All")
	    public void verifyCheckAll() throws Exception {
	        viewAllOrderPage.clickCheckAll(); // Click "Check All" button

	        // Assert that all orders are selected
	        Assert.assertTrue(viewAllOrderPage.verifyAllOrdersSelected(), "All records are not selected.");
	    }

	    // Test to verify "UnCheck All" functionality
	    @Test(priority = 2, description = "Verify UnCheck All")
	    public void verifyUnCheckAll() throws Exception {
	        viewAllOrderPage.clickUncheckAll(); // Click "UnCheck All" button

	        // Assert that all orders are unselected
	        Assert.assertTrue(viewAllOrderPage.verifyAllOrdersUnselected(), "Records are still selected.");
	    }

	    // Test to verify the ability to delete an order
	    @Test(priority = 3, description = "Verify Delete Order")
	    public void verifyDeleteOrder() throws Exception {
	        viewAllOrderPage.deleteOrder(name, product); // Delete the specified order

	        // Assert that the order is deleted
	        Assert.assertTrue(viewAllOrderPage.verifyDeletedRecord(name), "Record still exists.");
	    }

	    // Cleanup method to logout after tests
	    @AfterClass
	    public void logout() throws Exception {
	        if (basePage != null) {
	            basePage.logout(); // Logout from the application
	        }
	    }
	}