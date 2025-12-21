package com.alex.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.alex.api.base.BaseRequest;
import com.alex.api.dataprovider.PutPojoDataProvider;
import com.alex.api.models.PostModel;
import com.alex.api.models.UserModel;

import payload.PostPayloadBuilder;

public class PutUserTest {

    // ============================================================
    //                 POSITIVE TESTS USING POJO (RECOMMENDED)
    // ============================================================
	
	
	@Test(dataProvider = "validPutUsers", dataProviderClass = PutPojoDataProvider.class)
	public void updateUser_WithPojoDataProvider(UserModel user) {

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(user)
	    .when()
	        .put("/users/1")     // siempre actualizamos el mismo ID
	    .then()
	        .statusCode(200);
	}

    @Test
    public void updatePost_WithPojo_ShouldReturn200() {

        PostModel updatedPost = new PostModel(
                "Updated Title",
                "Updated Body",
                1
        );

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(updatedPost)       // 👈 POJO convertido a JSON automáticamente
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("body", equalTo("Updated Body"))
            .body("userId", equalTo(1));
    }



    // ============================================================
    //       POSITIVE TESTS USING PAYLOADBUILDER (LEGACY / OLDER)
    // ============================================================

    @Test
    public void updatePost_WithPayloadBuilder_ShouldReturn200() {

        String payload = PostPayloadBuilder.createPostPayload(
                "Updated Title",
                "Updated Body",
                1
        );

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("body", equalTo("Updated Body"))
            .body("userId", equalTo(1));
    }


    @Test
    public void updatePost_WithDefaultPayload_ShouldReturn200() {

        String payload = PostPayloadBuilder.createPostPayload();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Default Title"))
            .body("body", equalTo("Default Body"))
            .body("userId", equalTo(1));
    }



    // ============================================================
    //                       NEGATIVE TESTS (NEG)
    // ============================================================

    @Test
    public void updatePost_MissingTitle_ShouldStillReturn200_ButFailInRealAPI() {

        String payload = PostPayloadBuilder.postMissingTitle();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200); // JSONPlaceholder no valida datos
    }


    @Test
    public void updatePost_MissingBody_ShouldStillReturn200_ButFailInRealAPI() {

        String payload = PostPayloadBuilder.postMissingBody();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200);
    }


    @Test
    public void updatePost_InvalidUserId_ShouldStillReturn200() {

        String payload = PostPayloadBuilder.postInvalidUserId();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200);
    }


    @Test
    public void updatePost_MalformedJson_ShouldReturnErrorOr200() {

        String payload = PostPayloadBuilder.malformedPostJson();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200))); 
            // JSONPlaceholder puede devolver 200
    }

}
