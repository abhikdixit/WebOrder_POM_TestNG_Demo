package com.webOrders.utils;

import java.io.File;

import org.testng.annotations.DataProvider;

public class WebOrders_TestData {

    /**
     * DataProvider for updating the order form with valid data.
     *
     * @return 2D object array containing test data for updating the order form.
     */
    @DataProvider(name = "Update Form")
    public Object[][] UpdateOrderForm() {
        return new Object[][] {
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12/29", "success" },
            { "MyMoney", "5", "Nguyen", "123 Main St", "Dallas", "IL", "75000", "MasterCard", "123456789", "11/29", "success" }
        };
    }

    /**
     * DataProvider for verifying errors in the order form.
     *
     * @return 2D object array containing test data for error scenarios in the order form.
     */
    @DataProvider(name = "Update Order Form Error")
    public Object[][] verifyOrderFormError() {
        return new Object[][] {
            { "MyMoney", "", "Minh", "123 Main St", "Dallas", "ABC", "75000", "Visa", "123456789", "12/29", "empty_quantity" },
            { "MyMoney", "0", "Minh", "123 Main St", "Dallas", "ILA", "75000", "Visa", "123456789", "12/29", "invalid_quantity" },
            { "MyMoney", "5", "", "123 Main St", "Dallas", "ILA", "75000", "Visa", "123456789", "12/29", "empty_name" },
            { "MyMoney", "5", "Minh", "", "Dallas", "IL", "75000", "Visa", "123456789", "12/29", "empty_street" },
            { "MyMoney", "5", "Minh", "123 Main St", "", "IL", "75000", "Visa", "123456789", "12/29", "empty_city" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "", "Visa", "123456789", "12/29", "empty_zip" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "abcde", "Visa", "123456789", "12/29", "invalid_zip" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "", "12/29", "empty_cardnr" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "abcdef", "12/29", "invalid_cardnr" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "", "empty_expdate" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "abcdef", "invalid_date" },
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12-23", "invalid_date" }
        };
    }

    /**
     * DataProvider for resetting the form with valid data.
     *
     * @return 2D object array containing test data for resetting the form.
     */
    @DataProvider(name = "Reset Form")
    public Object[][] ResetForm() {
        return new Object[][] {
            { "MyMoney", "5", "Minh", "123 Main St", "Dallas", "IL", "75000", "Visa", "123456789", "12/29", "success" },
            { "MyMoney", "5", "Nguyen", "123 Main St", "Dallas", "IL", "75000", "MasterCard", "123456789", "11/29", "success" }
        };
    }

    /**
     * DataProvider for reading order data from an Excel file.
     *
     * @return 2D object array containing test data from the Excel file.
     * @throws Exception If the file is not found or an error occurs during reading.
     */
    @DataProvider(name = "OrdersExcelData")
    public Object[][] OrdersDataExcel() throws Exception {
        String filePath = System.getProperty("user.dir") + File.separator + "TestDataFile" + File.separator + "WebOrder_Order_Data.xlsx";
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("Excel file not found: " + filePath);
        }

        System.out.println("Reading Excel File: " + filePath);

        WebOrders_ExcelParsing excel = new WebOrders_ExcelParsing();
        Object[][] data = excel.getExcelData(filePath, "orders");

        System.out.println("Total Test Data Rows: " + data.length);
        return data;
    }

    /**
     * DataProvider for product prices.
     *
     * @return 2D object array containing product names and their prices.
     */
    @DataProvider(name = "ProductPrice")
    public Object[][] productPrice() {
        return new Object[][] {
            { "MyMoney", "$100" },
            { "FamilyAlbum", "$80" },
            { "ScreenSaver", "$20" }
        };
    }
}