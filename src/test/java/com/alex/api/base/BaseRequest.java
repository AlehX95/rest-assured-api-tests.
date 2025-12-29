package com.alex.api.base;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class BaseRequest {

	private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
	
	
	public static RequestSpecification getRequestSpec() {

	    return new RequestSpecBuilder()
	            .setBaseUri(BASE_URI)
	            .setContentType(ContentType.JSON)
	            .log(LogDetail.ALL) // loguea request completo
	            .build();
	}
	
	//=========Methods for Get Testing

	@Step("GET user by id: {userId}")
	public Response getUserById(int userId) {
	    return given()
	            .spec(getRequestSpec())
	            .when()
	            .get("/users/" + userId);
	}
	
	   @Step("GET all users")
	    public Response getAllUsers() {
	        return given()
	                .spec(getRequestSpec())
	                .when()
	                .get("/users");
	    }

	   @Step("GET user by id: {userId} (not found)")
	    public Response getUserByIdNotFound(int userId) {
	        return given()
	                .spec(getRequestSpec())
	                .when()
	                .get("/users/" + userId);
	    }
	   

	    // ---------------- POSTS ----------------

	    @Step("POST create post")
	    public Response createPost(Object payload) {
	        return given()
	                .spec(getRequestSpec())
	                .body(payload)
	                .when()
	                .post("/posts");
	    }

	    @Step("POST create user")
	    public Response createUser(Object payload) {
	        return given()
	                .spec(getRequestSpec())
	                .body(payload)
	                .when()
	                .post("/users");
	    }
	    
	    // ---------------- PUT ----------------

	    @Step("PUT update post by id: {postId}")
	    public Response updatePostPut(int postId, Object payload) {
	        return given()
	                .spec(getRequestSpec())
	                .body(payload)
	                .when()
	                .put("/posts/" + postId);
	    }

	    @Step("PUT update user by id: {userId}")
	    public Response updateUserPut(int userId, Object payload) {
	        return given()
	                .spec(getRequestSpec())
	                .body(payload)
	                .when()
	                .put("/users/" + userId);
	    }
	    

	    // ---------------- PATCH ----------------

	    @Step("PATCH update post by id: {postId}")
	    public Response updatePostPatch(int postId, Object payload) {
	        return given()
	                .spec(getRequestSpec())
	                .body(payload)
	                .when()
	                .patch("/posts/" + postId);
	    }
	    
	    
	    // ---------------- DELETE ----------------

	    @Step("DELETE post by id: {postId}")
	    public Response deletePost(int postId) {
	        return given()
	                .spec(getRequestSpec())
	                .when()
	                .delete("/posts/" + postId);
	    }

	    @Step("DELETE invalid endpoint")
	    public Response deleteInvalidEndpoint() {
	        return given()
	                .spec(getRequestSpec())
	                .when()
	                .delete("/postsss/1");
	    }
	}
	
	

