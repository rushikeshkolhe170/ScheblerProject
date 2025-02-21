package ProjectList;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class AddSingleProject extends BaseTest {

	@Test(description = "Adding the new project", priority = 1)
	@Feature("Add Project")
	@Severity(SeverityLevel.CRITICAL)
	public void addProject() throws Exception {

		// Opening Run calculation page..
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Click on new project calculation to create new project..
		driver.findElement(By.xpath("//button[text()=\"New Project Calculation\"]")).click();

		// Adding details to project details overview..
		String pro_No = "PO-Auto-01";
		driver.findElement(By.xpath("//input[@placeholder=\"Project Number\"]")).sendKeys(pro_No);
		driver.findElement(By.xpath("//input[@placeholder=\"Project Name\"]")).sendKeys("Automation-project");
		driver.findElement(By.xpath("//input[@placeholder=\"Revision\"]")).sendKeys("1");
		driver.findElement(By.cssSelector(".css-1cccqvr")).click();
		driver.findElement(By.xpath("//p[text()=\"Single\"]")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Prepared For\"]")).sendKeys("Automation testing");
		driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[6]")).sendKeys("Rushikesh Kolhe");
		driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();

		// Verifying the toast message..
		Thread.sleep(1000);
		WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toast_Message));
		String msg = toast_Message.getText();
		wait.until(ExpectedConditions.invisibilityOf(toast_Message));
		Assert.assertEquals(msg, "Project Added Successfully");
		
		driver.findElement(By.xpath("//img[@alt= 'Run Calculations']")).click();

		boolean pageNext = false;
		int k = 0;

		// List creation to store all the project numbers from the table..
		List<String> project_Numbers = new ArrayList<String>();
		while (pageNext == false || k == 1) {

			// Adding all the project numbers from table in the WebElement list..
			List<WebElement> projects = driver.findElements(By.xpath("//tr/td[2]"));
			for (WebElement pro : projects) {
				project_Numbers.add(pro.getText());
			}
			Thread.sleep(2000);
			try {

				// Click on the next button in the table..
				driver.findElement(By.xpath("//button[@data-testid=\"pagination-next\"]")).click();
			} catch (ElementClickInterceptedException e) {
				break;
			}
			Thread.sleep(2000);
			try {

				// Checking if the table has page to click on next..
				driver.findElement(By.xpath("//div/button[2][@data-testid=\"pagination-next\" and contains(@class,'Mui-disabled')]")).isDisplayed();
				pageNext = true;
				k = k + 1;
			} catch (NoSuchElementException e) {
				pageNext = false;
			}
		}

		// Verifying if newly added project is available in the list or not..
		if(project_Numbers.contains(pro_No)) {
			System.out.println("Congrats! Your project is in the list");
		}
		else {
			throw new Exception("Project is not available in the list");
		}
		// Verifying if newly added project is available in the list or not..
	}
}