package com.webOrders.home;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class WebOrders_HomePage {

	    // WebDriver instance to interact with the browser
	    WebDriver driver;

	    // Constructor to initialize WebDriver and PageFactory elements
	    public WebOrders_HomePage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
	    }

	    // WebElement for the "View all orders" tab
	    @FindBy(linkText = "View all orders")
	    WebElement allOrdersTab;

	    // WebElement for the "View all products" tab
	    @FindBy(linkText = "View all products")
	    WebElement allProductsTab;

	    // WebElement for the "Order" tab
	    @FindBy(linkText = "Order")
	    WebElement orderTab;

	    /**
	     * Method to click on the "View all orders" tab.
	     * Navigates to the WebOrders_ViewAllOrdersPage.
	     *
	     * @return WebOrders_ViewAllOrdersPage instance
	     */
	    public WebOrders_ViewAllOrdersPage clickOnAllOrdersTab() {
	        allOrdersTab.click(); // Click on the "View all orders" tab
	        return new WebOrders_ViewAllOrdersPage(driver); // Return the next page object
	    }

	    /**
	     * Method to click on the "View all products" tab.
	     * Navigates to the WebOrders_ViewAllProductsPage.
	     *
	     * @return WebOrders_ViewAllProductsPage instance
	     */
	    public WebOrders_ViewAllProductsPage clickOnAllProductsTab() {
	        allProductsTab.click(); // Click on the "View all products" tab
	        return new WebOrders_ViewAllProductsPage(driver); // Return the next page object
	    }

	    /**
	     * Method to click on the "Order" tab.
	     * Navigates to the WebOrders_OrdersPage.
	     *
	     * @return WebOrders_OrdersPage instance
	     */
	    public WebOrders_OrdersPage clickOnOrdersTab() {
	        orderTab.click(); // Click on the "Order" tab
	        return new WebOrders_OrdersPage(driver); // Return the next page object
	    }
	}