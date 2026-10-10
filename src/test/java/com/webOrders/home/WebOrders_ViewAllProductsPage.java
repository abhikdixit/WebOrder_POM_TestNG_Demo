package com.webOrders.home;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrders_ViewAllProductsPage {

    // WebDriver instance to interact with the browser
    WebDriver driver;

    // WebDriverWait instance for explicit waits
    WebDriverWait wait;

    // Constructor to initialize WebDriver, WebDriverWait, and PageFactory elements
    public WebOrders_ViewAllProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait timeout set to 10 seconds
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    // ============================
    // Web Elements
    // ============================

    // Table containing all products
    @FindBy(xpath = "//table[@class='ProductsTable']")
    WebElement tblProducts;

    // List of rows in the products table
    @FindBy(xpath = "//table[@class='ProductsTable']//tr")
    List<WebElement> tblRows;

    // ============================
    // Table Verification
    // ============================

    /**
     * Method to verify if the product table is displayed on the page.
     *
     * @return true if the table is displayed, false otherwise
     */
    public boolean validateProductTableDisplayed() {
        return tblProducts.isDisplayed(); // Check if the table is displayed
    }

    // ============================
    // Dynamic Row Retrieval
    // ============================

    /**
     * Method to get a specific row element based on the product name.
     *
     * @param productName Name of the product
     * @return WebElement representing the row
     */
    public WebElement getRowElement(String productName) {
        // XPath to locate the row based on the product name
        String dynamicXpath = "//table[@class='ProductsTable']//tr[td[normalize-space()='" + productName + "']]";
        return driver.findElement(By.xpath(dynamicXpath)); // Find and return the row element
    }

    // ============================
    // Product Details Retrieval
    // ============================

    /**
     * Method to get the price of a specific product.
     *
     * @param productName Name of the product
     * @return Price of the product as a String
     */
    public String getProductPrice(String productName) {
        WebElement row = getRowElement(productName); // Get the row for the product
        return row.findElement(By.xpath("./td[2]")).getText(); // Get the price from the second column
    }

    /**
     * Method to get the discount of a specific product.
     *
     * @param productName Name of the product
     * @return Discount of the product as a String
     */
    public String getProductDiscount(String productName) {
        WebElement row = getRowElement(productName); // Get the row for the product
        return row.findElement(By.xpath("./td[3]")).getText(); // Get the discount from the third column
    }

    // ============================
    // Product Existence Verification
    // ============================

    /**
     * Method to verify if a product exists in the table.
     *
     * @param productName Name of the product
     * @return true if the product exists, false otherwise
     */
    public boolean verifyProductExists(String productName) {
        try {
            getRowElement(productName); // Try to locate the row for the product
            return true; // Return true if the row is found
        } catch (Exception e) {
            return false; // Return false if an exception occurs (product not found)
        }
    }

    // ============================
    // Product Details Verification
    // ============================

    /**
     * Method to verify the details of a product in the table.
     *
     * @param productName Name of the product
     * @param price       Expected price of the product
     * @param discount    Expected discount of the product
     */
    public void verifyProductDetails(String productName, String price, String discount) {
        String actualPrice = getProductPrice(productName); // Get the actual price
        String actualDiscount = getProductDiscount(productName); // Get the actual discount

        // Validate that the actual price and discount match the expected values
        if (!actualPrice.equals(price) || !actualDiscount.equals(discount)) {
            throw new AssertionError(
                "Product details mismatch for " + productName +
                " Expected Price: " + price +
                " Actual Price: " + actualPrice +
                " Expected Discount: " + discount +
                " Actual Discount: " + actualDiscount
            );
        }
    }

    // ============================
    // Debugging Utility
    // ============================

    /**
     * Method to print all products in the table.
     * Useful for debugging purposes.
     */
    public void printAllProducts() {
        // Iterate through all rows in the table (excluding the header row)
        for (int i = 1; i < tblRows.size(); i++) {
            List<WebElement> cols = tblRows.get(i).findElements(By.tagName("td")); // Get all columns in the row
            String product = cols.get(0).getText(); // Get the product name
            String price = cols.get(1).getText(); // Get the price
            String discount = cols.get(2).getText(); // Get the discount
            System.out.println(product + " | " + price + " | " + discount); // Print the product details
        }
    }
}