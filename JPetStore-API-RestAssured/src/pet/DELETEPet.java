package pet;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DELETEPet {
	@Test
	public void DeleteUser() 
	{
		//Delete request
		RestAssured.baseURI="https://4ae55b3f-e22f-4c01-8dfc-f45049b0573e.mock.pstmn.io";
		RequestSpecification httpReq=RestAssured.given();
		Response res=httpReq.delete("/v2/pet/9");

		//Status validation
		System.out.println("Response code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");

		//Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("Message : "+eval.get("message"));
		Assert.assertEquals(eval.get("message"),"Pet deleted successfully.");

		//Response header validation
		String server=res.header("Server");
		Assert.assertEquals("nginx", server);
		String vary=res.header("Vary");
		Assert.assertEquals("Accept-Encoding", vary);
	}
}
