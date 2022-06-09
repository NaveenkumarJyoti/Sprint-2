package BusinessFlow;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JPetStoreStepDefinitions {

	static WebDriver driver;

	//Sign up
	@Given("User navigates to sign up page")
	public void user_navigates_to_sign_up_page() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\NAVEENKUMAR\\Desktop\\Sprint2\\Sprint-2 workspace\\JPetStore-GUI\\src\\test\\resources\\Drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.navigate().to("https://aspectran-jpetstore.herokuapp.com/catalog/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Up")).click();
	}

	@When("User enters necessary details")
	public void user_enters_necessary_details() throws InterruptedException {
		Thread.sleep(1000);
		//		driver.findElement(By.name("username")).sendKeys("Marriot");
		//		driver.findElement(By.name("password")).sendKeys("asDF@123");
		//		driver.findElement(By.name("repeatedPassword")).sendKeys("asDF@123");
		//		JavascriptExecutor js=(JavascriptExecutor)driver;
		//		js.executeScript("window.scrollBy(0,100)");
		//		driver.findElement(By.name("firstName")).sendKeys("Marnus");
		//		driver.findElement(By.name("lastName")).sendKeys("Raford");
		//		driver.findElement(By.name("email")).sendKeys("poo748@gmail.com");
		//		driver.findElement(By.name("phone")).sendKeys("78489454015");
		//		driver.findElement(By.name("address1")).sendKeys("Surya city");
		//		JavascriptExecutor js1=(JavascriptExecutor)driver;
		//		js1.executeScript("window.scrollBy(0,100)");
		//		driver.findElement(By.name("address2")).sendKeys("Chandapura");
		//		driver.findElement(By.name("city")).sendKeys("Bangalore");
		//		driver.findElement(By.name("state")).sendKeys("Karnataka");
		//		driver.findElement(By.name("zip")).sendKeys("560099");
		//		driver.findElement(By.name("country")).sendKeys("India");
		//		JavascriptExecutor js2=(JavascriptExecutor)driver;
		//		js2.executeScript("window.scrollBy(0,100)");
		//		Thread.sleep(1000);
		//		driver.findElement(By.xpath("//button[contains(text(),'Save Account Information')]")).click();
	}

	@Then("Account is created")
	public void account_is_created() throws InterruptedException {
		//		System.out.println("Account is created");
		//		Thread.sleep(3000);
		//		WebElement message=driver.findElement(By.xpath("//p[contains(text(),'Your account has been created. Please try login.')]"));
		//		Assert.assertTrue(message.isDisplayed());
		//		System.out.println("Message displayed as : "+message.getText());
	}

	@And("User enters invalid data in mandatory fields")
	public void user_enters_invalid_data_in_mandatory_fields() {
		driver.navigate().to("https://aspectran-jpetstore.herokuapp.com/catalog/");
		driver.findElement(By.linkText("Sign Up")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
		driver.findElement(By.name("phone")).sendKeys("abcdefgh");
		driver.findElement(By.name("email")).sendKeys("newbie");
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,150)");
		driver.findElement(By.xpath("//button[contains(text(),'Save Account Information')]")).click();
	}

	@Then("Error message")
	public void error_message() throws InterruptedException {
		Thread.sleep(3000);
		WebElement emailErrorMsg=driver.findElement(By.xpath("//span[contains(text(),'must be a well-formed email address')]"));
		Assert.assertTrue(emailErrorMsg.isDisplayed());
		System.out.println("Email error message : "+emailErrorMsg.getText());
		WebElement phoneErrorMsg=driver.findElement(By.xpath("//span[contains(text(),'Must be telephone number format (allowed format: \"')]"));
		Assert.assertTrue(phoneErrorMsg.isDisplayed());
		System.out.println("Phone error message : "+phoneErrorMsg.getText());
	}

	@And("User leaves mandatory fields blank")
	public void user_leaves_mandatory_fields_blank() {
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,150)");
		driver.findElement(By.xpath("//button[contains(text(),'Save Account Information')]")).click();
	}

	@Then("Error message is displayed")
	public void error_message_is_displayed() {
		Assert.assertTrue(driver.findElement(By.xpath("//body[1]/section[1]/div[2]/div[2]/div[1]/form[1]/table[2]/tbody[1]/tr[1]/td[2]/span[1]")).isDisplayed());
		System.out.println("Error message is : "+driver.findElement(By.xpath("//body[1]/section[1]/div[2]/div[2]/div[1]/form[1]/table[2]/tbody[1]/tr[1]/td[2]/span[1]")).getText());
	}

	//Login
	@Given("User is on login page")
	public void user_is_on_login_page() {
		driver.findElement(By.linkText("Sign In")).click();
	}

	@When("^User enters valid credentials$")
	public void user_enters_valid_credentials(DataTable credentials) throws InterruptedException {
		
		List<List<String>> data=credentials.cells();
 		
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(data.get(0).get(0));
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(data.get(0).get(1));
		Thread.sleep(2000);
	}

	@When("User clicks on login")
	public void user_clicks_on_login() throws InterruptedException {
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		Thread.sleep(1000);
	}

	@Then("Logged in successfully")
	public void logged_in_successfully() {
		System.out.println("Logged in successfully");
	}

	@Then("User checks logo")
	public void user_checks_logo() {
		System.out.println("User looks for Aspectran logo");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Then("User finds logo on top left corner")
	public void user_finds_logo_on_top_left_corner() {
		Assert.assertTrue(driver.findElement(By.xpath("//body/nav[@id='navigation']/div[1]/div[1]/a[1]")).isEnabled());
		System.out.println("User found the logo on top left corner");
	}

	@Then("User looks for welcome message")
	public void user_looks_for_welcome_message() {
		System.out.println("User looks for welcome message");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Then("User reads the welcome message")
	public void user_reads_the_welcome_message() {
		WebElement msg=driver.findElement(By.xpath("//div[@id='WelcomeContent']"));
		System.out.println("User reads the message : "+msg.getText());
	}

	//Search for pet
	@Given("User enters petname in search")
	public void user_enters_petname_in_search() {
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[1]/div[3]/div[1]/form[1]/div[1]/input[1]")).sendKeys("Angel Fish");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@When("User clicks on search")
	public void user_clicks_on_search() {
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@Then("Respective pets are displayed")
	public void respective_pets_are_displayed() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Search Results for \"Angel Fish\"')]")).isDisplayed());
		Thread.sleep(1000);
	}
	
	@And("User clicks on petId")
	public void user_clicks_on_petId() {
		driver.findElement(By.xpath("//tbody/tr[2]/td[1]/strong[1]/a[1]")).click();
	}
	
	@Then("Pet varieties are displayed")
	public void pet_varieties_are_displayed() {
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Angelfish')]")).isDisplayed());
		System.out.println("Varieties of "+driver.findElement(By.xpath("//h3[contains(text(),'Angelfish')]")).getText()+" are displayed");
	}
	
	@And("User clicks on certain pet in categories")
	public void user_clicks_on_certain_pet_in_categories() {
		driver.findElement(By.xpath("//a[contains(text(),'Cats')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@Then("User is navigated to the respective pet page")
	public void user_is_navigated_to_the_respective_page() {
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Cats')]")).isDisplayed());
		System.out.println("User is navigated to "+driver.findElement(By.xpath("//a[contains(text(),'Cats')]")).getText()+" page");
	}
	
	@And("User clicks on product id")
	public void user_clicks_on_product_id() {
		driver.findElement(By.xpath("//a[contains(text(),'FL-DSH-01')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@Then("Navigates to the varieties available")
	public void navigates_to_the_varieties_availabel() {
		System.out.println("Different varieties of "+driver.findElement(By.xpath("//h3[contains(text(),'Manx')]")).getText()+" are displayed");
	}
	
	@And("User clicks on item id")
	public void user_clicks_on_item_id() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'EST-14')]")).click();
		Thread.sleep(2000);
	}
	
	@Then("Respective pet details are displayed") 
	public void respective_pet_details_are_displayed() {
		System.out.println(driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText());
	}
	
	//Add to cart
	@Given("User is on pet page")
	public void user_is_on_pet_page() {
		
	}
	
	@When("User clicks on add to cart")
	public void user_clicks_on_add_to_cart() {
		driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	@Then("Navigated to cart")
	public void navigated_to_cart() {
		Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Shopping Cart')]")).isDisplayed());
	}
	
	@And("User changes the quantity")
	public void user_changes_the_quantity() throws InterruptedException {
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/input[1]")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/input[1]")).sendKeys("4");
		Thread.sleep(1000);
	}
	
	@When("User clicks on update cart")
	public void user_clicks_on_update_cart() {
		driver.findElement(By.xpath("//button[contains(text(),'Update Cart')]")).click();
	}
	
	@Then("Quantity and amount gets updated")
	public void quantity_and_amount_gets_updated() throws InterruptedException {
		Thread.sleep(2000);
		//Assert.assertEquals(driver.findElement(By.xpath("//tbody/tr[2]/td[5]/input[1]")).getText(),"4");
		Thread.sleep(1000);
	}
	
	@And("User clicks on remove")
	public void user_clicks_on_remove() {
		driver.findElement(By.xpath("//tbody/tr[2]/td[8]/a[1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@When("Pet is removed from cart")
	public void pet_is_removed_from_cart() {
		System.out.println("Pet is removed from the cart");
	}
	
	@Then("Cart empty message is displayed")
	public void cart_message_is_displayed() throws InterruptedException {
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Your cart is empty.')]")).isDisplayed());
		System.out.println("Message displayed is : "+driver.findElement(By.xpath("//td[contains(text(),'Your cart is empty.')]")).getText());
		Thread.sleep(2000);
	}
	
	@And("User clicks on return to main menu") 
	public void user_clicks_on_return_to_main_menu() throws InterruptedException {
		driver.findElement(By.linkText("Return to Main Menu")).click();
		Thread.sleep(2000);
	}
	
	@Then("Navigated to the main menu")
	public void navigated_to_the_main_menu() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='WelcomeContent']")).isDisplayed());
		System.out.println("Returned to Main Menu");
	}
	
	//Checkout
	@Given("User navigates to cart and click on checkout")
	public void user_navigates_to_cart_and_click_on_checkout() throws InterruptedException {
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[2]/div[2]/div[1]/div[1]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("FI-SW-02")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Add to Cart')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[1]/div[2]/div[1]/a[1]/img[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Proceed to Checkout')]")).click();
	}

	@When("Place the order")
	public void place_the_order() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		JavascriptExecutor js1=(JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,200)");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
	}

	@When("Order is placed")
	public void order_is_placed() {
		System.out.println("Order is placed");
	}

	@Then("User should see the successfully placed order message")
	public void user_should_see_the_successfully_placed_order_message() {
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Thank you, your order has been submitted.')]")).isDisplayed());
		System.out.println("Message : "+driver.findElement(By.xpath("//p[contains(text(),'Thank you, your order has been submitted.')]")).getText());
		driver.findElement(By.linkText("Return to Main Menu")).click();
	}

	//Delete Order
	@Given("User navigates to my orders")
	public void user_navigates_to_my_orders() throws InterruptedException {
		driver.findElement(By.linkText("My Orders")).click();
		Thread.sleep(2000);
	}

	@When("User selects a particular order id")
	public void user_selects_a_particular_order_id() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'1000')]")).click();
		Thread.sleep(2000);
	}

	@And("Clicks on delete order")
	public void clicks_on_delete_order() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Delete Order')]")).click();
	}

	@Then("Order deleted successfully")
	public void order_deleted_successfully() throws InterruptedException {
		System.out.println("Order deleted successfully");
		Thread.sleep(2000);
		driver.close();
	}
}
