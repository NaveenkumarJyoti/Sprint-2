package pet;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETPetByStatus {
	//Get request
	@Test
	public void GetPets()
	{
		//Sending request
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httprequest = RestAssured.given();
		Response res= httprequest.get("/v2/pet/findByStatus?status=available");		

		//Status validation
		int statusCode=res.getStatusCode();
		Assert.assertEquals(statusCode,200,"Correct stauts code returned");

		//Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("Pet Status is : "+eval.get("status"));
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");

		//Printing all the headers
		Headers allHeaders = res.headers(); 
		for(Header header : allHeaders) 
		{ 
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue()); 
		} 

		//Response headers validation
		String contentType = res.header("Content-Type"); 
		Assert.assertEquals(contentType, "application/json"); 
		String serverType = res.header("Server"); 
		Assert.assertEquals(serverType, "Jetty(9.2.9.v20150224)");
	}
}
