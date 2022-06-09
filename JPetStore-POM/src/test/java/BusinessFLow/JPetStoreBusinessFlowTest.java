package BusinessFLow;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import addToCart.MoveToCart;
import checkout.JPetStoreCartPage;
import checkout.JPetStoreCheckoutConfirmPage;
import checkout.JPetStoreCheckoutPage;
import checkout.JPetStoreMyOrdersPage;
import deleteOrder.DeleteOrders;
import loginPOM.JPetStoreHomePage;
import loginPOM.JPetStoreLoginPage;
import search.SearchForCategories;
import search.SearchForPetDetails;
import search.SearchForProductId;
import search.SearchPetsPage;
import signUp.JPetStoreSignUpPage;

public class JPetStoreBusinessFlowTest {
	String driverPath="C:\\Users\\NAVEENKUMAR\\Desktop\\Selenium\\selenium jars\\geckodriver-v0.31.0-win64\\geckodriver.exe";
	WebDriver driver;

	//Instantiation
	JPetStoreHomePage homePage;
	JPetStoreSignUpPage signUpPage;
	JPetStoreLoginPage loginPage;
	SearchPetsPage objSearchPage;
	SearchForCategories objSearchForCategories;
	SearchForProductId objSearchForProductId;
	SearchForPetDetails objSearchForPetDetails;
	MoveToCart objMoveToCart;
	JPetStoreCartPage cartPage;
	JPetStoreCheckoutPage checkoutPage;
	JPetStoreCheckoutConfirmPage checkoutConfirmPage;
	JPetStoreMyOrdersPage myOrdersPage;
	DeleteOrders objDeleteOrders;

	//Launching fire fox 
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.navigate().to("https://aspectran-jpetstore.herokuapp.com/catalog/");
	}

	//Create account
	@Test(priority = 1)
	public void testAccountCreatedSuccessfully() throws IOException {
		homePage=new JPetStoreHomePage(driver);
		signUpPage=new JPetStoreSignUpPage(driver);
		homePage.clickSignUp();

		//Read data from xlsx file
		FileInputStream fis1=new FileInputStream("C:\\Users\\NAVEENKUMAR\\Desktop\\Sprint2\\Day-3\\CreateAccount.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis1);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int rowNum=sheet.getLastRowNum();
		int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Row : "+rowNum+" & Column : "+columnNum);

		//		signUpPage.createAccount(sheet.getRow(0).getCell(0).toString(), sheet.getRow(0).getCell(1).toString(), sheet.getRow(0).getCell(2).toString(), sheet.getRow(0).getCell(3).toString(), 
		//				sheet.getRow(0).getCell(4).toString(), sheet.getRow(0).getCell(5).toString(), sheet.getRow(0).getCell(6).toString(), sheet.getRow(0).getCell(7).toString(), sheet.getRow(0).getCell(8).toString(), 
		//				sheet.getRow(0).getCell(9).toString(), sheet.getRow(0).getCell(10).toString(), sheet.getRow(0).getCell(11).toString());
		//
		//		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Your account has been created. Please try login.')]")).isDisplayed());
		//		System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Your account has been created. Please try login.')]")).getText());
		workbook.close();
	}


	//Logging in & validating welcome message
	@Test(priority = 2)
	public void testHomePageAppearCorrect() throws IOException {
		homePage=new JPetStoreHomePage(driver);
		loginPage=new JPetStoreLoginPage(driver);

		homePage.clickSignIn();

		//Fetching data from xlsx file
		FileInputStream fis1=new FileInputStream("C:\\Users\\NAVEENKUMAR\\Desktop\\Sprint2\\Day-3\\LoginCredentials.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis1);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int rowNum=sheet.getLastRowNum();
		int columnNum=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Row : "+rowNum+" & Column : "+columnNum);
		XSSFCell uname=sheet.getRow(0).getCell(0);
		XSSFCell pwd=sheet.getRow(0).getCell(1);
		System.out.println(uname.toString()+" & "+pwd.toString());
		workbook.close();

		loginPage.loginToJPetStore(uname.toString(),pwd.toString());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='WelcomeContent']")).isDisplayed());		
		Assert.assertEquals(homePage.getHomePageUserName(),"Welcome Velverde!");
		System.out.println("Welcome content : "+homePage.getHomePageUserName());
	}

	//Search for pets
	@Test(priority = 3)
	public void Search_Pet_Page() throws InterruptedException
	{
		objSearchPage = new SearchPetsPage(driver);
		objSearchPage.Productname("Koi");

		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		//search for categories
		objSearchForCategories = new SearchForCategories(driver);
		driver.findElement(By.xpath("//a[contains(text(),'Birds')]")).click();

		//search for product id
		objSearchForProductId = new SearchForProductId (driver);
		driver.findElement(By.linkText("AV-CB-01")).click();

		//search for pet details
		objSearchForPetDetails = new SearchForPetDetails(driver);
		driver.findElement(By.linkText("EST-18")).click();
		driver.findElement(By.xpath("//h3[contains(text(),'Amazon Parrot')]")).click();
		driver.manage().window().maximize();
	}

	//Add to cart
	@Test(priority = 4)
	public void Cart() {
		objMoveToCart = new MoveToCart(driver);
		objMoveToCart.AddToCart("bulldog");
		objMoveToCart.Update_Quantity("4");
		objMoveToCart.Click_on_Update();
		objMoveToCart.Remove_All();
	}

	//Checkout
	@Test(priority = 5)
	public void addPetToCart() {
		driver.findElement(By.xpath("//body/section[1]/div[2]/div[1]/div[3]/div[1]/form[1]/div[1]/input[1]")).sendKeys("Angelfish");
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'FI-SW-01')]")).click();
		driver.findElement(By.xpath("//tbody/tr[2]/td[5]/a[1]")).click();
	}

	//Validating order placed successfully
	@Test(priority = 6)
	public void testOrderPlacedSuccessfully() {
		cartPage=new JPetStoreCartPage(driver);
		cartPage.clickProceedToCheckout();

		checkoutPage=new JPetStoreCheckoutPage(driver);
		checkoutPage.clickContinue();

		checkoutConfirmPage=new JPetStoreCheckoutConfirmPage(driver);
		checkoutConfirmPage.clickConfirm();

		myOrdersPage=new JPetStoreMyOrdersPage(driver);
		myOrdersPage.validateMessage();
	}

	//DeleteOrder
	@Test(priority = 7)
	public void Delete() {
		objDeleteOrders = new DeleteOrders(driver);
		objDeleteOrders.My_Orders();
		objDeleteOrders.Order_Id();
		objDeleteOrders.Delete_Order();
	}
}
