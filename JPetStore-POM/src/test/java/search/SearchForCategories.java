package search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchForCategories {

	WebDriver driver;
	By Petcategory = By.linkText("Birds");

	public SearchForCategories(WebDriver driver)
	{
		this.driver = driver;
	}
	
	//click on categories of pets
	public void clickBirds(){
		driver.findElement(Petcategory).click();

	}
	
	//Click on birds
	public void SearchCategories(String strCategoryName)
	{
		//Click birds
		this.clickBirds();      
	}
}
