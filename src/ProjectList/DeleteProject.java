package ProjectList;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class DeleteProject extends BaseTest {

	@Test(description = "Deleting the project", priority = 1)
	@Feature("Delete Project")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteProject() throws Exception {

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();

		if (text != true) {

			// If project list is not empty pick 1st record delete option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Delete\"])[1]")).click();
			driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();

			// Capturing the toast message for deleting the project..
			Thread.sleep(1000);
			WebElement toast_Message = driver.findElement(By.cssSelector(".Toastify__toast-body"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(toast_Message));
			String msg = toast_Message.getText();
			wait.until(ExpectedConditions.invisibilityOf(toast_Message));
			Assert.assertEquals(msg, "Project has been successfully deleted.");
		} else {
			System.out.println("Oops! Projects are not available in the list..");
		}
	}
}
