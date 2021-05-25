package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class AllBaseClass {

		public static WebDriver driver;
		public static Properties propertie;
		
		public static Properties loadFileProoperties() throws IOException {
			
			FileInputStream stream = new FileInputStream("config.properties");
			propertie = new Properties();
			propertie.load(stream);
			return propertie;
			
		}
		
		@BeforeSuite
		public static void lanchBrowser() throws IOException {
			loadFileProoperties();
			String browser = propertie.getProperty("Broswer");
			String location = propertie.getProperty("Location");
			String url = propertie.getProperty("url");
			
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", location);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
			else if ( browser.equalsIgnoreCase("firfox")) {
				
				System.setProperty("webdriver.gecko.driver", location);
				driver = new FirefoxDriver();
				driver.get(url);
			}
			else if (browser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", location);
				driver = new EdgeDriver();
				driver.get(url);
				
			}
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			
		}
		
		@AfterSuite
		public void closeBrowser() {
			
			driver.quit();
		}
		
		
		public static void click(WebElement element) {
			
			element.click();
		}
		
		public static void clear (WebElement element) {
			
			element.clear();
		}
		public static void senkeys (WebElement element , String value) {
			
			element.sendKeys(value);
		}
		
		public static void dropDown (WebElement element, String option, String value) {
			try {
				Select sc = new Select(element);
				if(option.equalsIgnoreCase("byindex")) {
					int parseInt = Integer.parseInt(value);
					sc.selectByIndex(parseInt);
				}
				else if (option.equalsIgnoreCase("byvalue")) {
					sc.selectByValue(value);
				}
				else if (option.equalsIgnoreCase("byVisibletext")) {
					sc.selectByVisibleText(value);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			  }
			}
			
		public static void screenShot(String location) {
			try {
					
					TakesScreenshot ts = (TakesScreenshot) driver;
					File source = ts.getScreenshotAs(OutputType.FILE);
					File destinstion = new File(location);
					FileUtils.copyToDirectory(source, destinstion);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			public static void alert (String option,String value) {
				
				if (option.equalsIgnoreCase("simplealert")) {
					driver.switchTo().alert().accept();
				}
				else if (option.equalsIgnoreCase("confirmalert")) {
					
					if(value.equalsIgnoreCase("accept")) {
						driver.switchTo().alert().accept();
					}
					else if (value.equalsIgnoreCase("dimiss")) {
						driver.switchTo().alert().dismiss();
					}
				
				}
				else if (option.equalsIgnoreCase("propmptalert")) {
					driver.switchTo().alert().sendKeys(value);
					driver.switchTo().alert().accept();
					
				}
				
			}
				
			public static void rightClick() {
				
				Actions action= new Actions(driver);
				action.contextClick().build().perform();;
			}
			
			public static void doubleCllick() {
				
				Actions action = new Actions(driver);
				action.doubleClick().build().perform();;
				
			
			}
			
			public static void moveTOElement(WebElement element) {
				
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
			
			public static void frame (String option, String value, WebElement element) {
				
				if (option.equalsIgnoreCase("byindex")) {
					int parseInt = Integer.parseInt(value);
					driver.switchTo().frame(parseInt);
				}
				else if (option.equalsIgnoreCase("bystring")) {
					driver.switchTo().frame(value);
				}
				else if (option.equalsIgnoreCase("byelement")) {
					driver.switchTo().frame(element);
				}
			}
				
			public void scrollDownAndScrollUp(WebElement element) {
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();",element);
				
			}
			
		
		}





