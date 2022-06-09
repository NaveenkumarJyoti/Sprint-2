package user;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GETUserLogOut {
	//Get request
	@Test
	public void userLogOut() {
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given();
		
		JSONObject parameters=new JSONObject();
		httpReq.body(parameters.toJSONString());
		Response res=httpReq.get("/v2/user/logout");
		
		//Validating status
		JsonPath eval=res.jsonPath();
		System.out.println("Status code : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
		
		//Validating response body
		Assert.assertEquals(eval.get("type"),"unknown");
		Assert.assertEquals(eval.get("message"),"ok");
		System.out.println("message : "+eval.get("message"));
		System.out.println("Type : "+eval.get("type"));
		
		//Validating response headers
		String server=res.header("Server");
		Assert.assertEquals("Jetty(9.2.9.v20150224)", server);
		String accessControlHeaders=res.header("Access-Control-Allow-Headers");
		Assert.assertEquals("Content-Type, api_key, Authorization", accessControlHeaders);
	}
}
