package selenium.learning.java.selenium.basics;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PrintLinksCountInPage {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		int linksCount = driver.findElements(By.tagName("a")).size();
		System.out.println(linksCount);
		WebElement footerDriver = driver.findElement(By.xpath("//div[@id='gf-BIG']"));
		int footerlinksSize = footerDriver.findElements(By.tagName("a")).size();
		System.out.println(footerlinksSize);
		WebElement discountCoupons = driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td[1]/ul"));
		int linkDiscountCoupons = discountCoupons.findElements(By.tagName("a")).size();
		System.out.println(linkDiscountCoupons);
		Actions actions = new Actions(driver);
		for (int i = 1; i < linkDiscountCoupons; i++) {
			actions.keyDown(Keys.CONTROL).click(discountCoupons.findElements(By.tagName("a")).get(i))
					.keyUp(Keys.CONTROL).build().perform();
		}
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> itr = windowHandles.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			driver.switchTo().window(childWindow);
			System.out.println(driver.getTitle());
		}
		driver.quit();

	}

}
