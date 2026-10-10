package com.webOrders.common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webOrders.home.WebOrders_SignInPage;

public class WebOrders_BasePage {

    // WebDriver instance to interact with the browser
    private WebDriver driver;

    // WebDriverWait instance to handle explicit waits
    private WebDriverWait wait;

    // Locating the username input field using its ID
    @FindBy(id = "ctl00_MainContent_username")
    private WebElement txtUsername;

    // Locating the password input field using its ID
    @FindBy(id = "ctl00_MainContent_password")
    private WebElement txtPassword;

    // Locating the login button using its ID
    @FindBy(id = "ctl00_MainContent_login_button")
    private WebElement btnLogin;

    // Locating the logout link using its link text
    @FindBy(linkText = "Logout")
    private WebElement lnLogout;

    // Constructor to initialize the WebDriver and WebDriverWait instances
    // Also initializes the WebElements using PageFactory
    public WebOrders_BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Explicit wait timeout set to 15 seconds
        PageFactory.initElements(driver, this); // Initializes the WebElements in this class
    }

    // Method to navigate to the home page and perform login
    public WebOrders_SignInPage GoToHomePageAndSignIn(String username, String passwd) {
        return signInToWebOrders(username, passwd); // Calls the signInToWebOrders method
    }

    // Method to perform the login process
    public WebOrders_SignInPage signInToWebOrders(String username, String passwd) {
        enterUserName(username); // Enters the username
        enterPassword(passwd); // Enters the password
        submitButton(); // Clicks the login button
        return new WebOrders_SignInPage(driver); // Returns a new instance of the WebOrders_SignInPage
    }

    // Method to enter the username into the username field
    public void enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOf(txtUsername)); // Waits until the username field is visible
        txtUsername.clear(); // Clears any existing text in the username field
        txtUsername.sendKeys(username); // Enters the provided username
    }

    // Method to enter the password into the password field
    public void enterPassword(String passwd) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword)); // Waits until the password field is visible
        txtPassword.clear(); // Clears any existing text in the password field
        txtPassword.sendKeys(passwd); // Enters the provided password
    }

    // Method to click the login button
    public void submitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)); // Waits until the login button is clickable
        btnLogin.click(); // Clicks the login button
    }

    // Method to perform logout
    public void logout() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(lnLogout)); // Waits until the logout link is clickable
        lnLogout.click(); // Clicks the logout link
        Thread.sleep(2000); // Waits for 2 seconds to ensure the logout process is complete
    }

    // Getter method to retrieve the WebDriver instance
    public WebDriver getDriver() {
        return driver;
    }
}