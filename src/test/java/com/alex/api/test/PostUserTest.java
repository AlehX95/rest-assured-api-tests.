package com.alex.api.test;

import org.testng.annotations.Test;
import com.alex.api.base.BaseTest;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import com.alex.api.base.BaseRequest;
import payload.PostPayloadBuilder;

public class PostUserTest extends BaseTest {

	@Test
	public void postUsers() {

	    String payload = 
	    		PostPayloadBuilder.createPostPayload(
	    				  "Alejandro",
	    	                "QA",
	    	                1
	    				);
	    		
	  
	             given()
	             .spec(BaseRequest.getRequestSpec())
	                .body(payload)
	            .when()
	                .post("/posts")
	            .then()
	                .statusCode(201)    // <-- CORRECCIÓN AQUÍ
	                .body("title", equalTo("Alejandro"))
	                .body("body", equalTo("QA"))
	                .body("userId", equalTo(1))
	                .body("id", notNullValue());  // siempre devuelve un ID simulado

}
	
	@Test
	public void createPostAndValidate() {

	      String payload = PostPayloadBuilder.createPostPayload(
	                "Ivan",
	                "QA",
	                1
	        );

	    
	            given()
	            .spec(BaseRequest.getRequestSpec())
	                .body(payload)
	            .when()
	                .post("/posts")
	            .then()
	                .statusCode(201)
	                .body("title", equalTo("Ivan"))
	                .body("body", equalTo("QA"))
	                .body("userId", equalTo(1))
	                .body("id", notNullValue());
	                
	}
	
	///Test Negativos///
	@Test
	public void postMissingTitle() {

	    String payload = PostPayloadBuilder.postMissingTitle();

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(payload)
	    .when()
	        .post("/posts")
	    .then()
	        .statusCode(201);  // JSONPlaceholder siempre devuelve 201
	}
	@Test
	public void postMissingBody() {

	    String payload = PostPayloadBuilder.postMissingBody();

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(payload)
	    .when()
	        .post("/posts")
	    .then()
	        .statusCode(201);
	}

	@Test
	public void postInvalidUserId() {

	    String payload = PostPayloadBuilder.postInvalidUserId();

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(payload)
	    .when()
	        .post("/posts")
	    .then()
	        .statusCode(201);
	}


}

