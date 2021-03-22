package com.lufthansa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lufthansa.base.LufthansaTestBase;

public class LufthansaTestUtil extends LufthansaTestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 300;
	public static long IMPLICITLY_WAIT = 200;
	
	//Read Data from Excel Using HashMap method
	public static Workbook workbook = null;
	public static Sheet sheet = null;
	public static Row row = null;
	public static Map<String, String> map = null;
	
	public static Map<String, String> getLufthansaTestData() throws IOException {
		try {
			
			FileInputStream fileInputStream = new FileInputStream(properties.getProperty("ExcelPath"));
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheetAt(0);
			row = sheet.getRow(0);
			int colCount = row.getLastCellNum();
			
			map = new HashMap<String, String>();
			
			for(int i=0; i<colCount; i++) {
				map.put(sheet.getRow(0).getCell(i).toString().trim(), sheet.getRow(1).getCell(i).toString().trim());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		return map;
	}


}
