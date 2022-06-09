package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTCreateUsers {

	//Post request
	@Test
	public void PostCreateUser() throws IOException 
	{
		//Fetching data from property file
		File src=new File("C:\\Users\\NAVEENKUMAR\\Desktop\\Sprint2\\Day-1\\CreateUserRA.xlsx");
		FileInputStream fis=new FileInputStream(src);
		Properties prop=new Properties();
		prop.load(fis);
		String id=prop.getProperty("id");
		String username=prop.getProperty("username");
		String userstatus=prop.getProperty("userstatus");

		//Sending request
		RestAssured.baseURI = "https://petstore.swagger.io";
		RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json");

		//parameterized
		Response response = httpRequest.body("{ \"id\": \"" + id + "\", \"name\": \"" + username + "\",\"status\": \"" + userstatus + "\"}").post("/v2/user");

		//Status assertion
		System.out.println("Status code : " +response.getStatusCode());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		//Response body assertion
		JsonPath eval=response.jsonPath();
		System.out.println(eval.get("code").toString());

		//Headers assertion
		String contentType=response.header("Content-Type");
		System.out.println("Content-Type : "+contentType);
		Assert.assertEquals("application/json",contentType);
		String encoding=response.header("Transfer-Encoding");
		Assert.assertEquals(encoding,"chunked");
	}
}
