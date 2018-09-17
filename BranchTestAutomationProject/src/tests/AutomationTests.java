package tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.Utility;
import data.objects.Employee;
import pages.BranchHomePage;
import pages.GooglePage;
import pages.PartnersPage;
import pages.RequestADemoPage;
import pages.TeamPage;

public class AutomationTests {
	WebDriver driver;
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
		
	}
	
	@Test(priority=2)	
	public void verifyRequestADemoButtonFunctionality() throws InterruptedException {
		SoftAssert softAssertion= new SoftAssert();		
		driver=Utility.getBrowser();
		driver.get("https://branch.io/");
		BranchHomePage.clickOnRequestADemoButton(driver);
		boolean flag=driver.findElement(RequestADemoPage.headerTextRequestADemo).isDisplayed();
		softAssertion.assertEquals(flag, true,"Verify 'Request a Demo' page is displayed after clicking on 'Request a Demo' button in Home page.");
		
		if(flag==true) {
			Reporter.log("<font color='green'>"+"'Request a Demo' page is displayed after clicking on 'Request a Demo' button in Home page."+"</font>");		
		}
		else {
			Reporter.log("<font color='red'>"+"'Request a Demo' page is NOT displayed after clicking on 'Request a Demo' button in Home page."+"</font>");
		}
		
		softAssertion.assertAll();
	}
	
	@Test(priority=1)	
	public void verifyEmployeeInfoInAllAndOtherTabs() throws InterruptedException {
		SoftAssert softAssertion= new SoftAssert();
		
		driver=Utility.getBrowser();
		driver.get("http://www.google.com");
		driver.findElement(GooglePage.inputSearch).sendKeys("branch website");
		driver.findElement(GooglePage.btnGoogleSearch).click();
		driver.findElement(GooglePage.linkSearchResults).click();
		Utility.scrollToElement(driver, BranchHomePage.linkTeam);
		driver.findElement(BranchHomePage.linkTeam).click();		
		
		List<WebElement> depts=driver.findElements(TeamPage.tabDepts);
		ArrayList<Employee> allTabEmployeesList = new ArrayList<Employee>();		
		ArrayList<Employee> otherTabEmployeesList = new ArrayList<Employee>();
		
		for(WebElement dept :depts) {
			String categoryText=dept.getText();
			dept.click();			
			List<WebElement> employees=driver.findElements(TeamPage.divGenericTeamMemberInfo);			
			for(WebElement employee : employees) {
				Employee objEmployee=new Employee();
				if(employee.isDisplayed()) {
					objEmployee.setName(employee.findElement(By.xpath(".//h2")).getText());
					objEmployee.setDepartment(employee.findElement(By.xpath(".//h4")).getText());
					if(categoryText.equals("ALL")) {
						allTabEmployeesList.add(objEmployee);	
					}
					else {
						otherTabEmployeesList.add(objEmployee);
					}
				}
			}
		}		
		
		int allTabEmployeeCount=allTabEmployeesList.size();
		int otherTabEmployeeCount=otherTabEmployeesList.size();
		//Verify that number of employees match between All tab and sum of other tabs.
		Reporter.log("Employees count in 'All' tab is :="+allTabEmployeeCount);
		Reporter.log("Employees count in 'Other' department tabs is :="+otherTabEmployeeCount);
		if(allTabEmployeeCount==otherTabEmployeeCount) {
			Reporter.log("Emloyee count is matched in 'All' tab vs 'Other' tabs, allTabEmployeeCount:="+allTabEmployeeCount+" and otherTabEmployeeCount:="+otherTabEmployeeCount);		
		}
		else {
			Reporter.log("<font color='red'>"+"Emloyee count is not matched in 'All' tab vs 'Other' tabs, allTabEmployeeCount:="+allTabEmployeeCount+" and otherTabEmployeeCount:="+otherTabEmployeeCount+"</font>");
		}
		softAssertion.assertEquals(allTabEmployeeCount, otherTabEmployeeCount,"Verify that number of employees match between All tab and sum of other tabs.");
		
		//Verify that employee names match between All tab and other tabs
		//Verify that employee departments are listed correctly between All tab and Department tabs.
		 for(Employee allTabEmployee:allTabEmployeesList) {
			 boolean employeeFoundInOtherTabs=false;
			 for(Employee otherTabEmployee:otherTabEmployeesList) {
				 if(otherTabEmployee.equals(allTabEmployee)){
					 employeeFoundInOtherTabs=true;
					 break;
				 }
			 }
			 softAssertion.assertEquals(true,employeeFoundInOtherTabs,"Verify that employee names and departments are listed correctly between All tab and Department tabs for the Employee Name:="+allTabEmployee.getName()+" Department:="+allTabEmployee.getDepartment());
			 if(!employeeFoundInOtherTabs) {				 
				 Reporter.log("<font color='red'>"+"All tab Employee Name:="+allTabEmployee.getName()+" Department:="+allTabEmployee.getDepartment()+" is not found in other tabs."+"</font>");
			 }
			 else {
				 Reporter.log("<font color='green'>"+"All tab Employee Name:="+allTabEmployee.getName()+" Department:="+allTabEmployee.getDepartment()+" is found in other tabs."+"</font>");
			 }
		 }
		 
		 softAssertion.assertAll();
	}
	
	
	@Test(priority=2)	
	public void verifyViewOurCustomersButtonFunctionality() {
		SoftAssert softAssertion= new SoftAssert();		
		driver=Utility.getBrowser();
		driver.get("https://branch.io/");
		BranchHomePage.clickOnViewOurCustomersButton(driver);
		boolean flag=driver.findElement(PartnersPage.headerTextHappyPartners).isDisplayed();
		softAssertion.assertEquals(flag, true,"Verify 'Partners' page is displayed after clicking on 'View our Customers' button in Home page.");
		
		if(flag==true) {
			Reporter.log("<font color='green'>"+"'Partners' page is displayed after clicking on 'View our Customers' button in Home page."+"</font>");		
		}
		else {
			Reporter.log("<font color='red'>"+"'Request a Demo' page is NOT displayed after clicking on 'View our Customers' button in Home page."+"</font>");
		}
		
		softAssertion.assertAll();
	}
}
