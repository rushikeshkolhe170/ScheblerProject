package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {

	public WebDriver driver;
	
	@FindBy(css = ".Toastify__toast-body")
	private WebElement toastonPassChange;
	
	@FindBy(css = "div.Toastify__toast-body")
	private WebElement toastonLogin;
	
	public String toastMessage() {
		
		WebElement toast_Message = toastonPassChange;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toast_Message));
		String msg = toast_Message.getText();
		wait.until(ExpectedConditions.invisibilityOf(toast_Message));
		return msg;
	}
	
	public String toastAfterLogin() {
		
		WebElement toast_Message1 = toastonLogin;
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.visibilityOf(toast_Message1));
		String msg1 = toast_Message1.getText();
		wait1.until(ExpectedConditions.invisibilityOf(toast_Message1));
		return msg1;
	}
}
