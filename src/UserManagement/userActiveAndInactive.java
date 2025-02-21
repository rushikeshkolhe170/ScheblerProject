package UserManagement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class userActiveAndInactive extends BaseTest {

	@Test(description = "Active and Inactive user's count", priority = 1)
	@Feature("Users Inactive and Active status")
	@Severity(SeverityLevel.CRITICAL)
	public void userStatusCount() {

		// Going into the User Management page..
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[4]")).click();

		// Finding the count of users with inactive status..
		int inactive_Users = driver.findElements(By.cssSelector(".css-csyk4v")).size();

		// Finding the count of users with active status..
		int active_Users = driver.findElements(By.cssSelector(".css-1giok29")).size();

		// Capturing Total count of users on User Management page..
		String userCountUserManagement = driver
				.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1avx9gw\"])[1]"))
				.getText();

		// Verifying the active and inactive user's total count
		Assert.assertEquals(userCountUserManagement, Integer.toString(inactive_Users + active_Users + 1));

		// Verifying the active user's count..
		String active = driver
				.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1avx9gw\"])[2]"))
				.getText();
		Assert.assertEquals(active, Integer.toString(active_Users + 1));
	}

	@Test(description = "Inactive user's status in user details", priority = 2)
	@Feature("Inactive user's status in user details")
	@Severity(SeverityLevel.CRITICAL)
	public void userStatusVerification() throws Exception {

		// Checking if the inactive users are available or not..
		int userStatusCheck = driver.findElements(By.cssSelector(".css-csyk4v")).size();

		if (userStatusCheck >= 1) {
			// Finding the first inactive user and capture it's class attribute..
			String class_User = driver
					.findElement(By.xpath("(//*[@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-csyk4v'])[1]"))
					.getAttribute("class");

			// Finding the first inactive user and click on it's name to open user details..
			driver.findElement(By.xpath(
					"(//*[@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-csyk4v']/parent::*/parent::*/child::div/child::div)[1]"))
					.click();

			// Capturing the class attribute of active/inactive icon status from user
			// details..
			String class_User_Details = driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-78fly5'])[1]/child::*[1]"))
					.getAttribute("class");

			// Verification of active/inactive status for first inactive user..
			Assert.assertEquals(class_User, class_User_Details);
		}
		else {
			System.out.println("Inactive user's are not available");
		}
	}
}
