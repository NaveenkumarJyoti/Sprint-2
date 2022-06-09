package pet;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class POSTAddNewPet {
	//Post request
	@Test
	public void AddPet() {
		
		String id="123456";
		String username="doggie";
		String status="available";
		RestAssured.baseURI="https://petstore.swagger.io";
		RequestSpecification httpReq=RestAssured.given().header("Content-Type","application/json");
		
		Response res = httpReq.body("{ \"id\": \"" + id + "\", \"name\": \"" + username + "\",\"status\": \"" + status + "\"}").post("/v2/pet");
		ResponseBody body=res.getBody();
		System.out.println("Status Code is : "+res.getStatusCode());
		JsonPath eval=res.jsonPath();
		System.out.println("ID : "+eval.get("id"));
		System.out.println("Status : "+eval.get("status"));
			
 }
}
