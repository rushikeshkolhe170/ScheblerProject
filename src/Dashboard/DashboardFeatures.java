package Dashboard;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Pages.DashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class DashboardFeatures extends BaseTest {

	@Test(description = "Various calculation box on Dashboard", priority = 1)
	@Feature("Calculations boxes")
	@Severity(SeverityLevel.NORMAL)
	public void detailsOnDashboard() {

		// Capturing the title and count of first box on DashBoard..
		DashboardPage dp = new DashboardPage(driver);
		String text1 = dp.totalProjectText();
		boolean count1 = dp.totalProjectCounts();
		// Verifying the text is correct or not and count is display or not..
		Assert.assertEquals(text1, "Total Projects");
		Assert.assertEquals(count1, true);
		// Capturing the title and count of second box on DashBoard..
		String text2 = dp.totalPendingProjectText();
		boolean count2 = dp.totalPendingProjectCounts();
		// Verifying the text is correct or not..
		Assert.assertEquals(text2, "Total Pending Projects");
		Assert.assertEquals(count2, true);
		// Capturing the title and count of third box on DashBoard..
		String text3 = dp.totalReportText();
		boolean count3 = dp.totalReportCounts();
		// Verifying the text is correct or not..
		Assert.assertEquals(text3, "Total Reports");
		Assert.assertEquals(count3, true);
		// Capturing the title and count of fourth box on DashBoard..
		String text4 = dp.totalUserText();
		boolean count4 = dp.totalUserCounts();
		// Verifying the text is correct or not..
		Assert.assertEquals(text4, "User Management");
		Assert.assertEquals(count4, true);
	}

	@Test(description = "Toggle, Profile and Signout button", priority = 2)
	@Feature("Toggle, Profile and Signout button")
	@Severity(SeverityLevel.NORMAL)
	public void featureButton() throws Exception {

		DashboardPage dp = new DashboardPage(driver);
		// Checking which theme is currently on..
		dp.themeCheck();
		// Changing the theme..
		dp.themeChange();
		// Checking which theme is apply after click..
		dp.themeCheck();
		// Logout from the site..
		dp.logout();
	}
}
