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
	Code: driver.get("https://www.google.com/");
	
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
	Code: driver.navigate().to("https://www.examples/AutomationPractice/");
	
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
		
## Code for Actions Class - moveTo, context click, sendKeys,double click, dragAndDrop
	Package:	import org.openqa.selenium.interactions.Actions;
			 	import org.openqa.selenium.Keys;
	Code:	Actions actions = new Actions(driver);
		WebElement searchBox = driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
		WebElement accountList = driver.findElement(By.xpath("//a[@data-csa-c-slot-id='nav-link-accountList']"));
		//*****send keys and double click***********//
		actions.moveToElement(searchBox).click().keyDown(Keys.SHIFT).sendKeys("iphone").keyUp(Keys.SHIFT).doubleClick()
				.build().perform();
		//************** Moves to specific element and right click(contextClick()) ********************/
		actions.moveToElement(accountList).contextClick().build().perform();
		//******************************* Drag and Drop **************************************************/
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		actions.dragAndDrop(draggable, droppable).build().perform();*
		
## Switching Windows (Child/Parent) Code:
	Package: 	import java.util.Set;
					import java.util.Iterator;
	Code:		Set<String> windows = driver.getWindowHandles();
				Iterator<String> itr = windows.iterator();
				String parentWindow = itr.next();
				String childWindow = itr.next();
				driver.switchTo().window(childWindow);
				driver.switchTo().window(parentWindow);
				
## Frames:
	Code:	name/id => driver.switchTo().frame("frame-top");
			index	=>	driver.switchTo().frame(2);
			WebElement =>	WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"))
							driver.switchTo().frame(demoFrame);
## JavascriptExecutor (Scroll example):
	Package: import org.openqa.selenium.JavascriptExecutor;
	Code:	JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)");
		jse.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
		
## ChromeOptions
	Package:	import org.openqa.selenium.chrome.ChromeDriver;
				import org.openqa.selenium.chrome.ChromeOptions;
	Code: 		ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				WebDriver driver = new ChromeDriver(options);
	ChromeOptions options = new ChromeOptions();

	// Add the WebDriver proxy capability.
	Proxy proxy = new Proxy();
	proxy.setHttpProxy("myhttpproxy:3337");
	options.setCapability("proxy", proxy);
	
	// Add a ChromeDriver-specific capability.
	options.addExtensions(new File("/path/to/extension.crx"));
	ChromeDriver driver = new ChromeDriver(options);
	
	//Use a custom profile
	ChromeOptions options = new ChromeOptions();
	options.addArguments("user-data-dir=/path/to/your/custom/profile");
	
	//Start Chrome maximized
	ChromeOptions options = new ChromeOptions();
	options.addArguments("start-maximized");
	
	Use a Chrome executable in a non-standard location
	ChromeOptions options = new ChromeOptions();
	options.setBinary("/path/to/other/chrome/binary");
	
	//Block dialog windows
	ChromeOptions options = new ChromeOptions();options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popupblocking"));
	
	//Set download directory
	ChromeOptions options = new ChromeOptions();
	Map<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("download.default_directory", "/directory/path");
	options.setExperimentalOption("prefs", prefs);
	
## Cookie(s)
	Code: //Delete all cookies
		driver.manage().deleteAllCookies();
		//Delete a specific Cookie
		driver.manage().deleteCookieNamed("name of the cookie=>eg: sessionKey");
		
		Set<Cookie> cookies = driver.manage().getCookies();
	
	//Get all cookies
	for (Cookie ck : cookies) {
	    System.out.println("Cookie Name: " + ck.getName());
	    System.out.println("Cookie Value: " + ck.getValue());
	    System.out.println("Cookie Domain: " + ck.getDomain());
	    System.out.println("Cookie Expiry: " + ck.getExpiry());
	    System.out.println("----------------------");
	}
	
	//Get a specific cookie by name
	Cookie sessionCookie = driver.manage().getCookieNamed("JSESSIONID");
	System.out.println("Session Cookie Value: " + sessionCookie.getValue());
	
	//Add a new cookie
	Cookie newCookie = new Cookie("testCookie", "12345");
	driver.manage().addCookie(newCookie);
	System.out.println("New cookie added successfully!");
	
## Taking Screenshot
	Package: 	import java.io.File;
				import org.apache.commons.io.FileUtils;
				import org.openqa.selenium.OutputType;
				import org.openqa.selenium.TakesScreenshot;
	Code:		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/screenshots/screenshot.png"));
	
## Broken Links Identification and SoftAssertion
	Package:	import java.io.IOException;
				import java.net.HttpURLConnection;
				import java.net.URL;
				import org.testng.asserts.SoftAssert;
	Code:	SoftAssert softAssert = new SoftAssert();
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			int responseCode = connection.getResponseCode();
			// System.out.println(responseCode);
			softAssert.assertTrue(responseCode < 400,
					"The Broken link is: " + link.getText() + " with the status Code: " + responseCode);
		}
		driver.quit();
		softAssert.assertAll();
		
## Streams Java
	Package: import java.util.stream.Stream;
	Code:	Stream.of("apple", "alpha", "banana", "gauva", "butter", "box", "papaya", "button", "best", "bata", "beta",
				"bad", "cucumber").filter(name -> name.endsWith("a")).map(String::toUpperCase)
				.forEach(System.out::println);
			//Another example
			List<String> list = Arrays.asList("banana", "butter", "box", "button", "best", "bata", "beta", "bad");
			System.out.println("**********Sort the list starts with 'b' to upper case and print them******************");
			list.stream().filter(name -> name.startsWith("b")).sorted().map(String::toUpperCase)
				.forEach(System.out::println);