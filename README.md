#Selenium Concepts and Code examples

## Command to set the path for the browser drivers:
	-> Chrome: System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	-> Firefox: System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
	-> Edge: System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
	
## Command to launch Chrome browser: 
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.chrome.ChromeDriver;
	Code:	WebDriver driver = new ChromeDriver();
	
## Command to launch Firefox browser: 
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.firefox.FirefoxDriver;
	Code:	WebDriver driver = new EdgeDriver();
	
## Command to launch Edge browser:
	package: 	import org.openqa.selenium.WebDriver;
				import org.openqa.selenium.edge.EdgeDriver;
	Code:	WebDriver driver = new EdgeDriver();

## Command to maximize browser window:
	Code:	driver.manage().window().maximize();
	
## Command to hit a URL:
	Code: driver.get("https://rahulshettyacademy.com/");
	
## Command to get title of page:
	Code: driver.getTitle();
	
## Command to get current URL:
	Code: driver.getCurrentUrl();
	
## Command to close the browser:
	--> Current window: driver.close();
	--> All open tabs/windows: driver.quit();

## Command to sendKeys:
	Code: driver.findElement(By.id("inputUsername")).sendKeys("rahul");

## Command to click:
	Code: driver.findElement(By.linkText("Forgot your password?")).click();
	
## Command to Clear Text field:
	Code: driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
	
## Finding elements using Locators:
	package: import org.openqa.selenium.By;
	Code:	
		1. id: driver.findElement(By.id("inputUsername")).sendKeys("rahul");
		2. name: driver.findElement(By.name("inputPassword")).sendKeys("hello123");
		3. className: driver.findElement(By.className("signInBtn")).click();
		4. cssSelector: driver.findElement(By.cssSelector("p.error")).getText();
		5. linkText: driver.findElement(By.linkText("Forgot your password?")).click();
		6. xpath: driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Shelby");
		7. tagName: diver.findElement(By.tagName("a")).click();
			 