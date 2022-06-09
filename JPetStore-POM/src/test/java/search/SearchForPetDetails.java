package search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForPetDetails 
{
	WebDriver driver;
	By ItemId = By.linkText("EST-18");

	public SearchForPetDetails(WebDriver driver)
	{
		this.driver = driver;
	}

	//click on item id
	public void Item_Id() 
	{
		driver.findElement(ItemId).click();
	}

	public void Pet_Details()
	{
		driver.findElement(By.className("Amazon Parrot"));
	}

	public void SearchPetDetails(String strItemId){
		//Click birds
		this.Item_Id();
		this.Pet_Details();
	}
}