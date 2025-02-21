package UserManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class userSearch extends BaseTest {

	@Test(description = "Searching the users in user's list", priority = 1)
	@Feature("User searching")
	@Severity(SeverityLevel.NORMAL)
	public void userSearching() throws Exception {

		// Going into the User Management page..
		driver.findElement(By.xpath("(//p[@class=\"MuiTypography-root MuiTypography-body1 css-1ws9804\"])[4]")).click();

		// Searching the keyword in the search box..
		String keyword = "ru";
		driver.findElement(By.xpath("//input[@placeholder=\"Search…\"]")).sendKeys(keyword);
		Thread.sleep(2000);

		// Checking whether the users list is display or not..
		boolean data;
		try {
			driver.findElement(By.cssSelector("p.css-17yooox")).isDisplayed();
			data = true;
		} catch (NoSuchElementException e) {
			data = false;
		}
		if (data == true) {
			
			// Storing all the search appearances in the list..
			List<WebElement> username = driver.findElements(By.cssSelector("p.css-17yooox"));

			// Checking every element and capturing it's text..
			for (WebElement name : username) {
				String user = name.getText();

				// Converting capture text and search text to lower case because contains method
				// is case sensitive..
				if (user.toLowerCase().contains(keyword.toLowerCase())) {
					// If search text available in the user's name..
					System.out.println(user);
				} else {
					// If search text is not available in the user's name..
					System.out.println(user + " should not be in the list with this search appearance...");
				}
			}
		} else {
			System.out.println("Data is not available...");
		}
	}
}
