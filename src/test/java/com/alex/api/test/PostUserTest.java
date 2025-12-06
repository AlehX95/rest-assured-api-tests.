package com.alex.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.alex.api.base.BaseTest;
import static org.hamcrest.Matchers.equalTo;


public class PostUserTest extends BaseTest {

	@Test
	public void postUsers() {

	    String body = "{ \"title\": \"Alejandro\", \"body\": \"QA\", \"userId\": 1 }";

	    Response response = RestAssured
	            .given()
	                .header("Content-Type", "application/json")
	                .body(body)
	                .log().all()
	            .when()
	                .post("https://jsonplaceholder.typicode.com/posts")
	            .then()
	                .statusCode(201)    // <-- CORRECCIÓN AQUÍ
	                .log().all()
	                .extract().response();

	    System.out.println("Response Body:");
	    System.out.println(response.getBody().asString());


	    String userId = response.jsonPath().getString("id");
	    System.out.println("ID del usuario creado: " + userId);

	    System.out.println("BASE URI = " + RestAssured.baseURI);
	    System.out.println("BASE PATH = " + RestAssured.basePath);

}
	
	@Test
	public void createPostAndValidate() {

	    String body = "{ \"title\": \"Ivan\", \"body\": \"QA\", \"userId\": 1 }";

	    Response response = RestAssured
	            .given()
	                .header("Content-Type", "application/json")
	                .body(body)
	                .log().all()
	            .when()
	                .post("/posts")
	            .then()
	                .statusCode(201)
	                .body("title", equalTo("Ivan"))
	                .body("userId", equalTo(1))
	                .log().all()
	                .extract().response();
	                

	    String id = response.jsonPath().getString("id");
	    assert id != null && !id.isEmpty();

	    System.out.println("ID generado: " + id);
	}

}

