package ProjectList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class EditSingleProject extends BaseTest {

	@Test(description = "Editing the project", priority = 1)
	@Feature("Edit Project")
	@Severity(SeverityLevel.CRITICAL)
	public void editProject() throws Exception {

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();

		if (text != true) {

			// If project list is not empty pick 1st record edit option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Edit\"])[1]")).click();
			Thread.sleep(1000);

			// Project number..
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[1]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[1]")).sendKeys("PO-Up-Auto-01");

			// Project Name..
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[2]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[2]")).sendKeys("Automation-Update");

			// Prepared for..
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[4]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[4]"))
					.sendKeys("Automation prepared");

			// User..
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[6]"))
					.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[contains(@class,'css-1ixds2g')])[6]"))
					.sendKeys("Rushikesh Kolhe update");

			// Saving the edited changes..
			driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
		}
	}
}
