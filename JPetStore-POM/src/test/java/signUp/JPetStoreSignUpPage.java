package signUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JPetStoreSignUpPage {

	WebDriver driver;
	
	//Storing web elements
	By userId=By.name("username");
	By password=By.name("password");
	By confirmPassword=By.name("repeatedPassword");
	By firstName=By.name("firstName");
	By lastName=By.name("lastName");
	By email=By.name("email");
	By phone=By.name("phone");
	By add1=By.name("address1");
	By add2=By.name("address2");
	By city=By.name("city");
	By state=By.name("state");
	By zip=By.name("zip");
	By country=By.name("country");
	By saveAccountInfo=By.xpath("//button[contains(text(),'Save Account Information')]");
	
	//Constructor
	public JPetStoreSignUpPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Setters
	public void setUserId(String uId) {
		driver.findElement(userId).sendKeys(uId);
	}
	public void setPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	public void setConfirmPassword(String cPwd) {
		driver.findElement(confirmPassword).sendKeys(cPwd);
	}
	public void setFirstName(String fName) {
		driver.findElement(firstName).sendKeys(fName);
	}
	public void setLastName(String lName) {
		driver.findElement(lastName).sendKeys(lName);
	}
	public void setEmail(String uEmail) {
		driver.findElement(email).sendKeys(uEmail);
	}
//	public void setPhone(String ph) {
//		driver.findElement(phone).sendKeys(ph);
//	}
	public void setAdd1(String addr1) {
		driver.findElement(add1).sendKeys(addr1);
	}
	public void setAdd2(String addr2) {
		driver.findElement(add2).sendKeys(addr2);
	}
	public void setCity(String uCity) {
		driver.findElement(city).sendKeys(uCity);
	}
	public void setState(String stat) {
		driver.findElement(state).sendKeys(stat);
	}
	public void setZip(String uZip) {
		driver.findElement(zip).sendKeys(uZip);
	}
	public void setCountry(String nation) {
		driver.findElement(country).sendKeys(nation);
	}
	
	//click save account info
	public void clickSaveAccountInfo() {
		driver.findElement(saveAccountInfo).click();
	}
	
	//Create an account
	public void createAccount(String uId,String pwd,String cPwd,String fName,String lName,String uEmail,String addr1,String addr2,
			String uCity,String stat,String uZip,String nation) {
		this.setUserId(uId);
		this.setPassword(pwd);
		this.setConfirmPassword(cPwd);
		this.setFirstName(fName);
		this.setLastName(lName);
		this.setEmail(uEmail);
		//this.setPhone(ph);
		driver.findElement(phone).sendKeys("7892169588");
		this.setAdd1(addr1);
		this.setAdd2(addr2);
		this.setCity(uCity);
		this.setState(stat);
		this.setZip(uZip);
		this.setCountry(nation);
		this.clickSaveAccountInfo();
	}
	
	
}
