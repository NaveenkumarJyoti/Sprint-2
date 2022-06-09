package user;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETUserByName {
	@Test
	public void GetSingleUser() {
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given();
		
		JSONObject params=new JSONObject();
		httpReq.body(params.toJSONString());
			
		Response res=httpReq.get("/v2/user/abc");
		
		//Status and Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("firstname is : "+eval.get("firstName")+", lastname is : "+eval.get("lastName"));
		System.out.println("Status code is : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(eval.get("username"), "abc");
		
		//Response headers
		Headers allHeaders=res.headers();
		System.out.println("------------------------");
		for(Header h:allHeaders) {
			System.out.println("Key: "+h.getName()+" Value: "+h.getValue());
		}
		System.out.println("-----------------------------");
		
		//Response headers validation
		String contentType=res.header("Content-Type");
		System.out.println("Content-Type : "+contentType);
		Assert.assertEquals("application/json",contentType);
		
		String encoding=res.header("Transfer-Encoding");
		Assert.assertEquals(encoding,"chunked");
	}
}
