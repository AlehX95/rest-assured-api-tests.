package com.alex.api.base;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

	private static final String BASE_URI = "https://jsonplaceholder.typicode.com";
	
	
	public static RequestSpecification getRequestSpec() {

	    return new RequestSpecBuilder()
	            .setBaseUri(BASE_URI)
	            .setContentType(ContentType.JSON)
	            .log(LogDetail.ALL) // loguea request completo
	            .build();
	}

	
	
}
