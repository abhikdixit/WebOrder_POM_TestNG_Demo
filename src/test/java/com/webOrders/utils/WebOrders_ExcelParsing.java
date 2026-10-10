package com.webOrders.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.EncryptedDocumentException;

public class WebOrders_ExcelParsing {

    // Sheet and Workbook instances for Excel file operations
    private static Sheet ExcelWSheet;
    private static Workbook ExcelWBook;

    /**
     * Reads data from an Excel file and returns it as a 2D object array.
     *
     * @param fileName  Path to the Excel file.
     * @param sheetName Name of the sheet to read data from.
     * @return 2D object array containing the Excel data.
     * @throws EncryptedDocumentException If the file is encrypted.
     * @throws IOException                If an I/O error occurs.
     */
    public Object[][] getExcelData(String fileName, String sheetName) throws EncryptedDocumentException, IOException {
        // Open the Excel file
        FileInputStream ExcelFile = new FileInputStream(fileName);

        // Create Workbook and Sheet instances
        ExcelWBook = WorkbookFactory.create(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(sheetName);

        // Get the total number of rows and columns
        int totalNoOfRows = ExcelWSheet.getPhysicalNumberOfRows() - 1; // Exclude header row
        int totalNoOfCols = ExcelWSheet.getRow(0).getLastCellNum();

        // Initialize a 2D array to store the data
        String[][] arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

        // Loop through rows and columns to read data
        for (int i = 0; i < totalNoOfRows; i++) {
            Row row = ExcelWSheet.getRow(i + 1); // Skip header row
            for (int j = 0; j < totalNoOfCols; j++) {
                // Get the cell value and store it in the array
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                arrayExcelData[i][j] = getCellValueAsString(cell);
                System.out.print(arrayExcelData[i][j] + " | ");
            }
            System.out.println();
        }

        // Close the workbook and file input stream
        ExcelWBook.close();
        ExcelFile.close();

        return arrayExcelData;
    }

    /**
     * Converts a cell value to a string based on its type.
     *
     * @param cell The cell to convert.
     * @return The string representation of the cell value.
     */
    public static String getCellValueAsString(Cell cell) {
        String value = "";

        // Determine the cell type and convert accordingly
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue().trim();
                break;

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Format date cells
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                    value = sdf.format(date);
                } else {
                    // Convert numeric cells to long
                    value = String.valueOf((long) cell.getNumericCellValue());
                }
                break;

            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;

            case BLANK:
                value = "";
                break;

            default:
                value = "";
        }

        return value.trim();
    }
}