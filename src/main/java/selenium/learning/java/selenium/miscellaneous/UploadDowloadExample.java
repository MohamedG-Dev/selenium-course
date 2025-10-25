package selenium.learning.java.selenium.miscellaneous;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UploadDowloadExample {
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	DataFormatter formatter = new DataFormatter();
	WebDriver driver;

	@Test()
	public void uploadDownloadExcelFile() throws IOException {
		String fruitName = "Mango";
		File renamed = null;

		// Define workspace download folder
		String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";
		// Create folder if it doesn’t exist
		File folder = new File(downloadPath);
		if (!folder.exists())
			folder.mkdir();
		// Delete any existing files before test run
		for (File existingFile : folder.listFiles()) {
			if (existingFile.exists()) {
				existingFile.delete();
				System.out.println("Deleted existing File: " + existingFile);
			}
		}

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", downloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("safebrowsing.enabled", true);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.extensions_to_open", "application/vnd.ms-excel");

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		// Download a file
		driver.findElement(By.cssSelector("#downloadButton")).click();

		// waiting for the file to download and renaming the file
		try {
			File downloadFile = waitForFile(downloadPath, 20);
			if (downloadFile != null && downloadFile.getName().endsWith(".tmp")) {
				System.out.println("File downloaded Successfull: " + downloadFile);
				renamed = new File(downloadPath + File.separator + "donwloadedFile.xlsx");
				boolean renamedSuccess = downloadFile.renameTo(renamed);
				Assert.assertTrue(renamedSuccess, "The renaming of file is unsuccessfull");
			} else
				Assert.fail("File is not downloaded");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// update value into excel
		String sheetName = "Sheet1";
		String columnName = "Price";
		int price = 9090;
		int columnIndex = getColumnIndex(getSheetByName(renamed, sheetName), columnName);
		int rowIndex = getRowIndex(getSheetByName(renamed, sheetName), fruitName);
		System.out.println(columnIndex + " " + rowIndex);
		updateDataInCell(renamed, getSheetByName(renamed, sheetName), rowIndex, columnIndex, price);

		// Upload a file
		WebElement uploadButton = driver.findElement(By.cssSelector("#fileinput"));
		// send the file path in send keys method
		uploadButton.sendKeys(renamed.getAbsolutePath());
		// wait for the successful message disappears
		By successfulMsgToast = By.xpath("//div[@class='Toastify__toast-body']/child::div[2]");
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(successfulMsgToast));
		String successfullText = driver.findElement(successfulMsgToast).getText();
		Assert.assertEquals(successfullText, "Updated Excel Data Successfully.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(successfulMsgToast));
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		Assert.assertEquals(actualPrice, String.valueOf(price));
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled = false)
	public void testExcelCode() {
		File file = new File(System.getProperty("user.dir") + "/downloads/donwloadedFile.xlsx");
		String sheetName = "Sheet1";
		String columnName = "Price";
		int columnIndex = getColumnIndex(getSheetByName(file, sheetName), columnName);
		int rowIndex = getRowIndex(getSheetByName(file, sheetName), "Papaya");
		System.out.println(columnIndex + " " + rowIndex);
		updateDataInCell(file, getSheetByName(file, sheetName), rowIndex, columnIndex, 800);
	}

	public File waitForFile(String folderPath, int timeoutSeconds) throws InterruptedException {
		File folder = new File(folderPath);
		int waited = 0;
		while (waited < timeoutSeconds) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0)
				return files[0];
			Thread.sleep(1000);
			waited++;
		}
		return null;
	}

	public int getColumnIndex(Sheet sheet, String columnName) {
		Row firstRow = sheet.getRow(0);
		return IntStream.range(0, firstRow.getLastCellNum())
				.filter(k -> firstRow.getCell(k).getStringCellValue().equalsIgnoreCase(columnName)).findFirst()
				.orElse(-1);

	}

	public int getRowIndex(Sheet sheet, String fruitName) {
		return IntStream.range(0, sheet.getPhysicalNumberOfRows()).filter(rowIndex -> {
			Row row = sheet.getRow(rowIndex);
			if (row == null)
				return false;
			return IntStream.range(0, row.getLastCellNum()).mapToObj(row::getCell).filter(cell -> cell != null)
					.map(cell -> formatter.formatCellValue(cell)).anyMatch(value -> value.equalsIgnoreCase(fruitName));
		}).findFirst().orElse(-1);
	}

	public int getRowIndex(File file, String sheetName, int columnIndex, String fruitName) {
		int rowCount = 1, index = -1;
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			int totalSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < totalSheets; i++) {
				Iterator<Row> rows = workbook.getSheetAt(i).iterator();
				while (rows.hasNext()) {
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						if (formatter.formatCellValue(cell).equalsIgnoreCase(fruitName)) {
							index = rowCount;
							return index;
						}
					}
					rowCount++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return index;
	}

	public Sheet getSheetByName(File file, String sheetName) {
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			return StreamSupport.stream(workbook.spliterator(), false)
					.filter(sheet -> sheet.getSheetName().equalsIgnoreCase(sheetName)).findFirst()
					.orElseThrow(() -> new RuntimeException("Sheet not Found:" + sheetName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateDataInCell(File file, Sheet sheet, int rowIndex, int columnIndex, int Value) {
		sheet.getRow(rowIndex).getCell(columnIndex).setCellValue(Value);
		try {
			FileOutputStream fout = new FileOutputStream(file);
			workbook.write(fout);
			workbook.close();
			fout.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
