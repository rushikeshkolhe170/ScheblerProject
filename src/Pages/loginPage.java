package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseTest.BaseTest;

public class loginPage extends BaseTest{

	
	public loginPage(WebDriver driver) {
		
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//input[@id=\"outlined-start-adornment\"])[1]")
	private WebElement username;
	
	@FindBy(xpath = "(//input[@id=\"outlined-start-adornment\"])[2]")
	private WebElement password;
	
	@FindBy(xpath = "//button[text()=\"Login\"]")
	private WebElement login;
	
	public void login(String user, String pass) {
		
		username.sendKeys(user);
		password.sendKeys(pass);
		login.click();
	}
}
