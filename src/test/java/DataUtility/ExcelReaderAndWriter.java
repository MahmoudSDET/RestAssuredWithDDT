package DataUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;



public class ExcelReaderAndWriter {
	
	
	String filepath =System.getProperty("user.dir")+"/Data/SoapTestData.xlsx";
	
	String filepath_1 =System.getProperty("user.dir")+"/Data/RestTestData.xlsx";
	  public Object[][] dataSoupServices() throws IOException {
          
	    File file = new File(filepath);
	    FileInputStream fis = new FileInputStream(file);

	    XSSFWorkbook wb = new XSSFWorkbook(fis);
	    XSSFSheet sheet = wb.getSheetAt(0);
	    wb.close();
	    int lastRowNum = sheet.getLastRowNum() ;
	    int lastCellNum = sheet.getRow(0).getLastCellNum();
	    Object[][] obj = new Object[lastRowNum][1];

	    for (int i = 0; i < lastRowNum; i++) {
	      Map<Object, Object> datamap = new HashMap<>();
	      for (int j = 0; j < lastCellNum; j++) {
	        datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
	      }
	      obj[i][0] = datamap;

	    }
	    return  obj;
	  }
	  
	  
	  public Object[][] dataRestServices() throws IOException {
          
		    File file = new File(filepath_1);
		    FileInputStream fis = new FileInputStream(file);

		    XSSFWorkbook wb = new XSSFWorkbook(fis);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    wb.close();
		    int lastRowNum = sheet.getLastRowNum() ;
		    int lastCellNum = sheet.getRow(0).getLastCellNum();
		    Object[][] obj = new Object[lastRowNum][1];

		    for (int i = 0; i < lastRowNum; i++) {
		      Map<Object, Object> datamap = new HashMap<>();
		      for (int j = 0; j < lastCellNum; j++) {
		        datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
		      }
		      obj[i][0] = datamap;

		    }
		    return  obj;
		  }

}
