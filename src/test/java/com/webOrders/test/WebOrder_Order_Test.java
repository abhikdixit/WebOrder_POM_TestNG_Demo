package com.webOrders.test;

		import org.testng.annotations.AfterMethod;
		import org.testng.annotations.BeforeClass;
		import org.testng.annotations.Test;

		import com.webOrders.common.WebOrders_BasePage;
		import com.webOrders.common.WebOrders_BaseSetup;
		import com.webOrders.home.WebOrders_HomePage;
		import com.webOrders.home.WebOrders_OrdersPage;
		import com.webOrders.home.WebOrders_SignInPage;
		import com.webOrders.home.WebOrders_ViewAllOrdersPage;
		import com.webOrders.utils.WebOrders_TestData;

		public class WebOrder_Order_Test extends WebOrders_BaseSetup {

		    // Page Objects
		    WebOrders_SignInPage signIn;
		    WebOrders_HomePage homePage;
		    WebOrders_BasePage basePage;
		    WebOrders_OrdersPage orderPage;
		    WebOrders_ViewAllOrdersPage allOrderPage;

		    /*
		     * Initialize Page Objects
		     */
		    @BeforeClass(alwaysRun = true)
		    public void setUp() {
		        // Initialize the base page with the driver
		        basePage = new WebOrders_BasePage(getDriver());
		    }

		    /*
		     * Create Order Test
		     */
		    @Test(
		        description = "Create and verify new Orders",
		        dataProvider = "OrdersExcelData",
		        dataProviderClass = WebOrders_TestData.class,
		        priority = 1,
		        enabled = true
		    )
		    public void createAndVerifyOrder(
		        String product,
		        String qty,
		        String custName,
		        String street,
		        String city,
		        String state,
		        String zip,
		        String card,
		        String cardNr,
		        String expiry,
		        String expectedResult
		    ) throws Exception {

		        // Login and navigate to Orders page
		        login();

		        // Create Order
		        orderPage.createOrder(
		            product,
		            qty,
		            custName,
		            street,
		            city,
		            state,
		            zip,
		            card,
		            cardNr,
		            expiry,
		            expectedResult
		        );

		        /*
		         * Verify order in View All Orders page
		         */
		        if (expectedResult.equalsIgnoreCase("success")) {
		            // Navigate to "View All Orders" page
		            allOrderPage = homePage.clickOnAllOrdersTab();

		            // Verify the created order details
		            allOrderPage.verifyUpdatedOrder(
		                product,
		                qty,
		                custName,
		                street,
		                city,
		                state,
		                zip,
		                card,
		                cardNr,
		                expiry
		            );
		        }
		    }

		    /*
		     * Validate Reset Form
		     */
		    @Test(
		        description = "Validate reset button",
		        dataProvider = "Reset Form",
		        dataProviderClass = WebOrders_TestData.class,
		        priority = 2,
		        enabled = false
		    )
		    public void verifyResetForm(
		        String product,
		        String qty,
		        String custName,
		        String street,
		        String city,
		        String state,
		        String zip,
		        String card,
		        String cardNr,
		        String expiry,
		        String expectedResult
		    ) throws Exception {

		        // Login and navigate to Orders page
		        login();

		        // Reset the form and validate that all fields are cleared
		        orderPage.resetForm(
		            product,
		            qty,
		            custName,
		            street,
		            city,
		            state,
		            zip,
		            card,
		            cardNr,
		            expiry
		        );
		    }

		    /*
		     * Login helper method
		     */
		    private void login() {
		        // Navigate to the home page and sign in
		        signIn = basePage.GoToHomePageAndSignIn("Tester", "test");

		        // Verify the sign-in page text
		        signIn.verifySignInPageText();

		        // Initialize the home page and navigate to the Orders tab
		        homePage = new WebOrders_HomePage(getDriver());
		        orderPage = homePage.clickOnOrdersTab();
		    }

		    /*
		     * Logout after every test
		     */
		    @AfterMethod(alwaysRun = true)
		    public void tearDownTest() {
		        try {
		            // Logout from the application
		            if (basePage != null) {
		                basePage.logout();
		            }
		        } catch (Exception e) {
		            // Handle logout failure
		            System.out.println("Logout failed : " + e.getMessage());
		        }
		    }
		}