package NegativeTests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NegativeTestsStepDefinitions {
	static WebDriver driver;

	@Given("User is on sign up page")
	public void user_is_on_sign_up_page() {
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://aspectran-jpetstore.herokuapp.com/catalog/");
		driver.findElement(By.linkText("Sign Up")).click();
	}

	@When("Enter invalid details")
	public void enter_invalid_details() throws InterruptedException {
		driver.findElement(By.name("username")).sendKeys("Maiot");
		driver.findElement(By.name("password")).sendKeys("asDF@123");
		driver.findElement(By.name("repeatedPassword")).sendKeys("ASDF@123");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,100)");
		driver.findElement(By.name("firstName")).sendKeys("Marnus");
		driver.findElement(By.name("lastName")).sendKeys("Raford");
		driver.findElement(By.name("email")).sendKeys("po748@gmail.com");
		driver.findElement(By.name("phone")).sendKeys("78489454015");
		driver.findElement(By.name("address1")).sendKeys("Surya city");
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,100)");
		driver.findElement(By.name("address2")).sendKeys("Chandapura");
		driver.findElement(By.name("city")).sendKeys("Bangalore");
		driver.findElement(By.name("state")).sendKeys("Karnataka");
		driver.findElement(By.name("zip")).sendKeys("560099");
		driver.findElement(By.name("country")).sendKeys("India");
		JavascriptExecutor js2=(JavascriptExecutor)driver;
		js2.executeScript("window.scrollBy(0,100)");
		Thread.sleep(1000);
			}

	@And("Click on save account information")
	public void click_on_save_account_information() {
		driver.findElement(By.xpath("//button[contains(text(),'Save Account Information')]")).click();
	}

	@Then("Sign up failed and error message is displayed")
	public void sign_up_failed_and_error_message_is_displayed() {
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Passwords do not match')]")).isDisplayed());
		System.out.println("Message is : "+driver.findElement(By.xpath("//span[contains(text(),'Passwords do not match')]")).getText());
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		driver.findElement(By.linkText("Sign In")).click();
	}
	
	@When("^Enter invalid credentials$")
	public void enter_invalid_username_and_password(DataTable credentials) throws InterruptedException {
		
		List<List<String>> data=credentials.cells();
		
		Thread.sleep(2000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(data.get(0).get(0));
		Thread.sleep(1000);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(data.get(0).get(1));
	}
	
	@And("Click on login")
	public void click_on_login() throws InterruptedException {
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		Thread.sleep(2000);
	}

	@Then("Login failed message is displayed")
	public void login_failed_message_is_displayed() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Signon failed.')]")).isDisplayed());
		System.out.println("Error message is : "+driver.findElement(By.xpath("//div[contains(text(),'Signon failed.')]")).getText());
	}

	@Given("User clicks on search")
	public void user_clicks_on_search() {
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[1]/div[3]/div[1]/form[1]/div[1]/input[1]")).click();
	}
	
	@When("^User enters (.*)$")
	public void user_enters_product(String product) {
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[1]/div[3]/div[1]/form[1]/div[1]/input[1]")).sendKeys(product);
	}

	@And("Click on search")
	public void click_on_search() {
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
	}

	@Then("No relevant products are found")
	public void no_relevant_products_are_found() {
		System.out.println("No relevant products are displayed");
	}

}
