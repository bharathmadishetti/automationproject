package pages;

import org.openqa.selenium.By;

public class GooglePage {

	public static By inputSearch=By.name("q");
	public static By btnGoogleSearch=By.xpath("//input[@value='Google Search']");
	public static By linkSearchResults=By.xpath("//a[contains(.,'Branch - A mobile linking platform')]");
	
}
