package utils;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;

import base.ProjectSpecificMethods;

public class ReadExcel extends ProjectSpecificMethods{
	
//public static void main(String[] args) throws IOException

	public static String[][] excelData(String excelSource) throws IOException
	{		
		//XSSFWorkbook wb = new XSSFWorkbook("./data/CreateNewCase.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+excelSource+".xlsx");		                                    
		XSSFSheet sheet = wb.getSheet("sheet1");
		int lastRowNum = sheet.getLastRowNum();
		//int firstRowNum = sheet.getFirstRowNum();
		short lastCellNum = sheet.getRow(0).getLastCellNum();
		
		String data[][] = new String [lastRowNum][lastCellNum];
		
		for (int i = 1; i <= lastRowNum; i++) {
				
			for (int j = 0; j < lastCellNum; j++) {
				//String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(data[i-1][j]);
			}			
			
		}
		return data;	
		
	}
	
}
