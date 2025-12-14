package com.alex.api.test;

import static org.hamcrest.Matchers.*;
import java.util.List;
import static io.restassured.RestAssured.given;
import com.alex.api.base.*;  
import org.testng.annotations.Test;
import com.alex.api.base.BaseTest;

public class GetUserTest extends BaseTest {

	 @Test
	    public void getAllUsers() {

	        
	                given()
	                .spec(BaseRequest.getRequestSpec())
	                .when()
	                    .get("/users")
	                .then()
	                .statusCode(200)
	                .body("size()", greaterThan(0));
	    }
	 
	 @Test
	 public void getAllEmails() {
		 
		 //equivalente a SELECT email FROM users;

		 List<String> emails = 
	             given()
	             .spec(BaseRequest.getRequestSpec())
	             .when().get("/users")
	             .then()
	             .statusCode(200)
	                .extract().jsonPath().getList("email");

	        System.out.println("Emails: " + emails);
	 }

	 
	 @Test
	 public void getUserById() {
		 
	  
	             given()
	             .spec(BaseRequest.getRequestSpec())
	             .when()
	                 .get("/users/1")
	             .then()
	                 .statusCode(200)
	                 .body("name", equalTo("Leanne Graham"))
	                 .body("id", equalTo(1));
	               

	    
	    
	 }
	 
	 
	 @Test
	 public void getUserNameWithId5() {
		 
		 //equivalente a SELECT name FROM users WHERE id = 5;

	    
	              given()
	              .spec(BaseRequest.getRequestSpec())
	             .when().get("/users")
	             .then().statusCode(200)
	             .body("find { it.id == 5 }.name", equalTo("Chelsey Dietrich"));
	             
	 }
	 
	 @Test
	 public void countUsers() {

		 int count = 
	             given()
	             .spec(BaseRequest.getRequestSpec())
	             .when().get("/users")
	             .then().statusCode(200)
	             .extract().jsonPath().getList("$").size();
	           

	     System.out.println("Cantidad de usuarios: " + count);
	 }

	 @Test
	 public void validateUserList() {

	        given()
	        .spec(BaseRequest.getRequestSpec())
	         .when()
	             .get("/users")
	         .then()
	             .statusCode(200)
	             .body("size()", greaterThan(5))
	             .body("id", everyItem(greaterThan(0)))
	             .body("[0].email", containsString("@"))
	             .body("find { it.id == 3 }.name", equalTo("Clementine Bauch"));
	           
	 }

	 //Test negativos////////
	 
	 @Test
	 public void getUserNotFound() {

	     given()
	         .spec(BaseRequest.getRequestSpec())
	     .when()
	         .get("/users/999999")
	     .then()
	         .statusCode(404);
	 }

	
}
