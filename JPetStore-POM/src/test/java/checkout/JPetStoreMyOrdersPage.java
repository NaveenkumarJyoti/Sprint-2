package checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class JPetStoreMyOrdersPage {
	WebDriver driver;
	
	//Constructor
	public JPetStoreMyOrdersPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Storing web elements
	By message=By.xpath("//p[contains(text(),'Thank you, your order has been submitted.')]");
	
	//Validate order placed message
	public void validateMessage() {
		Assert.assertTrue(driver.findElement(message).isDisplayed());
		System.out.println("Message : "+driver.findElement(message).getText());
	}
}
