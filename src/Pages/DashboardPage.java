package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseTest.BaseTest;

public class DashboardPage extends BaseTest {

	public DashboardPage(WebDriver driver) {

		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@aria-label=\"Profile\"]")
	private WebElement profile;

	@FindBy(xpath = "//li[text()=\"Change Password\"]")
	private WebElement changePassword;

	@FindBy(xpath = "//input[@placeholder=\"Old Password\"]")
	private WebElement oldPassword;

	@FindBy(xpath = "//input[@placeholder=\"New Password\"]")
	private WebElement newPassword;

	@FindBy(xpath = "//input[@placeholder=\"Confirm Password\"]")
	private WebElement confirmPassword;

	@FindBy(xpath = "//button[text()=\"Change\"]")
	private WebElement changePass;

	@FindBy(xpath = "//button[@aria-label=\"Logout\"]")
	private WebElement logout;

	@FindBy(xpath = "//button[text()=\"Yes\"]")
	private WebElement yesconfirmationPopUp;

	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[1]")
	private WebElement totalProject;

	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-3nzzhj\"])[1]")
	private WebElement totalProjectCount;

	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[2]")
	private WebElement tPendingProject;
	
	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-3nzzhj\"])[2]")
	private WebElement tPendingProjectCount;
	
	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[3]")
	private WebElement totalReport;
	
	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-3nzzhj\"])[3]")
	private WebElement tReportCount;
	
	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[4]")
	private WebElement totalUsers;
	
	@FindBy(xpath = "(//p[@class=\"MuiTypography-root MuiTypography-body1 css-3nzzhj\"])[4]")
	private WebElement tUsersCount;
	
	@FindBy(xpath = "(//span[contains(@class,\"Mui-checked\")])[2]")
	private WebElement darkTheme;
	
	@FindBy(xpath = "(//span[@class=\"MuiSwitch-track css-1ju1kxc\"])[2]")
	private WebElement themeToggle;

	public void openProfile() {

		profile.click();
	}

	public void changePassword(String oldPass, String newPass) throws Exception {

		changePassword.click();
		Thread.sleep(3000);
		oldPassword.sendKeys(oldPass);
		newPassword.sendKeys(newPass);
		confirmPassword.sendKeys(newPass);
		changePass.click();
	}
	
	public void themeChange() {
		
		Actions a = new Actions(driver);
		a.click(themeToggle).perform();
	}

	public String totalProjectText() {

		String text = totalProject.getText();
		return text;
	}

	public String totalPendingProjectText() {

		String text = tPendingProject.getText();
		return text;
	}
	
	public String totalReportText() {

		String text = totalReport.getText();
		return text;
	}
	
	public String totalUserText() {

		String text = totalUsers.getText();
		return text;
	}

	public boolean totalProjectCounts() {

		boolean pc = totalProjectCount.isDisplayed();
		return pc;
	}
	
	public boolean totalPendingProjectCounts() {

		boolean ppc = tPendingProjectCount.isDisplayed();
		return ppc;
	}
	
	public boolean totalReportCounts() {

		boolean rc = tReportCount.isDisplayed();
		return rc;
	}
	
	public boolean totalUserCounts() {

		boolean rc = tUsersCount.isDisplayed();
		return rc;
	}
	
	public boolean themeCheck() {

		boolean theme;
		try {
			darkTheme.isDisplayed();
			theme = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			theme = false;
		}
		if (theme == true) {
			System.out.println("Currently the theme is DARK");
		} else {
			System.out.println("Currently the theme is WHITE");
		}
		return theme;
	}

	public void logout() {

		logout.click();
		yesconfirmationPopUp.click();
	}

}
