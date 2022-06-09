package addToCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoveToCart 
{
	WebDriver driver;
	By SearchProduct = By.xpath("//body/section[1]/div[2]/div[1]/div[3]/div[1]/form[1]/div[1]/input[1]");
	By SearchButton = By.xpath("//button[contains(text(),'Search')]");
	By ProductID = By.xpath("//a[contains(text(),'K9-BD-01')]");
	By AddToCart = By.xpath("//tbody/tr[2]/td[5]/a[1]");
	By Quantity = By.xpath("//tbody/tr[2]/td[5]/input[1]");
	By UpdateButton = By.xpath("//button[contains(text(),'Update Cart')]");
	By RemoveAll = By.xpath("//a[contains(text(),'Remove All')]");

	//constructor
	public MoveToCart(WebDriver driver) {
		this.driver=driver;	
	}

	//Search for Product
	public void AddToCart(String input) {
		driver.findElement(SearchProduct).sendKeys(input);
		driver.findElement(SearchButton).click();
		driver.findElement(ProductID).click();
		driver.findElement(AddToCart).click();

	}

	//update quantity
	public void Update_Quantity(String quantity) {
		driver.findElement(Quantity).clear();
		driver.findElement(Quantity).sendKeys(quantity);
	}

	//click on update
	public void Click_on_Update() {
		driver.findElement(UpdateButton).click();
	}

	//click on remove
	public void Remove_All() {
		driver.findElement(RemoveAll).click();
	}	
}

