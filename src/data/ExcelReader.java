package data;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e){

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			Cell.setCellType(Cell.CELL_TYPE_STRING);

			String CellData = Cell.getStringCellValue();
			

			return CellData;

			}catch (Exception e){

			return"";

			}

}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

// Constant variables Test Data path and Test Data file name

				FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}



public static void main(String args[]) throws Exception{
	
	//ExcelReader obj =new ExcelReader();
	ExcelReader.setExcelFile(Constant.Path_TestData,"Sheet1");
	//Cell.setCellType(Cell.CELL_TYPE_STRING);
	String susername = ExcelReader.getCellData(1,0);
	 System.out.println(susername);
	  		String spassword =ExcelReader.getCellData(1,1);
	  		System.out.println(spassword);
 
}
}





























/*import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    

    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create a object of File class to open xlsx file

    File file =    new File(filePath+"C://Program Files//Selenium//workspace//Selenium//src//TestData.xlsx"+fileName);

    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook myWorkbook = null;

    //Find the file extension by spliting file name in substring and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx")){

    //If it is xlsx file then create object of XSSFWorkbook class

    	myWorkbook= new XSSFWorkbook(inputStream);

    }

    //Check condition if the file is xls file

    else if(fileExtensionName.equals(".xls")){

        //If it is xls file then create object of XSSFWorkbook class

    	myWorkbook = new HSSFWorkbook(inputStream);

    }

    //Read sheet inside the workbook by its name

    Sheet mysheet = myWorkbook.getSheet(sheetName);

    //Find number of rows in excel file

    int rowCount = mysheet.getLastRowNum()-mysheet.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 0; i < rowCount+1; i++) {

        Row row = mysheet.getRow(i);

        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Print excel data in console

            System.out.print(row.getCell(j).getStringCellValue()+"|| ");

        }

        System.out.println();

    }

    

    }

    

    //Main function is calling readExcel function to read data from excel file

    public static void main(String args[]) throws IOException{

    //Create a object of ReadGuru99ExcelFile class

    ExcelReader objExcelFile = new ExcelReader();

    //Prepare the path of excel file

    String filePath = System.getProperty("user.dir")+"\\src\\pkg";

    //Call read file method of the class to read data

    objExcelFile.readExcel(filePath,"TestData.xlsx","Sheet1");

    }
}*/