package com.webOrders.home;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	public class WebOrders_OrdersPage {

	    // WebDriver instance to interact with the browser
	    WebDriver driver;

	    // WebDriverWait instance for explicit waits
	    WebDriverWait wait;

	    // Constructor to initialize WebDriver, WebDriverWait, and PageFactory elements
	    public WebOrders_OrdersPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait timeout set to 10 seconds
	    }

	    // WebElements for the Order page
	    @FindBy(name = "ctl00$MainContent$fmwOrder$txtQuantity")
	    WebElement txtQty;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$ddlProduct")
	    WebElement ddlProduct;

	    @FindBy(xpath = "//input[@value='Calculate']")
	    WebElement btnCalculate;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$txtName")
	    WebElement txtName;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox2")
	    WebElement txtStreet;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox3")
	    WebElement txtCity;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox4")
	    WebElement txtState;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox5")
	    WebElement txtZip;

	    @FindBy(xpath = "//table[@id = 'ctl00_MainContent_fmwOrder_cardList']")
	    WebElement rdoCard;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox6")
	    WebElement txtCardNr;

	    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox1")
	    WebElement txtExpDate;

	    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
	    WebElement btnProcess;

	    @FindBy(xpath = "//input[@value='Reset']")
	    WebElement btnReset;

	    @FindBy(xpath = "//strong[normalize-space()='New order has been successfully added.']")
	    WebElement lblSubmitSucess;

	    @FindBy(xpath = "//em[normalize-space()=\"Field 'Quantity' cannot be empty.\"]")
	    WebElement errEmptyQty;

	    @FindBy(xpath = "//span[normalize-space()='Quantity must be greater than zero.']")
	    WebElement errInvalidQty;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'Customer name' cannot be empty.\"]")
	    WebElement errCustNr;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'Street' cannot be empty.\"]")
	    WebElement errStreet;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'City' cannot be empty.\"]")
	    WebElement errCity;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'Zip' cannot be empty.\"]")
	    WebElement errEmptyZip;

	    @FindBy(xpath = "//span[normalize-space()='Invalid format. Only digits allowed.']")
	    WebElement errInvalidZip;

	    @FindBy(xpath = "//span[normalize-space()='Select a card type.']")
	    WebElement errSelectCard;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'Card Nr' cannot be empty.\"]")
	    WebElement errEmptyCardNr;

	    @FindBy(xpath = "//span[normalize-space()='Invalid format. Only digits allowed.'][@id ='ctl00_MainContent_fmwOrder_RegularExpressionValidator2']")
	    WebElement errInvalidCardNr;

	    @FindBy(xpath = "//span[normalize-space()=\"Field 'Expire date' cannot be empty.\"]")
	    WebElement errEmptyExpiry;

	    @FindBy(xpath = "//span[normalize-space()=\"Invalid format. Required format is mm/yy.\"]")
	    WebElement errInvalidExpiry;

	    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
	    WebElement txtUnitPrice;

	    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
	    WebElement txtDiscount;

	    // Method to get the unit price value
	    public String getUnitPrice() {
	        return txtUnitPrice.getAttribute("value");
	    }

	    // Method to get the discount value
	    public String getDiscount() {
	        return txtDiscount.getAttribute("value");
	    }

	    // Method to get the value of a specific field
	    public String getFieldValue(WebElement element) {
	        return element.getText();
	    }

	    // Method to enter quantity in the quantity field
	    public void enterQuantity(String qty) {
	        txtQty.clear(); // Clear the existing value
	        txtQty.sendKeys(qty); // Enter the new quantity
	    }

	    // Method to select a product from the dropdown
	    public void selectProduct(String product) {
	        Select select = new Select(ddlProduct); // Create a Select object for the dropdown
	        select.selectByVisibleText(product); // Select the product by visible text
	    }

	    // Method to click the Calculate button
	    public void clickCalculate() {
	        btnCalculate.click(); // Click the Calculate button
	    }

	    // Method to enter customer name
	    public void enterName(String name) {
	        txtName.sendKeys(name); // Enter the customer name
	    }

	    // Method to enter street address
	    public void enterStreet(String street) {
	        txtStreet.sendKeys(street); // Enter the street address
	    }

	    // Method to enter city
	    public void enterCity(String city) {
	        txtCity.sendKeys(city); // Enter the city
	    }

	    // Method to enter state
	    public void enterState(String state) {
	        txtState.sendKeys(state); // Enter the state
	    }

	    // Method to enter zip code
	    public void enterZip(String zip) {
	        txtZip.sendKeys(zip); // Enter the zip code
	    }

	    // Method to select a card type
	    public void enterCard(String card) {
	        rdoCard.findElement(By.xpath(".//input[@value = '" + card + "']")).click(); // Select the card type
	    }

	    // Method to enter card number
	    public void enterCardNr(String cardNr) {
	        txtCardNr.sendKeys(cardNr); // Enter the card number
	    }

	    // Method to enter expiry date
	    public void enterExpiryDate(String expDate) {
	        txtExpDate.sendKeys(expDate); // Enter the expiry date
	    }

	    // Method to click the Process button
	    public void clickProcess() {
	        btnProcess.click(); // Click the Process button
	    }

	    // Method to click the Reset button
	    public void clickReset() {
	        btnReset.click(); // Click the Reset button
	    }

	    // Method to check if the submission message is displayed
	    public boolean successfulSubmissionMessage() {
	        return lblSubmitSucess.isDisplayed(); // Return true if the message is displayed
	    }

	    // Method to create an order and validate the result
	    public void createOrder(String product, String qty, String custName, String street, String city, String state,
	                            String zip, String card, String cardNr, String expiry, String expectedResult) throws InterruptedException {

	        switch (expectedResult) {
	            case "success":
	                selectProduct(product);
	                enterQuantity(qty);
	                clickCalculate();
	                enterName(custName);
	                enterStreet(street);
	                enterCity(city);
	                enterState(state);
	                enterZip(zip);
	                enterCard(card);
	                enterCardNr(cardNr);
	                enterExpiryDate(expiry);
	                clickProcess();
	                Assert.assertTrue(successfulSubmissionMessage());
	                break;
	            case "empty_quantity":
	                enterQuantity(qty);
	                clickCalculate();
	                Assert.assertTrue(errEmptyQty.isDisplayed());
	                break;
	            case "invalid_quantity":
	                enterQuantity(qty);
	                clickProcess();
	                Assert.assertTrue(errInvalidQty.isDisplayed());
	                break;
	            case "empty_name":
	                enterName(custName);
	                clickProcess();
	                Assert.assertTrue(errCustNr.isDisplayed());
	                break;
	            case "empty_street":
	                enterStreet(street);
	                clickProcess();
	                Assert.assertTrue(errStreet.isDisplayed());
	                break;
	            case "empty_city":
	                enterCity(city);
	                clickProcess();
	                Assert.assertTrue(errCity.isDisplayed());
	                break;
	            case "empty_zip":
	                enterZip(zip);
	                clickProcess();
	                Assert.assertTrue(errEmptyZip.isDisplayed());
	                break;
	            case "invalid_zip":
	                enterZip(zip);
	                clickProcess();
	                Assert.assertTrue(errInvalidZip.isDisplayed());
	                break;
	            case "empty_card":
	                enterCard(card);
	                clickProcess();
	                Assert.assertTrue(errSelectCard.isDisplayed());
	                break;
	            case "empty_cardnr":
	                enterCardNr(cardNr);
	                clickProcess();
	                Assert.assertTrue(errEmptyCardNr.isDisplayed());
	                break;
	            case "invalid_cardnr":
	                enterCardNr(cardNr);
	                clickProcess();
	                Assert.assertTrue(errInvalidCardNr.isDisplayed());
	                break;
	            case "empty_expdate":
	                enterExpiryDate(expiry);
	                clickProcess();
	                Assert.assertTrue(errEmptyExpiry.isDisplayed());
	                break;
	            case "invalid_expdate":
	                enterExpiryDate(expiry);
	                clickProcess();
	                Assert.assertTrue(errInvalidExpiry.isDisplayed());
	                break;
	        }
	    }

	    // Method to reset the form and validate that all fields are cleared
	    public void resetForm(String product, String qty, String custName, String street, String city, String state,
	                          String zip, String card, String cardNr, String expiry) {

	        wait.until(ExpectedConditions.elementToBeClickable(txtQty));
	        selectProduct(product);
	        enterQuantity(qty);
	        clickCalculate();
	        enterName(custName);
	        enterStreet(street);
	        enterCity(city);
	        enterState(state);
	        enterZip(zip);
	        enterCard(card);
	        enterCardNr(cardNr);
	        enterExpiryDate(expiry);
	        clickReset();

	        // Validate that all fields are cleared
	        Assert.assertEquals("", getFieldValue(txtName));
	        Assert.assertEquals("", getFieldValue(txtQty));
	        Assert.assertEquals("", getFieldValue(txtState));
	        Assert.assertEquals("", getFieldValue(txtStreet));
	        Assert.assertEquals("", getFieldValue(txtCity));
	        Assert.assertEquals("", getFieldValue(txtCardNr));
	        Assert.assertEquals("", getFieldValue(txtZip));
	        Assert.assertEquals("", getFieldValue(txtExpDate));
	    }
	}