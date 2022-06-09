package store;

import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTPlaceOrder {
	//GET request
	@Test
	public void placeOrder() 
	{
		//sending request
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given();
		
		int id=11,petId=123,quantity=2;
		String shipDate="2022-06-03T17:26:16.045Z",status="placed";
		boolean complete=true;
		Response res=httpReq.body("{ \"id\": \"" + id + "\", \"name\": \"" + petId + "\",\"quantity\": \"" + quantity+ "\",\"shipDate\": \"" + shipDate+ "\",\"status\": \"" + status+ "\",\"complete\": \"" + complete +"\"}\"").get("/v2/store/order");

		//Status assertion
		System.out.println("Status code : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");

		//Printing response body in console
		System.out.println(res.getBody().asString());

		//Response body validation
		JsonPath eval=res.jsonPath();
		Assert.assertEquals((int)eval.get("quantity"),quantity);

		//Response Headers validation
		String transferEncoding=res.header("Transfer-Encoding");
		System.out.println("Transfer-Encoding : "+transferEncoding);
		Assert.assertEquals("chunked", transferEncoding);
		String server=res.header("Server");
		System.out.println("Server : "+server);
		Assert.assertEquals("Jetty(9.2.9.v20150224)", server);
	}

}
