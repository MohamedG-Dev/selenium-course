package selenium.learning.java.excelDemo;

import java.util.List;

import org.testng.annotations.Test;

public class ReadExcelData {

	@Test
	public void readExcelData() {
		ExcelReadDemo excelReader = new ExcelReadDemo();
		List<String> list = excelReader.getExcelRowData("Add Profile");
		list.stream().forEach(System.out::println);

	}

}
