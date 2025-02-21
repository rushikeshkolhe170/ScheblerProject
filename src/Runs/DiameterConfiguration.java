package Runs;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import BaseTest.runsPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DiameterConfiguration extends BaseTest {

	double HTotal_loss, LTotal_loss;
	double HOutlet_pressure, LOutlet_pressure;
	
	@Test(description = "Calculating Loss and Outlet pressure in one project", priority = 1)
	@Feature("Calculating Loss and Outlet pressure in one project")
	@Severity(SeverityLevel.CRITICAL)
	public void LossandOutletOverallTotal() throws Exception {

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();
		
		if (text != true) {

			// If project list is not empty pick 1st record edit option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Edit\"])[1]")).click();
			Thread.sleep(1000);

			// Going onto runs page..
			driver.findElement(By.xpath("(//button[contains(@class,'css-1f9rmc3')])[2]")).click();

			// Checking if run list is empty or not..
			runsPresence rp = new runsPresence();
			boolean text1 = rp.runsAvailability();
			
			if (text1 != true) {

				double Hloss = 0, Lloss = 0, Hot = 0, Lot = 0;

				// Opening every run one by one and capturing the loss and outlet pressure
				// calculation..
				boolean pagenext = true;
				int k = 0;
				int i = 1;
				while (pagenext == true || k == 1) {
					try {
					driver.findElement(
							By.xpath("(//div[@class=\"tss-1qtl85h-MUIDataTableBodyCell-root\"]/button[1])[" + i + "]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[text()=\"Set High Fire Conditions\"]")).click();  // High fire set
					String loss1 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
							.getAttribute("value");
					Hloss = Hloss + Double.parseDouble(loss1);
					String ot1 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
							.getAttribute("value");
					Hot = Hot + Double.parseDouble(ot1);
					driver.findElement(By.xpath("//div[text()=\"Set Low Fire Conditions\"]")).click();  // Low fire set
					String loss2 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
							.getAttribute("value");
					Lloss = Lloss + Double.parseDouble(loss2);
					String ot2 = driver.findElement(By
							.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
							.getAttribute("value");
					Lot = Lot + Double.parseDouble(ot2);
					driver.findElement(By.xpath("//div[text()=\"Cancel\"]")).click();
					i++;
					}
					catch(NoSuchElementException e) {
						break;
					}
					Thread.sleep(1000);
					try {
						// Click on the next button in the table..
						if (i == 6) {
							driver.findElement(By.xpath("//button[@data-testid=\"pagination-next\"]")).click();
							i = 1;
						}
					} catch (ElementClickInterceptedException e) {
			
					}
					Thread.sleep(1000);
					try {

						// Checking if the table has page to click on next..
						if(i == 6) {
						driver.findElement(By.xpath(
								"//div/button[2][@data-testid=\"pagination-next\" and contains(@class,'Mui-disabled')]"))
								.isDisplayed();
						pagenext = false;
						k = k + 1;
						}
					} catch (NoSuchElementException e) {
						pagenext = true;
						i = 1;
					}
				}
				HTotal_loss = Hloss;
				LTotal_loss = Lloss;
				HOutlet_pressure = Hot;
				LOutlet_pressure = Lot;
				System.out.println(HTotal_loss+" "+LTotal_loss+" "+HOutlet_pressure+" "+LOutlet_pressure );
			}
		}
	}
	
	@Test(description = "Verifying the values in Diameter Configuration page", priority = 2, dependsOnMethods = "LossandOutletOverallTotal")
	@Feature("Verifying the values in Diameter Configuration page")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyDiameterConfigurationCal() {
		
		// Opening Diameter configuration page..
		driver.findElement(By.xpath("(//button[contains(@class,'css-1f9rmc3')])[3]")).click();
		
		// Clicking on the High fire condition radio button..
		driver.findElement(By.xpath("//input[@value=\"high\"]")).click();
		
		// Capturing the Total loss and Outlet pressure from appliance in diameter configuration..
		String a = driver.findElement(By.xpath("(//tbody[@class=\"MuiTableBody-root css-1xnox0e\"])[2]/tr[1]/td[2]/div")).getText();
		String AHTotal_loss = a.substring(0, 5);
		String b = driver.findElement(By.xpath("(//tbody[@class=\"MuiTableBody-root css-1xnox0e\"])[2]/tr[1]/td[4]/div")).getText();
		String AHOutlet_pressure = b.substring(0, 5);
		
		// Clicking on the High fire condition radio button..
		driver.findElement(By.xpath("//input[@value=\"low\"]")).click();
				
		// Capturing the Total loss and Outlet pressure from appliance in diameter configuration..
		String c = driver.findElement(By.xpath("(//tbody[@class=\"MuiTableBody-root css-1xnox0e\"])[2]/tr[1]/td[2]/div")).getText();
		String ALTotal_loss = c.substring(0, 5);
		String d = driver.findElement(By.xpath("(//tbody[@class=\"MuiTableBody-root css-1xnox0e\"])[2]/tr[1]/td[4]/div")).getText();
		String ALOutlet_pressure = d.substring(0, 5);
		
		// Converting previous calculation to string to get only first 5 characters..
		String conHloss = Double.toString(HTotal_loss);
		String data1 = conHloss.substring(0, 5);
		String conLloss = Double.toString(LTotal_loss);
		String data2 = conLloss.substring(0, 5);
		String conHpressure = Double.toString(HOutlet_pressure);
		String data3 = conHpressure.substring(0, 5);
		String conLpressure = Double.toString(LOutlet_pressure);
		String data4 = conLpressure.substring(0, 5);
		
		// Verification..
		Assert.assertEquals(Double.parseDouble(AHTotal_loss), Double.parseDouble(data1));
		Assert.assertEquals(Double.parseDouble(AHOutlet_pressure), Double.parseDouble(data3));
		Assert.assertEquals(Double.parseDouble(ALTotal_loss), Double.parseDouble(data2));
		Assert.assertEquals(Double.parseDouble(ALOutlet_pressure), Double.parseDouble(data4));
	}
}
