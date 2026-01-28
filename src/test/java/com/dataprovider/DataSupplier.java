package com.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataSupplier {

	// dataProvider-->To control only specific data to be executed instead of
	// all, use indices={0,1} or indices={1,4} --> Mentioned index data only
	// will be executed

	@DataProvider(name = "sortingOptionsData")
	Object[][] sortingData() {
		Object[][] data = { { "lohi", "Price (low to high)" }, { "hilo", "Price (high to low)" },
				{ "za", "Name (Z to A)" }, { "az", "Name (A to Z)" }, };
		return data;
	}

	// dataProvider-->To control only specific data to
	// be executed instead of all, use indices={0,1} or
	// indices={1,4} --> Mentioned index data only will
	// be executed

	@DataProvider(name = "sortingOptionsDataFromExcel" /* ,indices = { 2, 3 } */)
	Object[][] sortingDataFromExcel() throws IOException {

		String[][] excelData = ReadExcelData.readExcelData(".//TestData//Sorting Options Data.xlsx", "Sheet1");

		return excelData;

	}

}
