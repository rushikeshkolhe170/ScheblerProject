package BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class projectPresence extends BaseTest {

	public boolean projectAvailability() throws Exception {

		// Checking if the project list is empty..
		boolean text;
		try {
			driver.findElement(By.xpath("//p[text()=\"No Project Added\"]")).isDisplayed();
			text = true;
			throw new Exception("Project is not available");
		} catch (NoSuchElementException e) {
			text = false;
		}
		return text;
	}

	public String projectType() {

		// Checking the project type..
		String projectType = driver.findElement(By.cssSelector(".css-16zhpft")).getText();
		return projectType;
	}
}
