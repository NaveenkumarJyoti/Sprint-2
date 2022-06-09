package store;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DELETEPurchaseByOrderId 
{
	@Test
	public void DeleteUser() 
	{
		//Delete request
		RestAssured.baseURI="https://a83f1936-30a1-4a15-93b7-090c6a59104b.mock.pstmn.io";
		RequestSpecification httpReq=RestAssured.given();
		Response res=httpReq.delete("/v2/store/order/4");

		//Status validation
		System.out.println("Response code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");

		//Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("Message : "+eval.get("message"));
		Assert.assertEquals(eval.get("message"), "Deleted purchase successfully.");

		//Response header validation
		String server=res.header("Server");
		Assert.assertEquals("nginx", server);
		String vary=res.header("Vary");
		Assert.assertEquals("Accept-Encoding", vary);
	}
}
