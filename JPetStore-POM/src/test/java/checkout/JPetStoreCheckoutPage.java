package checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JPetStoreCheckoutPage {
	WebDriver driver;

	//Constructor
	public JPetStoreCheckoutPage(WebDriver driver) {
		this.driver=driver;
	}


	//Storing web elements
	By continu=By.xpath("//button[contains(text(),'Continue')]");

	//Click on continue
	public void clickContinue() {
		driver.findElement(continu).click();
	}
}
