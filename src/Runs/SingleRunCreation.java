package Runs;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import BaseTest.verificationEmptyAndNANField;
import BaseTest.verificationSignleRunData;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class SingleRunCreation extends BaseTest {

	String runName = "Automation Testing";

	@Test(description = "Creating new Signle Run", priority = 1)
	@Feature("New Single run creation")
	@Severity(SeverityLevel.CRITICAL)
	public void createRun() throws Exception {

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();

		if (text != true) {

			// If project list is not empty pick 1st record Edit option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Edit\"])[1]")).click();

			// Going onto runs page..
			driver.findElement(By.xpath("(//button[contains(@class,'css-1f9rmc3')])[2]")).click();

			// Checking the Manufacturer and model name..
			//String info = driver.findElement(By.xpath("//td[2]/div[@class='tss-1qtl85h-MUIDataTableBodyCell-root']")).getText();
						
			// Opening add new runs page..
			driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

			// Adding run name..
			driver.findElement(By.xpath("//input[@placeholder=\"Run Name\"]")).sendKeys(runName);

			// Opening manufacturer DropDown..
			driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();
			
			// Selecting random Manufacturer..
			List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
			List<WebElement> data = manufacturers.subList(1, manufacturers.size());
			if (data == null || data.isEmpty()) {
	            throw new Exception("Manuafacturer List is empty");
	        }
			Random rand = new Random();
			int randomIndex = rand.nextInt(data.size());
			WebElement randomElement = data.get(randomIndex);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true)", randomElement);
			randomElement.click();
			Thread.sleep(1000);
			
			// Opening models for the random manufacturer..
			driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
			
			// Selecting random Model for the manufacturer..
			List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
			List<WebElement> data1 = models.subList(1, models.size());
			if (data1 == null || data1.isEmpty()) {
	            throw new Exception("Model List is empty");
	        }
			int randomIndex1 = rand.nextInt(data1.size());
			WebElement randomElement1 = data1.get(randomIndex1);
			jse.executeScript("arguments[0].scrollIntoView(true)", randomElement1);
			randomElement1.click();
			Thread.sleep(1000);

			// Calling method to verify empty field and field which contains NAN..
			verificationEmptyAndNANField venf = new verificationEmptyAndNANField();
			venf.dataAvailability();

			// Calling method to verify if the fields has equal values as they should be..
			verificationSignleRunData vsrd = new verificationSignleRunData();
			vsrd.verification();

			// Click on High Fire..
			driver.findElement(By.xpath("//div[text()=\"Set High Fire Conditions\"]")).click();
			
			// Click on Low Fire..
			driver.findElement(By.xpath("//div[text()=\"Set Low Fire Conditions\"]")).click();
			
			// Click on Save..
			driver.findElement(By.xpath("//div[text()=\"Save\"]")).click();
			
			// Waiting for toast message to appear and verify it..
			WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(toast_Message));
			String msg = toast_Message.getText();
			wait.until(ExpectedConditions.invisibilityOf(toast_Message));
			Assert.assertEquals(msg, "Run Created Successfully");
		}
	}
	
	@Test(description = "Deleting the created run", priority = 2, dependsOnMethods = "createRun")
	@Feature("Deleting the Run")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteRun() throws Exception {
		
		boolean pageNext = false;
		int k = 0;
		int i =0;

		// Using while loop in case if there are more than 1 page in the run list..
		while (pageNext == false || k == 1) {
			List<WebElement> runs = driver.findElements(By.xpath("(//tbody[@class=\"MuiTableBody-root css-1xnox0e\"])/tr/td[2]/div[2]"));
			for(WebElement run : runs) {
				String runName1 = run.getText();
				i++;
				
				// Checking if newly created run is in the list or not
				if(runName1.startsWith(runName)) {
					
					// Selecting the delete option for the chosen run..
					driver.findElement(By.xpath("(//img[@aria-label=\"Delete\"])["+i+"]")).click();
					driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
					
					// Waiting for toast message to appear and verify it..
					WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					wait.until(ExpectedConditions.visibilityOf(toast_Message));
					String msg = toast_Message.getText();
					wait.until(ExpectedConditions.invisibilityOf(toast_Message));
					Assert.assertEquals(msg, "Run has been successfully deleted.");
					break;
				}
			}
			Thread.sleep(2000);
			try {

				// Click on the next button in the table..
				driver.findElement(By.xpath("//button[@data-testid=\"pagination-next\"]")).click();
				i = 0;
			} catch (ElementClickInterceptedException e) {
				break;
			} catch (NoSuchElementException e1) {
				
				// Handling exception if no runs are available in the list..
				boolean info = driver.findElement(By.xpath("//p[text()=\"No Runs Added\"]")).isDisplayed();
				if(info == true) {
					System.out.println("No runs available");
					break;
				} else {
					throw new Exception("Exception in run list");
				}
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
	}
}
