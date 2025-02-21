package Runs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.projectPresence;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(Listener.listener.class)

public class ModelList extends BaseTest {

	@Test(description = "Verifying models in Aerco manufacturer", priority = 1)
	@Feature("Models verification in Aerco manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListAerco() throws Exception {

		String aercoModels[] = { "AM 399", "AM 500", "AM 750", "AM1000", "BMK-750", "BMK-1000", "BMK-1500", "BMK-2000",
				"BMK-2500", "BMK-3000", "BMK-4000", "BMK-5000N", "BMK-5000", "BMK-6000", "Innovation 600",
				"Innovation 800", "Innovation 1060", "Innovation 1350", "Multi-Fuel Condensing 3000",
				"Multi-Fuel Condensing 4000", "Multi-Fuel Condensing 5000", "Multi-Fuel Condensing 6000",
				"Multi-Fuel Condensing 8000", "Multi-Fuel Condensing 10000", "Modulex-303", "Modulex-454",
				"Modulex-606", "Modulex-757", "Modulex-909", "Modulex-1060", "Modulex-EXT-321", "Modulex-EXT-481",
				"Modulex-EXT-641", "Modulex-EXT-802", "Modulex-EXT-962", "Modulex-EXT-1123", "Modulex-EXT-1500/1530",
				"Modulex-EXT-1912", "Modulex-EXT-2295/2300", "Modulex-EXT-2600/2677", "Modulex-EXT-3000/3060" };
		ArrayList<String> aercoList = new ArrayList<String>(Arrays.asList(aercoModels));

		// Opening Run calculation page..
		driver.findElement(By.xpath("(//button[@class=\"MuiButtonBase-root css-1xke27f\"])[2]")).click();

		// Checking if the project list is empty..
		projectPresence pp = new projectPresence();
		boolean text = pp.projectAvailability();

		if (text != true) {

			// If project list is not empty pick 1st record Edit option..
			driver.findElement(By.xpath("(//img[@aria-label=\"Edit\"])[1]")).click();

			// Going onto runs page..
			driver.findElement(By.xpath("(//button[contains(@class,'css-1f9rmc3')])[2]")).click();

			// Opening add new runs page..
			driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

			// Opening manufacturer DropDown..
			driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

			// Selecting all the manufacturer names and adding them in the list..
			List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
			ArrayList<String> modelNames = new ArrayList<String>();

			Boolean status = true;
			// Skipping 1st element and start from 1st index..
			for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
				String m = manufacturer.getAttribute("data-value");

				// Selecting only the Aerco manufacture..
				if (m.equalsIgnoreCase("Aerco")) {
					manufacturer.click();
					Thread.sleep(2000);

					// Capturing all the models in the Aerco manufacturer..
					driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
					Thread.sleep(2000);
					List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
					for (WebElement mod : models.subList(1, models.size())) {
						String name = mod.getText();
						modelNames.add(name);
					}
					status = false;
				}
				if (status == false) {
					break;
				}
			}

			// Verifying the capture models are same as that of above mention models list..
			aercoList.retainAll(modelNames);
			Assert.assertEquals(aercoList.size(), modelNames.size());
			driver.navigate().refresh();
		}
	}

	@Test(description = "Verifying models in Camus manufacturer", priority = 2, dependsOnMethods = "modelListAerco")
	@Feature("Models verification in Camus manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListCamus() throws Exception {

		Thread.sleep(2000);
		String camusModels[] = {"Dynaforce DR(H)-0300", "Dynaforce DR(H)-0350", "Dynaforce DR(H)-0400", "Dynaforce DR(H)-0500", "Dynaforce DR(H)-0600",
				"Dynaforce DR(H)-0800", "Dynaforce DR(H)-1000", "Dynaforce DR(H)-1200", "Dynaforce DR(H)-1400", "Dynaforce DR(H)-1600", "Dynaforce DR(H)-1800",
				"Dynaforce DR(H)-2000", "Dynaforce DR(H)-2500", "Dynaforce DR(H)-3000", "Dynaforce DR(H)-3500", "Dynaforce DR(H)-4000",
				"Dynaforce DR(H)-4500", "Dynaforce DR(H)-5000", "Avenger ARN-1000", "Avenger ARN-1500", "Avenger ARN-2000", "Avenger ARN-2500",
				"Avenger ARN-3000", "Avenger ARN-3500", "Avenger ARN-4000", "Avenger ARP-1000", "Avenger ARP-1500", "Avenger ARP-2000",
				"Avenger ARP-2500", "Avenger ARP-3000", "Avenger ARP-3500", "Avenger ARP-4000"};
		ArrayList<String> camusList = new ArrayList<String>(Arrays.asList(camusModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Camus manufacture..
			if (m.equalsIgnoreCase("Camus")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Camus manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		camusList.retainAll(modelNames);
		if (camusList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (camusList.size() > modelNames.size()) {
			System.out.println("List is missing few camus models..");
		} else {
			System.out.println("List is modify by adding few camus models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Cleaver Brooks manufacturer", priority = 3, dependsOnMethods = "modelListCamus")
	@Feature("Models verification in Cleaver Brooks manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListCleaverBrooks() throws Exception {

		Thread.sleep(2000);
		String cleaverbrooksModels[] = {"CFLC-4000", "CFLC-5000", "CFLC-6000", "CFLC-8000", "CFLC-10000", "CFLC-12000", "CFCE-500", "CFCE-750", "CFCE-1000",
				"CFCE-1500", "CFCE-2000", "CFCE-3500", "CFCE-4000", "CFCE-5000", "CFCE-6000", "CBLE-125", "CBLE-150", "CBLE-200", "CBLE-250", "CBLE-300",
				"CBLE-350", "CBLE-400", "CBLE-500", "CBLE-600", "CBLE-700", "CBLE-800", "CBEX-100", "CBEX-125", "CBEX-150", "CBEX-200", "CBEX-250",
				"CBEX-300", "CBEX-350", "CBEX-400", "CBEX-500", "CBEX-600", "CBEX-700", "CBEX-800", "CBEX-900", "CBEX-1000", "CBEX-1100", "CBEX-1200", "CFCH-70"};
		ArrayList<String> cleaverbrooksList = new ArrayList<String>(Arrays.asList(cleaverbrooksModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Cleaver Brooks manufacture..
			if (m.equalsIgnoreCase("Cleaver Brooks")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Cleaver Brooks manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		cleaverbrooksList.retainAll(modelNames);
		if (cleaverbrooksList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (cleaverbrooksList.size() > modelNames.size()) {
			System.out.println("List is missing few cleaverbrooks models..");
		} else {
			System.out.println("List is modify by adding few cleaverbrooks models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Fulton manufacturer", priority = 3, dependsOnMethods = "modelListCamus")
	@Feature("Models verification in Fulton manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListFulton() throws Exception {

		Thread.sleep(2000);
		String fultonModels[] = {"EDR-750", "EDR-1000", "EDR-1500", "EDR-2000", "EDR+2500", "EDR+3000", "EDR+6000", "FTC - 0080", "PHW-0750", "PHW-1000",
				"PHW-2000", "VTG-2000", "VTG-3000", "VTG-4000", "VTG-5000", "VTG-6000", "VMP-40", "VMP-49.5", "VMP-50", "VMP-60", "VMP-80", "VMP-100",
				"VMP-130", "VMP-150", "VSRT-10", "VSRT-15", "VSRT-20", "VSRT-30", "VSRT-40", "VSRT-50", "VSRT-60", "VSRT-9.5", "VSRT-80.", "VSRT-100",
				"VSRT-125", "VSRT-250"};
		ArrayList<String> fultonList = new ArrayList<String>(Arrays.asList(fultonModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Fulton manufacture..
			if (m.equalsIgnoreCase("Fulton")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Fulton manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		fultonList.retainAll(modelNames);
		if (fultonList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (fultonList.size() > modelNames.size()) {
			System.out.println("List is missing few fulton models..");
		} else {
			System.out.println("List is modify by adding few fulton models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Laars manufacturer", priority = 4, dependsOnMethods = "modelListFulton")
	@Feature("Models verification in Laars manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListLaars() throws Exception {

		Thread.sleep(2000);
		String laarsModels[] = {"Pennant 500", "Pennant 750", "Pennant 1000", "Pennant 1250", "Pennant 1500", "Pennant 1750", "Pennant PNCH 2000",
				"Pennant PNCV 2000", "MagnaTherm MGH 1600", "MagnaTherm MGH 2000", "MagnaTherm MGH 2500", "MagnaTherm MGH 3000", "MagnaTherm MGH 3500",
				"MagnaTherm MGH 4000", "MagnaTherm MGV 1600", "MagnaTherm MGV 2000", "MagnaTherm MGV 2500", "MagnaTherm MGV 3000", "MagnaTherm MGV 3500",
				"MagnaTherm MGV 4000"};
		ArrayList<String> laarsList = new ArrayList<String>(Arrays.asList(laarsModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Laars manufacture..
			if (m.equalsIgnoreCase("Laars")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Laars manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		laarsList.retainAll(modelNames);
		if (laarsList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (laarsList.size() > modelNames.size()) {
			System.out.println("List is missing few laars models..");
		} else {
			System.out.println("List is modify by adding few laars models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Lochinvar manufacturer", priority = 5, dependsOnMethods = "modelListLaars")
	@Feature("Models verification in Lochinvar manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListLochinvar() throws Exception {

		Thread.sleep(2000);
		String lochinvarModels[] = {"AWH-1250", "AWH-1500", "AWH-2000", "AWH-3000", "AWH-4000", "Crest FBN-0751", "Crest FBN-1001", "Crest FBN-1251",
				"Crest FBN-1501", "Crest FBN-1751", "Crest FBN-2001", "Crest FBN-2500", "Crest FBN-3000", "Crest FBN-3500", "Crest FBN-4000",
				"Crest FBN-5000", "Crest FBN-6000", "FTX400(N,L)", "FTX500(N,L)", "FTX600N,L)", "FTX725(N,L)", "FTX850(N,L)"};
		ArrayList<String> lochinvarList = new ArrayList<String>(Arrays.asList(lochinvarModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Lochinvar manufacture..
			if (m.equalsIgnoreCase("Lochinvar")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Lochinvar manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		lochinvarList.retainAll(modelNames);
		if (lochinvarList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (lochinvarList.size() > modelNames.size()) {
			System.out.println("List is missing few lochinvar models..");
		} else {
			System.out.println("List is modify by adding few lochinvar models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Patterson-Kelley manufacturer", priority = 6, dependsOnMethods = "modelListLochinvar")
	@Feature("Models verification in Patterson-Kelley manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListPattersonKelley() throws Exception {

		Thread.sleep(2000);
		String pattersonkelleyModels[] = {"Mach CM-300", "Mach CM-399", "Mach CM-500", "Mach C-300", "Mach C-450", "Mach C-750", "Mach C-900", "Mach C-1050",
				"Mach C-1500", "Mach C-1500H", "Mach C-2000", "Mach C-2000H", "Mach C-2500", "Mach C-3000", "Mach C-4000", "MFD-750", "MFD-1000",
				"MFD-1500", "MFD-2000", "MFD-2500", "MFD-3000", "Sonic SC-650", "Sonic SC-650GG", "Sonic SC-750", "Sonic SC-750GG", "Sonic SC-850",
				"Sonic SC-850GG", "Sonic SC-1000", "Sonic SC-1000GG", "Storm ST1250", "Storm ST1500", "Storm ST1750", "Storm ST2000", "Storm ST2500",
				"Storm ST3000", "Storm ST3500", "Storm ST4000", "Storm ST5000"};
		ArrayList<String> pattersonkelleyList = new ArrayList<String>(Arrays.asList(pattersonkelleyModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Patterson-Kelley manufacture..
			if (m.equalsIgnoreCase("Patterson-Kelley")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Patterson-Kelley manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		pattersonkelleyList.retainAll(modelNames);
		if (pattersonkelleyList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (pattersonkelleyList.size() > modelNames.size()) {
			System.out.println("List is missing few pattersonkelley models..");
		} else {
			System.out.println("List is modify by adding few pattersonkelley models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in PVI manufacturer", priority = 7, dependsOnMethods = "modelListPattersonKelley")
	@Feature("Models verification in PVI manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListPVI() throws Exception {

		Thread.sleep(2000);
		String pviModels[] = {"Tricon 50 L 300A-PVIF", "Tricon 75 L 300A-PVIF", "Tricon 100 SL 300A-PVIF", "Tricon 150 L 300A-PVIF", "Tricon 180 L 300A-PVIF",
				"Tricon 200 L 300A-PVIF", "Tricon 150 L 250A-PVIF", "Tricon 180 L 250A-PVIF", "Tricon 200 L 250A-PVIF", "Conquest 20 L 100A-GCL",
				"Conquest 25 L 100A-GCL", "Conquest 30 L 100A-GCL", "Conquest 40 L 130A-GCML", "Conquest 50 L 130A-GCML", "Conquest 60 L 130A-GCML",
				"Conquest 70 L 130A-GCML", "Conquest 80 L 130A-GCML", "Conquest 90 L 130A-GCML", "Conquest 100 L 130A-GCML"};
		ArrayList<String> pviList = new ArrayList<String>(Arrays.asList(pviModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the PVI manufacture..
			if (m.equalsIgnoreCase("PVI")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the PVI manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		pviList.retainAll(modelNames);
		if (pviList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (pviList.size() > modelNames.size()) {
			System.out.println("List is missing few pvi models..");
		} else {
			System.out.println("List is modify by adding few pvi models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in RBI manufacturer", priority = 8, dependsOnMethods = "modelListPVI")
	@Feature("Models verification in RBI manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListRBI() throws Exception {

		Thread.sleep(2000);
		String rbiModels[] = {"FlexCore CK-850", "FlexCore CK-1000", "FlexCore CK-1500", "FlexCore CK-2000", "FlexCore CK-2500", "FlexCore CK-3000",
				"FlexCore CK-3500", "FlexCore CK-4000", "FlexCore CK-4500", "FlexCore CK-5000", "FlexCore CK-6000"};
		ArrayList<String> rbiList = new ArrayList<String>(Arrays.asList(rbiModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the RBI manufacture..
			if (m.equalsIgnoreCase("RBI")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the RBI manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		rbiList.retainAll(modelNames);
		if (rbiList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (rbiList.size() > modelNames.size()) {
			System.out.println("List is missing few rbi models..");
		} else {
			System.out.println("List is modify by adding few rbi models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Riello manufacturer", priority = 9, dependsOnMethods = "modelListRBI")
	@Feature("Models verification in Riello manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListRiello() throws Exception {

		Thread.sleep(2000);
		String rielloModels[] = {"Array AR-1000", "Array AR-1500", "Array AR-2000", "Array AR-3000", "Array AR-4000", "RTC 3000", "RTC 4000", "RTC 5000",
				"RTC 6000", "RTC 8000", "RTC 10000"};
		ArrayList<String> rielloList = new ArrayList<String>(Arrays.asList(rielloModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Riello manufacture..
			if (m.equalsIgnoreCase("Riello")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Riello manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		rielloList.retainAll(modelNames);
		if (rielloList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (rielloList.size() > modelNames.size()) {
			System.out.println("List is missing few riello models..");
		} else {
			System.out.println("List is modify by adding few riello models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Advanced thermal Dynamics manufacturer", priority = 10, dependsOnMethods = "modelListRiello")
	@Feature("Models verification in Advanced thermal Dynamics manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListAdvancedthermalDynamics() throws Exception {

		Thread.sleep(2000);
		String atdModels[] = {"KN-26"};
		ArrayList<String> atdList = new ArrayList<String>(Arrays.asList(atdModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Advanced thermal Dynamics manufacture..
			if (m.equalsIgnoreCase("Advanced thermal Dynamics")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced thermal Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		atdList.retainAll(modelNames);
		if (atdList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (atdList.size() > modelNames.size()) {
			System.out.println("List is missing few atd models..");
		} else {
			System.out.println("List is modify by adding few atd models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Bryan manufacturer", priority = 11, dependsOnMethods = "modelListAdvancedthermalDynamics")
	@Feature("Models verification in Bryan manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListBryan() throws Exception {

		Thread.sleep(2000);
		String bryanModels[] = {"AB90", "AB120", "AB150", "AB200", "AB250", "AB300", "CL/CLM120", "CL/CLM150", "CL/CLM180", "CL/CLM210", "CL/CLM240",
				"CL/CLM270", "CL/CLM300", "D", "DR250", "DR350", "DR450", "DR650", "DR850", "EB-75", "EB-100", "EB-125", "EB-150", "EB-175", "EB-200",
				"EB-240", "HE-AB90", "HE-AB120", "HE-AB150", "HE-AB200", "HE-AB250", "HE-AB300", "HE-CLM90", "HE-CLM120", "HE-CLM150", "HE-CLM180",
				"HE-CLM210", "HE-CLM240", "HE-CLM270", "HE-CLM300", "HED", "HE-RV350", "HE-RV400", "HE-RV450", "HE-RV500", "HE-RV550", "HE-RV600",
				"HE-RV700", "HE-RV800", "RV350", "RV400", "RV450", "RV500", "RV550", "RV600", "RV700", "RV800", "RW", "BFIT-1000", "BFIT-1250",
				"BFIT-1500", "BFIT-2000", "BFIT-2500", "BFIT-3000", "BFIT-3500", "BFIT-4000", "BFITW-1000", "BFITW-1250", "BFITW-1500", "BFITW-2000",
				"BFITW-2500", "BFITW-3000", "BFITW-3500", "BFITW-4000"};
		ArrayList<String> bryanList = new ArrayList<String>(Arrays.asList(bryanModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Bryan manufacture..
			if (m.equalsIgnoreCase("Bryan")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Bryan Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		bryanList.retainAll(modelNames);
		if (bryanList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (bryanList.size() > modelNames.size()) {
			System.out.println("List is missing few bryan models..");
		} else {
			System.out.println("List is modify by adding few bryan models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in De Dietrich manufacturer", priority = 12, dependsOnMethods = "modelListBryan")
	@Feature("Models verification in De Dietrich manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListDeDietrich() throws Exception {

		Thread.sleep(2000);
		String dedietrichModels[] = {"GT 334A", "GT335A", "GT336A", "GT337A", "GT338A", "GT339A", "GT 430-8A", "GT 430-9A", "GT 430-10A", "GT 430-11A",
				"GT 430-12A", "GT 430-13A", "GT 430-14A", "GT 530-15A", "GT 530-16A", "GT 530-17A", "GT 530-18A", "GT 530-19A", "GT 530-20A", "GT 530-21A",
				"GT 530-22A", "GT 530-23A", "GT 530-24A", "GT 530-25A", "GT 530-26AE", "GT 530-27AE", "GT 530-28AE", "GT 530-29AE", "GT 530-30AE",
				"GT 530-31AE", "GT 530-32AE"};
		ArrayList<String> dedietrichList = new ArrayList<String>(Arrays.asList(dedietrichModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the De Dietrich manufacture..
			if (m.equalsIgnoreCase("De Dietrich")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced De Dietrich Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		dedietrichList.retainAll(modelNames);
		if (dedietrichList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (dedietrichList.size() > modelNames.size()) {
			System.out.println("List is missing few dedietrich models..");
		} else {
			System.out.println("List is modify by adding few dedietrich models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Hurst manufacturer", priority = 13, dependsOnMethods = "modelListDeDietrich")
	@Feature("Models verification in Hurst manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListHurst() throws Exception {

		Thread.sleep(2000);
		String hurstModels[] = {"4VTHW"};
		ArrayList<String> hurstList = new ArrayList<String>(Arrays.asList(hurstModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Hurst manufacture..
			if (m.equalsIgnoreCase("Hurst")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Hurst Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		hurstList.retainAll(modelNames);
		if (hurstList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (hurstList.size() > modelNames.size()) {
			System.out.println("List is missing few hurst models..");
		} else {
			System.out.println("List is modify by adding few hurst models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Kohler genset manufacturer", priority = 14, dependsOnMethods = "modelListHurst")
	@Feature("Models verification in Kohler genset manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListKohlergenset() throws Exception {

		Thread.sleep(2000);
		String kohlergensetModels[] = {"KD2500"};
		ArrayList<String> kohlergensetList = new ArrayList<String>(Arrays.asList(kohlergensetModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Kohler genset manufacture..
			if (m.equalsIgnoreCase("Kohler genset")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Kohler genset Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		kohlergensetList.retainAll(modelNames);
		if (kohlergensetList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (kohlergensetList.size() > modelNames.size()) {
			System.out.println("List is missing few kohlergenset models..");
		} else {
			System.out.println("List is modify by adding few kohlergenset models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Miura manufacturer", priority = 15, dependsOnMethods = "modelListKohlergenset")
	@Feature("Models verification in Miura manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListMiura() throws Exception {

		Thread.sleep(2000);
		String miuraModels[] = {"LX-250SG-16"};
		ArrayList<String> miuraList = new ArrayList<String>(Arrays.asList(miuraModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Miura manufacture..
			if (m.equalsIgnoreCase("Miura")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Miura Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		miuraList.retainAll(modelNames);
		if (miuraList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (miuraList.size() > modelNames.size()) {
			System.out.println("List is missing few miura models..");
		} else {
			System.out.println("List is modify by adding few miura models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Raypack manufacturer", priority = 16, dependsOnMethods = "modelListMiura")
	@Feature("Models verification in Raypack manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListRaypack() throws Exception {

		Thread.sleep(2000);
		String raypackModels[] = {"2007 Xvers"};
		ArrayList<String> raypackList = new ArrayList<String>(Arrays.asList(raypackModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Raypack manufacture..
			if (m.equalsIgnoreCase("Raypack")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Raypack Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		raypackList.retainAll(modelNames);
		if (raypackList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (raypackList.size() > modelNames.size()) {
			System.out.println("List is missing few raypack models..");
		} else {
			System.out.println("List is modify by adding few raypack models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Viessmann manufacturer", priority = 17, dependsOnMethods = "modelListRaypack")
	@Feature("Models verification in Viessmann manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListViessmann() throws Exception {

		Thread.sleep(2000);
		String viessmannModels[] = {"Vitocrossal 200, CM2-186", "Vitocrossal 200, CM2-246", "Vitocrossal 200, CM2-311", "Vitocrossal 200, CM2-400",
				"Vitocrossal 200, CM2-500", "Vitocrossal 200, CM2-620", "Vitocrossal 200, CM2-620TX", "Vitocrossal 300, CA3-2.5", "Vitocrossal 300, CA3-3.0",
				"Vitocrossal 300, CA3-3.5", "Vitocrossal 300, CA3-4.0", "Vitocrossal 300, CA3-5.0", "Vitocrossal 300, CA3-6.0"};
		ArrayList<String> viessmannList = new ArrayList<String>(Arrays.asList(viessmannModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Viessmann manufacture..
			if (m.equalsIgnoreCase("Viessmann")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Viessmann Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		viessmannList.retainAll(modelNames);
		if (viessmannList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (viessmannList.size() > modelNames.size()) {
			System.out.println("List is missing few viessmann models..");
		} else {
			System.out.println("List is modify by adding few viessmann models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Weil McClain manufacturer", priority = 19, dependsOnMethods = "modelListViessmann")
	@Feature("Models verification in Weil McClain manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListWeilMcClain() throws Exception {

		Thread.sleep(2000);
		String weilmcClainModels[] = {"Weil-McLain 988", "Weil-McLain LGB-12-W"};
		ArrayList<String> weilmcClainList = new ArrayList<String>(Arrays.asList(weilmcClainModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Weil McClain manufacture..
			if (m.equalsIgnoreCase("Weil McClain")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Weil McClain Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		weilmcClainList.retainAll(modelNames);
		if (weilmcClainList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (weilmcClainList.size() > modelNames.size()) {
			System.out.println("List is missing few weilmcClain models..");
		} else {
			System.out.println("List is modify by adding few weilmcClain models..");
		}
		driver.navigate().refresh();
	}
	
	@Test(description = "Verifying models in Generator manufacturer", priority = 20, dependsOnMethods = "modelListWeilMcClain")
	@Feature("Models verification in Generator manufacturer")
	@Severity(SeverityLevel.NORMAL)
	public void modelListGenerator() throws Exception {

		Thread.sleep(2000);
		String generatorModels[] = {"Caterpillar Model C32 1250kW"};
		ArrayList<String> generatorList = new ArrayList<String>(Arrays.asList(generatorModels));

		// Opening add new runs page..
		driver.findElement(By.xpath("//p[text()=\"Add New Runs\"]")).click();

		// Opening manufacturer DropDown..
		driver.findElement(By.xpath("(//p[text()=\"Manufacturer\"])[1]")).click();

		// Selecting all the manufacturer names and adding them in the list..
		List<WebElement> manufacturers = driver.findElements(By.cssSelector(".css-1km1ehz"));
		ArrayList<String> modelNames = new ArrayList<String>();
		Boolean status = true;

		// Skipping 1st element and start from 1st index..
		for (WebElement manufacturer : manufacturers.subList(1, manufacturers.size())) {
			String m = manufacturer.getAttribute("data-value");

			// Selecting only the Generator manufacture..
			if (m.equalsIgnoreCase("Generator")) {
				manufacturer.click();
				Thread.sleep(2000);

				// Capturing all the models in the Advanced Generator Dynamics manufacturer..
				driver.findElement(By.xpath("(//div[contains(@class,'css-1cccqvr')])[3]")).click();
				Thread.sleep(2000);
				List<WebElement> models = driver.findElements(By.cssSelector(".css-1km1ehz"));
				for (WebElement mod : models.subList(1, models.size())) {
					String name = mod.getText();
					modelNames.add(name);
				}
				status = false;
			}
			if (status == false) {
				break;
			}
		}

		// Verifying the capture models are same as that of above mention models list..
		generatorList.retainAll(modelNames);
		if (generatorList.size() == modelNames.size()) {
			System.out.println("List is correctly match..");
		} else if (generatorList.size() > modelNames.size()) {
			System.out.println("List is missing few generator models..");
		} else {
			System.out.println("List is modify by adding few generator models..");
		}
		driver.navigate().refresh();
	}
}
