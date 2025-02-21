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
import BaseTest.runsPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class MakeRunCopy extends BaseTest {

	String runName = "OG Run";
	String copyRun = "Copy Run";
	String OGop, OGop1, OGloss, OGloss1, OGmanu, OGmodel;
	String Copyop, Copyop1, Copyloss, Copyloss1, Copymanu, Copymodel;

	@Test(description = "Creating run to be copy", priority = 1)
	@Feature("Creating run to be copy")
	@Severity(SeverityLevel.CRITICAL)
	public void creatingRun() throws Exception {

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

			// Opening add new runs page..
			driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

			runsPresence rp = new runsPresence();
			boolean presence = rp.runsAvailability();

			if (presence != true) {

				// Adding run name..
				driver.findElement(By.xpath("//input[@placeholder=\"Run Name\"]")).sendKeys(runName);

				// Opening manufacturer DropDown..
				Thread.sleep(1000);
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
				JavascriptExecutor jse = (JavascriptExecutor) driver;
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

				// Capturing manufacturer and model name..
				OGmanu = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-1xhj18k\"])[2]")).getText();
				OGmodel = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-1xhj18k\"])[3]")).getText();

				// Click on High Fire..
				driver.findElement(By.xpath("//div[text()=\"Set High Fire Conditions\"]")).click();
				Thread.sleep(1000);

				// Capturing few element in High Fire to verify them later..
				OGop = driver
						.findElement(By.xpath(
								"(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
						.getAttribute("value");
				OGloss = driver
						.findElement(By.xpath(
								"(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
						.getAttribute("value");

				// Click on Low Fire..
				driver.findElement(By.xpath("//div[text()=\"Set Low Fire Conditions\"]")).click();
				Thread.sleep(1000);

				// Capturing few element in High Fire to verify them later..
				OGop1 = driver
						.findElement(By.xpath(
								"(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
						.getAttribute("value");
				OGloss1 = driver
						.findElement(By.xpath(
								"(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
						.getAttribute("value");

				// Click on Save..
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[text()=\"Save\"]")).click();

				// Waiting for toast message to appear and verify it..
				WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOf(toast_Message));
				String msg = toast_Message.getText();
				wait.until(ExpectedConditions.invisibilityOf(toast_Message));
				Assert.assertEquals(msg, "Run Created Successfully");
				System.out.println(OGloss + " " + OGloss1 + " " + OGmanu + " " + OGmodel + " " + OGop + " " + OGop1);
			}
			else {
				System.out.println("Run is already available..");
			}
		}
	}

	@Test(description = "Making copy of the Run", priority = 2, dependsOnMethods = "creatingRun")
	@Feature("Making Copy of existing Run")
	@Severity(SeverityLevel.CRITICAL)
	public void makeCopyofRun() throws Exception {

		boolean pageNext = false, pageNext1 = false;
		int k = 0, p = 0;
		int index = 0;

		// Using while loop in case if there are more than 1 page in the run list..
		while (pageNext == false || k == 1) {

			// Capturing the list of the run names..
			Thread.sleep(1000);
			List<WebElement> runnames = driver.findElements(By.xpath("//tr/td[2]/div[2]"));

			// Clicking on the make copy option for the created run..
			for (WebElement run : runnames) {
				String runstat = run.getText();
				index++;
				if (runstat.contains(runName)) {

					// Clicking on the make copy option for the created run..
					driver.findElement(
							By.xpath("(//tr/td[2]/div[2]/parent::*/parent::*/td[6]/div[2]/button[3])[" + index + "]"))
							.click();
					String runname1 = driver.findElement(By.xpath("//input[@placeholder=\"Run Name\"]"))
							.getAttribute("value");
					if (!runname1.isEmpty()) {
						throw new Exception("Run name is not empty");
					} else {
						driver.findElement(By.xpath("//input[@placeholder=\"Run Name\"]")).sendKeys(copyRun);
						Thread.sleep(2000);
					}

					// capturing manufacturer and model names..
					Copymanu = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-1xhj18k\"])[2]")).getText();
					Copymodel = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-1xhj18k\"])[3]"))
							.getText();

					// Click on High Fire..
					driver.findElement(By.xpath("//div[text()=\"Set High Fire Conditions\"]")).click();
					Thread.sleep(1000);

					// Capturing few element in High Fire to verify them later..
					Copyop = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
							.getAttribute("value");
					Copyloss = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
							.getAttribute("value");

					// Click on Low Fire..
					driver.findElement(By.xpath("//div[text()=\"Set Low Fire Conditions\"]")).click();
					Thread.sleep(1000);

					// Capturing few element in High Fire to verify them later..
					Copyop1 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
							.getAttribute("value");
					Copyloss1 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
							.getAttribute("value");

					// Click on Save..
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[text()=\"Save\"]")).click();

					// Waiting for toast message to appear and verify it..
					WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					wait.until(ExpectedConditions.visibilityOf(toast_Message));
					String msg = toast_Message.getText();
					wait.until(ExpectedConditions.invisibilityOf(toast_Message));
					Assert.assertEquals(msg, "Run Created Successfully");
					break;
				}
			}
			Thread.sleep(1000);
			try {
				// Click on the next button in the table..
				driver.findElement(By.xpath("//button[@data-testid=\"pagination-next\"]")).click();
				index = 0;
			} catch (ElementClickInterceptedException e) {
				break;
			}
			Thread.sleep(1000);
			try {

				// Checking if the table has page to click on next..
				driver.findElement(By
						.xpath("//div/button[2][@data-testid=\"pagination-next\" and contains(@class,'Mui-disabled')]"))
						.isDisplayed();
				pageNext = true;
				k = k + 1;
			} catch (NoSuchElementException e) {
				pageNext = false;
			}
		}

		// Using while loop in case if there are more than 1 page in the run list..
		while (pageNext1 == false || p == 1) {

			// Verifying if the copy run is available in the list or not..
			Thread.sleep(2000);
			List<WebElement> runs = driver.findElements(By.xpath("//tr/td[2]/div[2]"));
			for (WebElement r : runs) {
				String runname = r.getText();
				if (runname.equals(copyRun)) {
					System.out.println("Copy run is in the list..");
					break;
				}
			}
			Thread.sleep(1000);
			try {
				// Click on the next button in the table..
				driver.findElement(By.xpath("//button[@data-testid=\"pagination-next\"]")).click();
			} catch (ElementClickInterceptedException e) {
				break;
			}
			Thread.sleep(1000);
			try {

				// Checking if the table has page to click on next..
				driver.findElement(By
						.xpath("//div/button[2][@data-testid=\"pagination-next\" and contains(@class,'Mui-disabled')]"))
						.isDisplayed();
				pageNext1 = true;
				p = p + 1;
			} catch (NoSuchElementException e) {
				pageNext1 = false;
			}
		}
		System.out
				.println(Copyloss + " " + Copyloss1 + " " + Copymanu + " " + Copymodel + " " + Copyop + " " + Copyop1);
	}

	@Test(description = "Verification of OG and Copy run data", priority = 3, dependsOnMethods = "makeCopyofRun")
	@Feature("Data verification in OG and Copy")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyData() {

		// Verifying Manufacturer and model name..
		Assert.assertEquals(Copymanu, OGmanu);
		Assert.assertEquals(Copymodel, OGmodel);

		// Verifying the data in high fire..
		Assert.assertEquals(Copyop, OGop);
		Assert.assertEquals(Copyloss, OGloss);

		// Verifying the data in low fire..
		Assert.assertEquals(Copyop1, OGop1);
		Assert.assertEquals(Copyloss1, OGloss1);
	}
}
