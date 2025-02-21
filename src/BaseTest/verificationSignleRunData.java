package BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;

public class verificationSignleRunData extends BaseTest {

	public void verification() throws Exception {
		
		// Cross verification for equal fields..
		
		// Volume flow rate
		Thread.sleep(2000);
		String vfr = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input)[2]"))
				.getAttribute("value");  // Duct
		System.out.println("vfr: "+ vfr);
		String vfr1 = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-saxq3k\"]/div/div[2]/div/input)[7]"))
				.getAttribute("value");  // Appliance
		System.out.println("vfr1: "+ vfr1);
		String vfr2 = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-1w4qmsq\"]/div/div/div[2]/div/input)[4]"))
				.getAttribute("value");  // Report
		System.out.println("vfr2: "+ vfr2);
		Assert.assertEquals(vfr, vfr1);
		Assert.assertEquals(vfr1, vfr2);
		
		// Gas Temperature
		String gt = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input)[3]"))
				.getAttribute("value");  // Duct
		System.out.println("gt: "+ gt);
		String gt1 = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-saxq3k\"]/div/div[2]/div/input)[8]"))
				.getAttribute("value");  // Appliance
		System.out.println("gt1: "+ gt1);
		Assert.assertEquals(gt, gt1);
		
		// Diameter
		String di = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input)[6]"))
				.getAttribute("value");  // Duct
		System.out.println("di: "+ di);
		String di1 = driver.findElement(By.xpath("(//div[@class=\"MuiBox-root css-saxq3k\"]/div/div[2]/div/input)[5]"))
				.getAttribute("value");  // Appliance
		System.out.println("di1: "+ di1);
		String di2 = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-1gpcqis\"]/div/input)[1]"))
				.getAttribute("value");  // Recommended diameter
		System.out.println("di2: "+ di2);
		Assert.assertEquals(di, di1);
		Assert.assertEquals(di1, di2);
		
		// Velocity
		String vel = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input)[12]"))
				.getAttribute("value");  // Duct
		System.out.println("vel: "+ vel);
		String vel1 = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-1gpcqis\"]/div/input)[2]"))
				.getAttribute("value");  // Recommended diameter
		System.out.println("vel1: "+ vel1);
		Assert.assertEquals(vel, vel1); 
		
		// Loss
		String loss = driver
				.findElement(
						By.xpath("(//input[@class=\"MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq\"])[43]"))
				.getAttribute("value");  // Duct
		System.out.println("loss: "+ loss);
		String loss1 = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-1gpcqis\"]/div/input)[3]"))
				.getAttribute("value");  // Recommended diameter
		System.out.println("loss1: "+ loss1);
		String loss2 = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[1]"))
				.getAttribute("value");  // Recommended diameter appliance
		System.out.println("loss2: "+ loss2);
		Assert.assertEquals(loss, loss1);
		Assert.assertEquals(loss1, loss2);
		
		// Total Draft
		String tdk = driver
				.findElement(By.xpath("(//div[@class=\"MuiBox-root css-m47ygh\"]/div/div/div[2]/div/input)[25]"))
				.getAttribute("value");  // Duct
		String td = tdk.substring(0,5);
		System.out.println("td: "+ td);
		String td1k = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[2]"))
				.getAttribute("value");  // Recommended diameter appliance
		String td1 = td1k.substring(0,5);
		System.out.println("td1: "+ td1);
		Assert.assertEquals(td, td1);
		
		// Outlet pressure
		String op1 = driver
				.findElement(
						By.xpath("(//div[@class=\"MuiFormControl-root MuiTextField-root css-af722y\"]/div/input)[3]"))
				.getAttribute("value");  // Recommended diameter appliance
		String OP = op1.substring(0,5);
		System.out.println("OP: "+ OP);
		double count = Double.parseDouble(loss2) - Double.parseDouble(tdk);
		String OP1 = (Double.toString(count)).substring(0,5);
		System.out.println("OP1: "+ OP1);
		Assert.assertEquals(OP, OP1);
	}
}
