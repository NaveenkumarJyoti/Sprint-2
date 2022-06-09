package user;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUTUserDetails {
	//Put request
	@Test
	public void UpdateUser() {
		String firstName="Chiesa";
		String lastName="Vlahovic";
		
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpRequest=RestAssured.given().header("Content-Type","application/json");
		Response response=httpRequest.body("{\"firstName\": \""+firstName+"\", \"lastName\": \""+lastName+"\"}" ).put("/v2/user/abc");
		
		//Status validation
		System.out.println("The response code: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		
		//Response header validation
		JsonPath validate=response.jsonPath();
		Assert.assertEquals(validate.get("type"),"unknown");
		System.out.println("type : "+validate.get("type"));
		String connection=response.header("Connection");
		System.out.println("Connection : "+connection);
		Assert.assertEquals("keep-alive", connection);
		String accessMethods=response.header("Access-Control-Allow-Methods");
		System.out.println("Access-Control-Allow-Methods : "+accessMethods);
		Assert.assertEquals("GET, POST, DELETE, PUT", accessMethods);
	}
}
