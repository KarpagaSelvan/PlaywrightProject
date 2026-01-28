package com.dataprovider;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static String[][] readExcelData(String fileLocation, String sheetName) throws IOException {

		// Locate the test data file
//		String fileLocation = ".//TestData//Sorting Options Data.xlsx";

		// Locate the workbook & sheet
		XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int lastRowNum = sheet.getLastRowNum();
		short lastCellNum = sheet.getRow(0).getLastCellNum();

		// Storing the fetched values in the 2 dim array to reuse it DataPRovider
		// Providing the size of an Array

		String[][] data = new String[lastRowNum][lastCellNum];

		for (int i = 1; i <= lastRowNum; i++) { /* Because 0th row is a header */
			// Read the data from sheet
			XSSFRow row = sheet.getRow(i); // Because 0th row is header not value

			for (int j = 0; j < lastCellNum; j++) { /* Because I have only 2 columns */
				XSSFCell cell = row.getCell(j);

				DataFormatter dataFormatter = new DataFormatter();
				String formatCellValue = dataFormatter.formatCellValue(cell); // Formatting the values to String,
																				// because it is a input and anyway
																				// we're going to use it as SendKeys("")
				data[i - 1][j] = formatCellValue; // i-1 is because we need 0,0 as a first pair of values in @DP

//				System.out.println(stringCellValue);
			}
		}

		workbook.close();
		return data;

	}

}
