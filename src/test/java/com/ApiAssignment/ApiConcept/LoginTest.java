package com.ApiAssignment.ApiConcept;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginTest {

	@Before
	public void before() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	@Test
	public void testUserLoginRequest() {

		// Create a request object
		RequestSpecification request = RestAssured.given();

		String email = "jbond@007.com";
		String password = "watchingya";

		// Add header
		request.header("Content-Type", "application/json");

		// Add the request body
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("password", password);

		request.body(jsonObject);

		//Doing registration so that user can login
		Response response1 = request.post("/register");

		Assert.assertEquals(200, response1.statusCode());

		JsonPath path = response1.jsonPath();

		String respEmail = path.getString("email");
		String respPassword = path.getString("password");
        System.out.println("respEmail : " + respEmail + "respPassword=:" + respPassword);
        Assert.assertEquals(respEmail, email);
        
		//Checking if user can login 
		Response response2 = request.post("/login");  
		Assert.assertEquals(200, response2.statusCode());
		System.out.println("Login:" + response2);

	}
}
