package Dashboard;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Pages.DashboardPage;
import Pages.loginPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class ChangePassword extends BaseTest{

	@Test(description =  "Changing password of the user", priority = 1)
	@Feature("User password Change")
	@Severity(SeverityLevel.CRITICAL)
	public void changePassword() throws Exception {
		
		String oldpass = "Rushi@7738";
		String newPass = "Test@123";
		
		// Opening options from the profile icon..
		DashboardPage dp = new DashboardPage(driver);
		dp.openProfile();
		dp.changePassword(oldpass, newPass);
		String msg = dp.toastMessage();
		//Verifying the toast message for successful password change..
		Assert.assertEquals(msg, "Password changed successfully");
		dp.logout();
		Thread.sleep(2000);
		loginPage lp = new loginPage(driver);
		lp.login("rkolhe@gmail.com", newPass);
		// Toast message capturing..
		Thread.sleep(1000);
		String msg1 = lp.toastAfterLogin();
		// Verifying the toast message for successful password change..
		Assert.assertEquals(msg1, "Login Successful");
		dp.openProfile();
		String oldpass1 = newPass;
		String newPass1 = oldpass;
		dp.changePassword(oldpass1, newPass1);
		String msg3 = dp.toastMessage();
		//Verifying the toast message for successful password change..
		Assert.assertEquals(msg3, "Password changed successfully");
		dp.logout();
		lp.login("rkolhe@gmail.com", newPass1);
		String msg2 = lp.toastAfterLogin();
		//Verifying the toast message for successful password change..
		Assert.assertEquals(msg2, "Login Successful");
	}
}
