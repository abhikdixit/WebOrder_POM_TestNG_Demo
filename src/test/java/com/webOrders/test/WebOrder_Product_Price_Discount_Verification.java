package com.webOrders.test;

    import org.openqa.selenium.WebDriver;
    import org.testng.Assert;
    import org.testng.annotations.BeforeClass;
    import org.testng.annotations.Test;

    import com.webOrders.common.WebOrders_BasePage;
    import com.webOrders.common.WebOrders_BaseSetup;
    import com.webOrders.home.WebOrders_HomePage;
    import com.webOrders.home.WebOrders_OrdersPage;
    import com.webOrders.home.WebOrders_SignInPage;
    import com.webOrders.home.WebOrders_ViewAllProductsPage;
    import com.webOrders.utils.WebOrders_TestData;

    public class WebOrder_Product_Price_Discount_Verification extends WebOrders_BaseSetup {

        // Page Objects
        WebOrders_SignInPage signIn;
        WebOrders_HomePage homePage;
        WebOrders_BasePage basePage;
        WebOrders_OrdersPage orderPage;

        private WebDriver driver;

        // Setup method to initialize the driver and login before tests
        @BeforeClass
        public void setUp() throws Exception {
            driver = getDriver(); // Get WebDriver instance
            basePage = new WebOrders_BasePage(driver); // Initialize base page

            // Login to the application
            signIn = basePage.GoToHomePageAndSignIn("Tester", "test");
            homePage = new WebOrders_HomePage(driver); // Initialize home page
        }

        // Test to verify product price in the Order page
        @Test(
            description = "Verify Product price in Order Page",
            dataProvider = "ProductPrice",
            dataProviderClass = WebOrders_TestData.class
        )
        public void verifyProductPrice(String product, String expectedPrice) throws Exception {
            // Step 1: Navigate to "View All Products" page
            WebOrders_ViewAllProductsPage productPage = homePage.clickOnAllProductsTab();
            String actualPrice = productPage.getProductPrice(product); // Get product price

            // Validate price in the "View All Products" page
            Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch in Products page");

            // Step 2: Navigate to "Order" page
            orderPage = homePage.clickOnOrdersTab();
            orderPage.selectProduct(product); // Select product
            orderPage.enterQuantity("1"); // Enter quantity
            orderPage.clickCalculate(); // Calculate total price

            // Step 3: Verify price in the "Order" page
            String actualOrderPrice = orderPage.getUnitPrice();
            Assert.assertEquals(actualOrderPrice, expectedPrice.replace("$", ""), "Product price mismatch in Order page");
        }
    }