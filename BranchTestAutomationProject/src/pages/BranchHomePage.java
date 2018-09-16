package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BranchHomePage {
	public static By linkTeam=By.xpath("//a[text()='Team']");
	public static By buttonRequestADemo=By.xpath("//li[@class='nav-section secondary']//a[text()='Request a Demo']");
	public static By ribbonCustomers=By.xpath("//section[@id='customers']");
	public static By buttonViewOurCustomers=By.xpath("//a[text()='View our customers']");
	
	
	public static void clickOnRequestADemoButton(WebDriver driver) {
		driver.findElement(buttonRequestADemo).click();
	}
	public static void clickOnViewOurCustomersButton(WebDriver driver) {
		Actions actions = new Actions(driver);
		WebElement ribbonCustomersElement = driver.findElement(ribbonCustomers);
		actions.moveToElement(ribbonCustomersElement).perform();;
		driver.findElement(buttonViewOurCustomers).click();
	}

}
