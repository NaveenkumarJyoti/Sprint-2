package JPetStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessFlowJPetStore {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver;

		//Reading the property file
		File source=new File("./Test-Resources/config.property");
		FileInputStream fis=new FileInputStream(source);
		Properties prop=new Properties();
		prop.load(fis);

		//Launching fire fox driver
		System.setProperty("webdriver.gecko.driver",prop.getProperty("FireFoxDriver"));		
		driver=new FirefoxDriver();

		//Navigating to JPetStore home page
		driver.navigate().to(prop.getProperty("url"));

		//Sign in
		driver.findElement(By.linkText(prop.getProperty("signIn"))).click();
		driver.findElement(By.name(prop.getProperty("username"))).clear();
		driver.findElement(By.name(prop.getProperty("username"))).sendKeys("Luke");
		driver.findElement(By.name(prop.getProperty("password"))).clear();
		driver.findElement(By.name(prop.getProperty("password"))).sendKeys("Abc@123");
		driver.findElement(By.xpath(prop.getProperty("login"))).click(); 

		//Search pet
		FileInputStream excelFile=new FileInputStream("C:\\Users\\NAVEENKUMAR\\Desktop\\Sprint2\\Day-4\\SearchPet.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(excelFile);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int row=sheet.getLastRowNum();
		int col=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Rows : "+row+"   Columns : "+col);


		//Iterating
		XSSFCell searchKey;
		for(int i=0;i<=row;i++) {
			searchKey=sheet.getRow(i).getCell(0);
			driver.findElement(By.xpath(prop.getProperty("searchInput"))).sendKeys(searchKey.toString());
			driver.findElement(By.xpath(prop.getProperty("clickSearch"))).click();
			Thread.sleep(2000);
			if(i==0) {
				driver.navigate().back();
				driver.findElement(By.xpath(prop.getProperty("searchInput"))).clear();
			}
		}
		workbook.close();

		//Adding pet to cart
		driver.findElement(By.xpath(prop.getProperty("clickPetId"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("addToCart"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		//Proceed to Checkout and place the order
		driver.findElement(By.xpath(prop.getProperty("proceedToCheckout"))).click();
		driver.findElement(By.xpath(prop.getProperty("continue"))).click();
		driver.findElement(By.xpath(prop.getProperty("confirm"))).click();
		try {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("message"))));
		}catch(Exception e) {

		}
		Assert.assertTrue(driver.findElement(By.xpath(prop.getProperty("message"))).isDisplayed());
		System.out.println("Message : "+driver.findElement(By.xpath(prop.getProperty("message"))).getText());

		//Validating order id
		driver.findElement(By.linkText(prop.getProperty("myOrders"))).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement orderId=driver.findElement(By.xpath(prop.getProperty("orderId")));
		Assert.assertTrue(orderId.getText().matches("[0-9]+"));
		System.out.println("Order id is : "+orderId.getText());

		//close window
		driver.close();
	}
}
