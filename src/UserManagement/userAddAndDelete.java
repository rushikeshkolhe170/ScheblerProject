package UserManagement;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class userAddAndDelete extends BaseTest {

	@Test(description =  "User addition in the schebler site", priority = 1)
	@Feature("New user addition")
	@Severity(SeverityLevel.CRITICAL)
	public void addUsers() throws Exception {
		
		//Going into the User Management page..
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[4]")).click();
		
		//Opening Add User panel..
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ey4h9j\"])[2]")).click();
		
		//Filling in the details for the new user..
		driver.findElement(By.xpath("//input[@placeholder=\"Full Name\"]")).sendKeys("Rajendra Limaye");
		driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).sendKeys("rajendra123@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("welcome");
		driver.findElement(By.xpath("//input[@placeholder=\"Confirm Password\"]")).sendKeys("welcome");
		driver.findElement(By.xpath("//input[@placeholder=\"Phone No.\"]")).sendKeys("9865329865");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
		
		//Verifying the toast message for user creation..
		WebElement toast_message = driver.findElement(By.xpath("//div[text()=\"User Added Successfully\"]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toast_message));
		String msg = toast_message.getText();
		Assert.assertEquals(msg, "User Added Successfully");
		
		//Capturing the name of newly added user..
		Thread.sleep(2000);
		String newUserName = driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-17yooox\"])[last()]")).getText();
		
		//Verifying if the created user is the same..
		Assert.assertEquals(newUserName, "Rajendra Limaye");
	}
	
	@Test(description =  "User deletion from the schebler site", priority = 2)
	@Feature("User deletion")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteUser() throws Exception {
		
		//Finding the newly created user and deleting him..
		Thread.sleep(2000);
		String user = driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-17yooox\"])[1]")).getText();
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-17yooox\"])[1]")).click();
		driver.findElement(By.xpath("//*[@data-testid=\"DeleteIcon\"]")).click();
		driver.findElement(By.xpath("//button[text()=\"Yes\"]")).click();
		
		//Verification of Toast message for successful user deletion..
		WebElement toast_Message = driver.findElement(By.xpath("//div[text()=\"User has been successfully deleted.\"]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
		wait.until(ExpectedConditions.visibilityOf(toast_Message));
		String msg = toast_Message.getText();
		Assert.assertEquals(msg, "User has been successfully deleted.");
		System.out.println(user + " is deleted");
	}
}
