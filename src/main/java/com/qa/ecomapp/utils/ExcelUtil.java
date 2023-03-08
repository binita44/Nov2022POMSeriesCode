package com.qa.ecomapp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static final String TEST_DATA_EXCELSHEET_PATH = "./src/test/resources/testData/opencartregisterdata.xlsx";
	private static Workbook book;//cannot use non static variable directly in static method
	private static Sheet sheet;//always import from ss poi....
	
	
	//create method to read data
	public static Object[][]getTestData(String sheetName) { //give me sheet name and i will give you data
		System.out.println("Reading the data from excel : " + sheetName);
		
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_EXCELSHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);//will go to the sheet that we provide
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//column count is constant, only number of row will vary
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
