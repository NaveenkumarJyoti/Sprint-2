package user;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DELETEUser {
	
	@Test
	public void DeleteUser() 
	{
		//Delete request
		RestAssured.baseURI="https://6479aa6e-7e37-4ee2-8dc7-16f6f37d6ab6.mock.pstmn.io";
		RequestSpecification httpReq=RestAssured.given();
		Response res=httpReq.delete("/v2/user/abc");
		
		//Status validation
		System.out.println("Response code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
		
		//Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("Message : "+eval.get("message"));
		Assert.assertEquals(eval.get("message"), "User deleted successfully.");
		
		//Response header validation
		String eTag=res.header("ETag");
		Assert.assertEquals("W/\"31-YHsAadk2JqTcmnpC0tXVnkKY8TU\"", eTag);
		String vary=res.header("Vary");
		Assert.assertEquals("Accept-Encoding", vary);
	}
}
