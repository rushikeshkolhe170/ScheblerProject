package BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class runsPresence extends BaseTest {

	public boolean runsAvailability() throws Exception {

		// Checking if run list is empty or not..
		boolean text1;
		try {
			driver.findElement(By.xpath("//p[text()=\"No Runs Added\"]")).isDisplayed();
			text1 = true;
			throw new Exception("No runs are available in the project");
		} catch (NoSuchElementException e) {
			text1 = false;
		}
		return text1;
	}
}
