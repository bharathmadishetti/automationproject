package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utility {

	
	public static WebDriver getBrowser() {
		
		String os=System.getProperty("os.name");
		if(os.toLowerCase().contains("win")) {
			System.out.println("Running on Windows OS.");
			System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");			
		}
		else if(os.toLowerCase().contains("mac")) {
			System.out.println("Running on MAC OS.");
			System.setProperty("webdriver.chrome.driver", "drivers/mac/chromedriver");
		}
		else if(os.toLowerCase().contains("nix")||os.toLowerCase().contains("nux")) {
			System.out.println("Running on Linux OS.");
			System.setProperty("webdriver.chrome.driver", "drivers/linux/chromedriver");
		}
		else {
			System.out.println("Fail - Driver not available for the OS:="+os);
			return null;
		}
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void scrollToElement(WebDriver driver,By by) throws InterruptedException {		
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000); 
	}
}
