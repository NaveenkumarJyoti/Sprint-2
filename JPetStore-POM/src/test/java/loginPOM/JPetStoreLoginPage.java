package loginPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JPetStoreLoginPage {

	WebDriver driver;
	By username=By.name("username");
	By password=By.name("password");
	By login=By.xpath("//button[contains(text(),'Login')]");
	
	//Constructor
	public JPetStoreLoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Set username
	public void setUserName(String uname) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(uname);
	}
	
	//Set password
	public void setPassword(String pwd) {
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);
	}
	
	//Click on login
	public void clickLogin() {
		driver.findElement(login).click();
	}
	
	//Login to JPetStore
	public void loginToJPetStore(String uname,String pwd) {
		this.setUserName(uname);
		this.setPassword(pwd);
		this.clickLogin();
	}
	
}
