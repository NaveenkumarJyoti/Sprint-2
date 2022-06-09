package search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForProductId {

	WebDriver driver;
	By ProductId = By.linkText("AV-CB-01");

	public SearchForProductId(WebDriver driver) {
		this.driver = driver;
	}

	// click on
	public void Categories() {
		driver.findElement(ProductId).click();
	}

	public void SearchProductId(String strProductId){
		//Click birds
		this.Categories();
	}
}
