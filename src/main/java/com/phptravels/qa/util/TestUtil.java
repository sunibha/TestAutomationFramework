package com.phptravels.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TestUtil {
	public static final int PAGE_LOAD_TIMEOUT = 60;
	public static final int IMPLICITLY_WAIT_TIMEOUT = 20;
	public static ReadConfig config = new ReadConfig();
	
	public HashMap<String, String> checkBrokenLinks(List<String> allLinks){
		
		System.out.println(allLinks);
		HttpURLConnection huc;
		
		HashMap<String, String> brokenLinks = new HashMap<String, String>();
		
		for(String link: allLinks) {
			if (link!=null && link.trim() != "" && !(link.contains("javascript:void"))) {	
				//System.out.println(link);
				try {
					huc = (HttpURLConnection) (new URL(link).openConnection());
					huc.setRequestMethod("HEAD");
					huc.connect();
					if (huc.getResponseCode() >=400) {
						brokenLinks.put(link, huc.getResponseMessage());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}		
		
		System.out.println("Number of broken links in Home Page:  " + brokenLinks.size());
		return brokenLinks;
	}
	
	public static Object[][] getLoginDataFromExcelFile(){
		
		Object data[][] = null;
		XSSFWorkbook workbook;
		
		String testDataFilePath =  config.getTestDataFilePath() + "/" + config.getTestDataFileName();
		String loginDataSheetName = config.getLoginSheetName();
		
		try {
			FileInputStream testDataInputStream = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(testDataInputStream);
			
			Sheet testDataSheet = workbook.getSheet(loginDataSheetName);
			
			int rowCount = testDataSheet.getLastRowNum();
			int colCount = testDataSheet.getRow(0).getLastCellNum();
			
			System.out.println("Row count === " + rowCount);
			
			data = new Object[rowCount][colCount];
			
			for (int i=0; i<rowCount; i++) {
				Row currRow = testDataSheet.getRow(i+1);
				for (int j=0; j<colCount; j++) {
					data[i][j] = currRow.getCell(j).getStringCellValue();
					System.out.println(data[i][j]);
				}
				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
		
		
	}

	//capture screen shot utility
	public static String captureScreenShot(WebDriver driver, String screenShotPrefix) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmssss");
		String currDate = dateFormat.format(new Date());
		
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		String filePath = config.getScreenShotFilePath() + "/" + screenShotPrefix + currDate + ".png";
		File destFile = new File(filePath);
		
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		
		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filePath;
	}

}
