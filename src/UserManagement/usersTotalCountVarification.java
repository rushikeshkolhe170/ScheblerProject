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

public class usersTotalCountVarification extends BaseTest{

	@Test(description = "Total number of current users")
	@Feature("User count on every page should be same")
	@Severity(SeverityLevel.NORMAL)
	public void currentUsersCount() throws Exception {
		
		//Text capturing from User management box on DashBoard page..
		Thread.sleep(2000);
		String userCountDashboard = driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-3nzzhj\"])[4]")).getText();
		String text = driver.findElement(By.xpath("//p[@class=\"MuiTypography-root MuiTypography-body1 css-1to4ed0\"]")).getText();
		
		//Verifying the text inside the box..
		Assert.assertEquals(text, "Total No. of Current User");
		
		//Going into the User Management page..
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[4]")).click();
		Thread.sleep(2000);
		
		//Capturing Total count of users on User Management page..
		String userCountUserManagement = driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1avx9gw\"])[1]")).getText();
		
		//Verifying the DashBoard display count is equal to Total User count from User Management..
		Assert.assertEquals(userCountDashboard, userCountUserManagement);
		
		//Capturing Total count of users from User's list..
		int userList = driver.findElements(By.xpath("//div[@class=\"user-card MuiBox-root css-0\"]")).size();
		
		//Verifying the DashBoard display count is equal to Total User count from User's list..
		Assert.assertEquals(userCountDashboard,Integer.toString(userList));
	}
}
