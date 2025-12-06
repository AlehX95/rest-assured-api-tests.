package com.alex.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import java.util.List;

import org.testng.annotations.Test;
import com.alex.api.base.BaseTest;

public class GetUserTest extends BaseTest {

	 @Test
	    public void getAllUsers() {

	        Response response = RestAssured
	                .given()
	                    .log().all()
	                .when()
	                    .get("/users")
	                .then()
	                    .statusCode(200)
	                    .log().all()
	                    .extract().response();

	        System.out.println(response.getBody().asString());
	    }
	 
	 @Test
	 public void getAllEmails() {
		 
		 //equivalente a SELECT email FROM users;

	     Response response = RestAssured
	             .given().log().all()
	             .when().get("/users")
	             .then().statusCode(200)
	             .extract().response();

	     List<String> emails = response.jsonPath().getList("email");
	     System.out.println("Emails: " + emails);
	 }

	 
	 @Test
	 public void getUserById() {

	     Response response = RestAssured
	             .given()
	                 .log().all()
	             .when()
	                 .get("/users/1")
	             .then()
	                 .statusCode(200)
	                 .body("name", equalTo("Leanne Graham"))
	                 .body("id", equalTo(1))
	                 .log().all()
	                 .extract().response();

	     String name = response.jsonPath().getString("name");
	     System.out.println("User name: " + name);
	 }
	 
	 
	 @Test
	 public void getUserNameWithId5() {
		 
		 //equivalente a SELECT name FROM users WHERE id = 5;

	     Response response = RestAssured
	             .given().log().all()
	             .when().get("/users")
	             .then().statusCode(200).body("find { it.id == 5 }.name", equalTo("Chelsey Dietrich"))
	             .extract().response();

	     String name = response.jsonPath().getString("find { it.id == 5 }.name");
	     System.out.println("Nombre del usuario con ID 5: " + name);
	 }
	 
	 @Test
	 public void countUsers() {

	     Response response = RestAssured
	             .given().log().all()
	             .when().get("/users")
	             .then().statusCode(200).body("size()", equalTo(10))
	             .extract().response();

	     int count = response.jsonPath().getList("$").size();
	     System.out.println("Cantidad de usuarios: " + count);
	 }

	 @Test
	 public void validateUserList() {

	     RestAssured
	         .given()
	             .log().all()
	         .when()
	             .get("/users")
	         .then()
	             .statusCode(200)
	             .body("size()", greaterThan(5))
	             .body("id", everyItem(greaterThan(0)))
	             .body("[0].email", containsString("@"))
	             .body("find { it.id == 3 }.name", equalTo("Clementine Bauch"))
	             .log().all();
	 }

	 
}
