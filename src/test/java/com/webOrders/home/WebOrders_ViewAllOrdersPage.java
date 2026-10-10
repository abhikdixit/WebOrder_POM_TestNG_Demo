package com.webOrders.home;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebOrders_ViewAllOrdersPage {

    // WebDriver instance to interact with the browser
    WebDriver driver;

    // WebDriverWait instance for explicit waits
    WebDriverWait wait;

    // Constructor to initialize WebDriver, WebDriverWait, and PageFactory elements
    public WebOrders_ViewAllOrdersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait timeout set to 10 seconds
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    // ============================
    // Web Elements
    // ============================

    // Table containing all orders
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']")
    WebElement tblAllOrders;

    // List of rows in the orders table
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr")
    List<WebElement> tblRows;

    // Edit button for the first record in the table
    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td/input[@type='image']")
    WebElement btnFirstRecordEdit;

    // "Check All" button
    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    WebElement btnCheckAll;

    // "Uncheck All" button
    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    WebElement btnUncheckAll;

    // "Delete Selected" button
    @FindBy(id = "ctl00_MainContent_btnDelete")
    WebElement btnDeleteSelected;

    // ============================
    // Verify Table
    // ============================

    /**
     * Method to validate that the orders table is displayed on the page.
     */
    public void validateTableIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(tblAllOrders)); // Wait until the table is visible
    }

    // ============================
    // Dynamic Row
    // ============================

    /**
     * Method to get a specific row element based on the name and product.
     *
     * @param name    Name of the customer
     * @param product Product name
     * @return WebElement representing the row
     */
    public WebElement getRowElement(String name, String product) {
        String xpath = "//table[@id='ctl00_MainContent_orderGrid']"
                     + "//td[contains(text(),'" + name + "')]"
                     + "/following-sibling::td[contains(text(),'" + product + "')]";
        return driver.findElement(By.xpath(xpath)); // Find and return the row element
    }

    // ============================
    // Check Box
    // ============================

    /**
     * Method to click the checkbox for a specific row based on the name and product.
     *
     * @param name    Name of the customer
     * @param product Product name
     */
    public void clickCheckBox(String name, String product) {
        WebElement row = getRowElement(name, product); // Get the row element
        row.findElement(By.xpath("./preceding-sibling::td//input")).click(); // Click the checkbox in the row
    }

    // ============================
    // Edit
    // ============================

    /**
     * Method to click the edit button for a specific row based on the name and product.
     *
     * @param name    Name of the customer
     * @param product Product name
     * @return WebOrders_EditOrderPage instance
     */
    public WebOrders_EditOrderPage clickEdit(String name, String product) {
        WebElement row = getRowElement(name, product); // Get the row element
        row.findElement(By.xpath("./following-sibling::td//input")).click(); // Click the edit button in the row
        return new WebOrders_EditOrderPage(driver); // Return the next page object
    }

    /**
     * Method to click the edit button for the first record in the table.
     *
     * @return WebOrders_EditOrderPage instance
     */
    public WebOrders_EditOrderPage clickEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(btnFirstRecordEdit)); // Wait until the button is clickable
        btnFirstRecordEdit.click(); // Click the edit button
        return new WebOrders_EditOrderPage(driver); // Return the next page object
    }

    // ============================
    // Check All
    // ============================

    /**
     * Method to click the "Check All" button.
     */
    public void clickCheckAll() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCheckAll)).click(); // Wait and click the button
    }

    /**
     * Method to click the "Uncheck All" button.
     */
    public void clickUncheckAll() {
        wait.until(ExpectedConditions.elementToBeClickable(btnUncheckAll)).click(); // Wait and click the button
    }

    /**
     * Method to click the "Delete Selected" button.
     */
    public void clickDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteSelected)).click(); // Wait and click the button
    }

    // ============================
    // Verify Checkbox
    // ============================

    /**
     * Method to verify if all checkboxes in the table are selected.
     *
     * @return true if all checkboxes are selected, false otherwise
     */
    public boolean verifyAllOrdersSelected() {
        List<WebElement> checkboxes = tblAllOrders.findElements(By.xpath(".//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                return false; // Return false if any checkbox is not selected
            }
        }
        return true; // Return true if all checkboxes are selected
    }

    /**
     * Method to verify if all checkboxes in the table are unselected.
     *
     * @return true if all checkboxes are unselected, false otherwise
     */
    public boolean verifyAllOrdersUnselected() {
        List<WebElement> checkboxes = tblAllOrders.findElements(By.xpath(".//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                return false; // Return false if any checkbox is selected
            }
        }
        return true; // Return true if all checkboxes are unselected
    }

    // ============================
    // Delete Order
    // ============================

    /**
     * Method to delete a specific order based on the name and product.
     *
     * @param name    Name of the customer
     * @param product Product name
     */
    public void deleteOrder(String name, String product) {
        clickCheckBox(name, product); // Select the checkbox for the order
        clickDelete(); // Click the delete button
    }

    /**
     * Method to verify if a specific record is deleted based on the name.
     *
     * @param name Name of the customer
     * @return true if the record is deleted, false otherwise
     */
    public boolean verifyDeletedRecord(String name) {
        String pageSource = driver.getPageSource(); // Get the page source
        return !pageSource.contains(name); // Return true if the name is not found in the page source
    }

    // ============================
    // Updated Order Verification
    // ============================

    /**
     * Method to verify if an updated order exists in the table.
     *
     * @param values Array of expected values to match in the row
     */
    public void verifyUpdatedOrder(String... values) {
        wait.until(ExpectedConditions.visibilityOf(tblAllOrders)); // Wait until the table is visible
        boolean orderFound = false;

        for (int i = 1; i < tblRows.size(); i++) {
            WebElement row = tblRows.get(i); // Get the row element
            String rowText = row.getText(); // Get the text of the row
            boolean matched = true;

            for (String value : values) {
                String expected = normalize(value); // Normalize the expected value
                String actual = normalize(rowText); // Normalize the actual row text
                if (!actual.contains(expected)) {
                    matched = false; // Set matched to false if any value does not match
                    break;
                }
            }

            if (matched) {
                orderFound = true; // Set orderFound to true if all values match
                break;
            }
        }

        if (!orderFound) {
            throw new AssertionError("No row contains all the provided values: " + Arrays.toString(values));
        }

        System.out.println("Order verification successful");
    }

    // ============================
    // Data Normalization
    // ============================

    /**
     * Method to normalize a string for comparison.
     *
     * @param value Input string
     * @return Normalized string
     */
    private String normalize(String value) {
        if (value == null) {
            return ""; // Return an empty string if the value is null
        }

        value = value.trim().toLowerCase(); // Trim and convert to lowercase

        // Convert date format from "12/2029" to "12/29"
        if (value.matches("\\d{2}/20\\d{2}")) {
            value = value.substring(0, 3) + value.substring(5);
        }

        value = value.replace(",", "").replace(" ", ""); // Remove commas and spaces
        return value; // Return the normalized value
    }
}