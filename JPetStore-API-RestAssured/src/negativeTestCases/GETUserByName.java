package negativeTestCases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GETUserByName {

	@Test
	public void petById() {
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given();
		
		Response res=httpReq.get("v2/user/Gisele");
		
		System.out.println("Status code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 404);
		
		JsonPath eval=res.jsonPath();
		System.out.println("Error message : "+eval.get("message"));
		
		String connection=res.header("Connection");
		System.out.println("Connection : "+connection);
		Assert.assertEquals("keep-alive", connection);
		
		String transferEncoding=res.header("Transfer-Encoding");
		System.out.println("Transfer-Encoding : "+transferEncoding);
		Assert.assertEquals("chunked", transferEncoding);
		
		String server=res.header("Server");
		System.out.println("Server : "+server);
		Assert.assertEquals("Jetty(9.2.9.v20150224)", server);
		
		String contentType=res.header("Content-Type");
		System.out.println("Content-Type : "+contentType);
		Assert.assertEquals("application/json", contentType);
	}
}
