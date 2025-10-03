package com.ecart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtils {

	
	@SuppressWarnings("resource")
	public static  Map<String,String> getDataFromFile(String sheetName) throws Exception
	{
		
		Map<String, String> dataMap = new HashMap<>();
		
		//To take file Path
		String filepath = System.getProperty("user.dir") + File.separator + "EcartData.xlsx";
		//FileInputStram for read data from any file
		FileInputStream fis = new FileInputStream(filepath);
		//XSSFWorkbook  is a core class in the Apache POI library, specifically designed to work with Excel files in the XLSX format 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//To go into specific sheet
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		for(Row row :sheet) 
		{
			Cell key =row.getCell(1);
			Cell value=row.getCell(2);
			if (key != null && value !=null) 
			{
				dataMap.put(key.getStringCellValue(), value.getStringCellValue());
			}
		}
		
		return dataMap;
	}

}
