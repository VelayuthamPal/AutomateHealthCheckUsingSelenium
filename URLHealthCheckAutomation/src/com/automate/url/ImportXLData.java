package com.automate.url;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.url.dao.UrlStruct;
//@Test
public class ImportXLData {

	// First Set of Declarations for Excel Work book
	String filePath;
	FileInputStream inputStream;
	Workbook workBook;
	Sheet urlSheet;
	
	public static LinkedHashMap<Integer, UrlStruct> urlLhm = new LinkedHashMap<>();
	public static Integer hashMapId = 1;

	public void loadData() throws IOException {

		// Initializations for first set of declarations
		filePath = "../URLHealthCheckAutomation/TestFiles/UrlList.xlsx";

		inputStream = new FileInputStream(filePath);

		workBook = new XSSFWorkbook(inputStream);

		urlSheet = workBook.getSheet("URL List");

		// Create Row Iterator to iterate through the rows
		Iterator<Row> rowIterator = urlSheet.iterator();

		while (rowIterator.hasNext()) {

			Row excelRow = rowIterator.next();

			// Create Cell Iterator to iterate through the cells
			Iterator<Cell> cellIterator = excelRow.cellIterator();
			
			//Initialization For LinkedHashMap Requirement
			int colNum = 0;
			String name = null;
			String serverType = null;
			
			while (cellIterator.hasNext()) {

				Cell excelCell = cellIterator.next();

				System.out.println(excelCell);
				
				if(colNum == 0){
					name = excelCell.getStringCellValue();
				}
				
				else {
					serverType = excelCell.getStringCellValue();
				}
				colNum++;
			}
			
			UrlStruct url = new UrlStruct(name, serverType); 
			urlLhm.put(hashMapId, url);
			hashMapId++;

			System.out.println();
		}

	}
	
	public LinkedHashMap<Integer, UrlStruct> getValueFromLinkedHashMap(){
		
		return urlLhm;
	}

	//Constructor for ImportXLData
	public ImportXLData() throws IOException {
		super();
		loadData();
	}

}
