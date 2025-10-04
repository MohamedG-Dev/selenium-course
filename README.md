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

## Command to navigate to a URL:
	Code: driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
	
## Command to navigate back:
	Code: driver.navigate().back();
	
## Command to navigate forward:
	Code: driver.navigate().forward();
	
## Command to refresh:
	Code: driver.navigate().refresh();
	
## Code for Handling static dropdowns:
	Package: import org.openqa.selenium.support.ui.Select;
	Code: WebElement currenyDropDown = driver.findElement(By.xpath("//select[contains(@id,'DropDownListCurrency')]"));
		Select dropdown = new Select(currenyDropDown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());	
		
## Command to check a checkbox is selected:
	Code: driver.findElement(By.xpath("//input[contains(@id,'SeniorCitizenDiscount')]")).isSelected();
	
## Command(s) to handle alert:
	Code: driver.switchTo().alert();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();

## Command for Implicit wait:
	Code: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	
## Command for Explicit Wait - WebDriverWait
	Package: import org.openqa.selenium.support.ui.WebDriverWait;
	Code: WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
## Command for Explicit Wait - FluentWait
	Package: import java.time.Duration;
		import java.util.function.Function;
		import org.openqa.selenium.support.ui.FluentWait;
		import org.openqa.selenium.support.ui.Wait;
	Code: Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		WebElement resultSuccess = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed())
					return driver.findElement(By.xpath("//h4[text()='Hello World!']"));
				else
					return null;
			}
		});
		
## Code for Actions Class - moveTo, context click, sendKeys,double click
	Package:	import org.openqa.selenium.interactions.Actions;
			 	import org.openqa.selenium.Keys;
	Code:	Actions actions = new Actions(driver);
		WebElement searchBox = driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
		WebElement accountList = driver.findElement(By.xpath("//a[@data-csa-c-slot-id='nav-link-accountList']"));
		//*****send keys and double click***********//
		actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone").keyUp(Keys.SHIFT).doubleClick()
				.build().perform();
		//************** Moves to specific element ********************/
		actions.moveToElement(accountList).contextClick().build().perform();