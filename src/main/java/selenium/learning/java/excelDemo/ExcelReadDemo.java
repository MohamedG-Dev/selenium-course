package selenium.learning.java.excelDemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadDemo {

	public List<String> getExcelRowData(String testCaseName) {
		List<String> list = new ArrayList<>();
		String sheetName = "testdata";
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/excelFiles/excelDemoData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int numberOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row firstRow = rows.next();
					Iterator<Cell> cells = firstRow.cellIterator();
					int k = 0, count = 0;
					while (cells.hasNext()) {
						Cell cell = cells.next();
						if (cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
							count = k;
						}
						k++;
					}
					System.out.println("Column Index: " + count);
					while (rows.hasNext()) {
						Row row = rows.next();
						if (row.getCell(count).getStringCellValue().equalsIgnoreCase(testCaseName)) {
							Iterator<Cell> currentRowCells = row.cellIterator();
							while (currentRowCells.hasNext()) {
								Cell currentRowCell = currentRowCells.next();
								if (currentRowCell.getCellType() == CellType.STRING)
									list.add(currentRowCell.getStringCellValue());
								else if (currentRowCell.getCellType() == CellType.NUMERIC)
									list.add(NumberToTextConverter.toText(currentRowCell.getNumericCellValue()));
							}
						}
					}
				}
			}
			workbook.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
