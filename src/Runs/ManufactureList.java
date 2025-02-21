package Runs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class ManufactureList extends BaseTest {

	String names[] = { "Aerco", "Camus", "Cleaver Brooks", "Fulton", "Laars", "Lochinvar", "Patterson-Kelley", "PVI",
			"RBI", "Riello", "Advanced thermal Dynamics", "Bryan", "De Dietrich", "Hurst", "Kohler genset", "Miura",
			"Raypack", "Viessmann", "Weil McClain", "Generator" };
	ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(names));

	@Test(description = "Verifying the Manufacturer list", priority = 1)
	@Feature("Manufacturer list verification")
	@Severity(SeverityLevel.NORMAL)
	public void manufactureLIst() throws Exception {

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();

		if (text != true) {

			// If project list is not empty pick 1st record view option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Edit\"])[1]")).click();

			// Going onto runs page..
			driver.findElement(By.xpath("(//button[contains(@class,'css-1f9rmc3')])[2]")).click();

			// Opening add new runs page..
			driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

			// Opening manufacturer DropDown..
			driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

			// Capturing all the manufacturer names and adding them in the list..
			ArrayList<String> manufacturerNames = new ArrayList<String>();
			List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
			for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
				String name = manufacturer.getText();
				manufacturerNames.add(name);
			}

			// Verifying if the capture list has the above mention 20 manufactures in it or
			// not..
			nameList.retainAll(manufacturerNames);

			// Verification of required manufacturer list and capture manufacturer list
			// size..
			if (nameList.size() == manufacturerNames.size()) {
				System.out.println("List is correctly match..");
			} else if (nameList.size() > manufacturerNames.size()) {
				System.out.println("List is missing few manufacturer..");
			} else {
				System.out.println("List is modify by adding few manufacturer..");
			}
		}
	}
}
