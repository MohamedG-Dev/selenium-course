package selenium.learning.java.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FrameExample {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/droppable/");
		WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(demoFrame);
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		Actions actions = new Actions(driver);
		actions.dragAndDrop(draggable, droppable).build().perform();
		//switching back to the main content of the page
		//driver.switchTo().defaultContent();

	}

}
