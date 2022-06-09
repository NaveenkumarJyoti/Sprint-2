package user;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GETUserLogin {
	//Get request
	@Test
	public void userLogin() {
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given().when().param("username", "abc")
				.param("password", "string");
		
		JSONObject requestParameters=new JSONObject();
		httpReq.body(requestParameters.toJSONString());
		
		//Status validation
		Response res=httpReq.get("/v2/user/login/");
		System.out.println("Status code : "+res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println("Status line : "+res.getStatusLine());
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
		
		//Response body validation
		JsonPath eval=res.jsonPath();
		System.out.println("Message : "+eval.get("message"));
		Assert.assertEquals(eval.get("type"),"unknown");
		
		//Response header validation
		String transferEncoding=res.header("Transfer-Encoding");
		System.out.println("Transfer-Encoding : "+transferEncoding);
		Assert.assertEquals("chunked", transferEncoding);
		String server=res.header("Server");
		System.out.println("Server : "+server);
		Assert.assertEquals("Jetty(9.2.9.v20150224)", server);
	}
}

//JSONObject requestParams=new JSONObject();
//httpRequest.body(requestParams.toJSONString());
//requestParams.put("name","Naviin");
//requestParams.put("job","Analyst");
