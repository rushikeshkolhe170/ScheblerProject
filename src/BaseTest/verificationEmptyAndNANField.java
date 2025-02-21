package BaseTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class verificationEmptyAndNANField extends BaseTest {

	String invalid = "NaN";
	
	public void dataAvailability() throws Exception {

		Thread.sleep(2000);
		// Verifying if Run name field has input in it or not..
		String runName = driver.findElement(By.xpath("//input[@placeholder=\"Run Name\"]")).getAttribute("value");
		if (runName.isEmpty()) {
			throw new Exception("Run name is empty it does not get fill by the script");
		}

		// Verifying if any field is empty in Appliance data panel..
		List<WebElement> applianceData = driver
				.findElements(By.xpath("//div[@class=\"MuiBox-root css-saxq3k\"]/div/div[2]/div/input"));
		int i = 1;
		for (WebElement data : applianceData) {
			String fieldData = data.getAttribute("value");
			if (fieldData.isEmpty()) {
				throw new Exception("Data is not available for field number " + i + " in Appliance data panel");
			} else {
				i++;
			}
		}

		// Verifying if any field is empty in Report data panel..
		List<WebElement> reportData = driver
				.findElements(By.xpath("//div[@class=\"MuiBox-root css-1w4qmsq\"]/div/div/div[2]/div/input"));
		int j = 1;
		for (WebElement data1 : reportData) {
			String fieldData1 = data1.getAttribute("value");
			if (fieldData1.isEmpty()) {
				throw new Exception("Data is not available for field number " + j + " in Report data panel");
			} else {
				j++;
			}
		}

		// Verifying if any field is empty in Cautionary Deviation panel..
		List<WebElement> cautionaryDeviation = driver
				.findElements(By.xpath("(//div[@class=\"MuiBox-root css-97vxn5\"])[1]/div/div/div/input"));
		int k = 1;
		for (WebElement data2 : cautionaryDeviation) {
			String fieldData2 = data2.getAttribute("value");
			if (fieldData2.isEmpty()) {
				throw new Exception(
						"Data is not available for field number " + k + " in Cautionary Deviation panel");
			} else {
				k++;
			}
		}

		// Verifying if any field is empty in Ambient Temperature Range panel..
		List<WebElement> ambientTempRange = driver
				.findElements(By.xpath("(//div[@class=\"MuiBox-root css-97vxn5\"])[2]/div/div/div/input"));
		int l = 1;
		for (WebElement data3 : ambientTempRange) {
			String fieldData3 = data3.getAttribute("value");
			if (fieldData3.isEmpty()) {
				throw new Exception(
						"Data is not available for field number " + l + " in Ambient Temperature Range panel");
			} else {
				l++;
			}
		}

		// Verifying if any field is empty in Recommended Diameter(Run identifier) Range
		// panel..
		List<WebElement> recommendedDiameterRun = driver.findElements(
				By.xpath("//div[@class=\"MuiFormControl-root MuiTextField-root css-1gpcqis\"]/div/input"));
		int m = 1;
		for (WebElement data4 : recommendedDiameterRun) {
			String fieldData4 = data4.getAttribute("value");
			if (fieldData4.isEmpty()) {
				throw new Exception("Data is not available for field number " + m
						+ " in Recommended Diameter(Run identifier) panel");
			} else {
				m++;
			}
		}

		// Verifying if any field is empty in Recommended Diameter(Appliance) Range
		// panel..
		List<WebElement> recommendedDiameterApp = driver.findElements(
				By.xpath("//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input"));
		int n = 1;
		for (WebElement data5 : recommendedDiameterApp) {
			String fieldData5 = data5.getAttribute("value");
			if (fieldData5.isEmpty()) {
				throw new Exception("Data is not available for field number " + n
						+ " in Recommended Diameter(Appliance) panel");
			} else {
				n++;
			}
		}

		// Verifying if first 5 fields are empty in Duct Pressure Loss
		// panel..
		List<WebElement> ductPressureLoss = driver
				.findElements(By.xpath("//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input"));
		int o = 1;
		for (WebElement data6 : ductPressureLoss) {
			String fieldData6 = data6.getAttribute("value");
			if (fieldData6.isEmpty()) {
				throw new Exception("Data is not available for field number " + o
						+ " in Recommended Diameter(Appliance) panel");
			} else {
				o++;
			}
			if (o == 6) {
				break;
			}
		}
		
		// Checking if any field has NaN string..
		List<WebElement> nanData = driver.findElements(By.cssSelector(".css-1x5jdmq"));
		for(WebElement data7 : nanData) {
			String fielddata7 = data7.getAttribute("value");
			if(fielddata7.equals(invalid)) {
				throw new Exception("There is a field in calculation which shows NAN");
			}
		}
	}
}
