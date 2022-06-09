package negativeTestCases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GETPetById {
	
	@Test
	public void petById() {
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given();
		
		Response res=httpReq.get("v2/pet/12548788");
		
		System.out.println("Status code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 404);
		
		JsonPath eval=res.jsonPath();
		System.out.println("Error message : "+eval.get("message"));
		
		String connection=res.header("Connection");
		Assert.assertEquals("keep-alive", connection);
		
		String transferEncoding=res.header("Transfer-Encoding");
		Assert.assertEquals("chunked", transferEncoding);
		
	}
}
