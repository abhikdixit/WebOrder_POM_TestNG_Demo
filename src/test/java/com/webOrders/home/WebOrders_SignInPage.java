package com.webOrders.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrders_SignInPage {

    // WebDriver instance to interact with the browser
    private WebDriver driver;

    // WebElement representing the "Web Orders" text on the sign-in page
    @FindBy(xpath = "//h1[normalize-space()='Web Orders']")
    WebElement txtWebOrder;

    /**
     * Constructor to initialize the WebDriver and PageFactory elements.
     *
     * @param driver WebDriver instance passed from the test class
     */
    public WebOrders_SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    /**
     * Method to verify if the current page URL contains the expected URL.
     *
     * @return true if the current URL contains the expected URL, false otherwise
     */
    public boolean verifySignInPageURL() {
        String pageURL = driver.getCurrentUrl(); // Get the current page URL
        String expectedPageURL = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx";
        return pageURL.contains(expectedPageURL); // Check if the current URL contains the expected URL
    }

    /**
     * Method to verify if the "Web Orders" text is displayed on the page.
     *
     * @return true if the text matches the expected text, false otherwise
     */
    public boolean verifySignInPageText() {
        String pageText = txtWebOrder.getText(); // Get the text of the WebElement
        String expectedPageText = "Web Orders";
        return pageText.contains(expectedPageText); // Check if the text matches the expected text
    }

    /**
     * Method to verify if the page title matches the expected title.
     *
     * @return true if the page title matches the expected title, false otherwise
     */
    public boolean verifySignInPageTitle() {
        String pageTitle = driver.getTitle(); // Get the current page title
        String expectedPageTitle = "Web Orders";
        return pageTitle.contains(expectedPageTitle); // Check if the title matches the expected title
    }
}