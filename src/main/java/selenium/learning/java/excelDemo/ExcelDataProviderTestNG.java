package selenium.learning.java.excelDemo;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProviderTestNG {

	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "testData")
	public void testCaseData(String str, String str1, String str2, String str3, String num) {
		System.out.println(str + " " + str1 + " " + str2 + " " + str3 + " " + num);
	}

	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/excelFiles/excelDemoData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				XSSFCell currentCell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(currentCell);
			}
		}
		workbook.close();
		return data;
	}
}
