package com.alex.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.alex.api.base.BaseRequest;
import payload.PostPayloadBuilder;

public class PutUserTest {

    @Test
    public void updatePostSuccessfully() {

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
    public void updatePostWithDefaultPayload() {

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
    //                         PUT NEGATIVOS
    // ============================================================

    @Test
    public void updatePostMissingTitle() {

        String payload = PostPayloadBuilder.postMissingTitle();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            // JSONPlaceholder devuelve 200 siempre
            .statusCode(200);
    }


    @Test
    public void updatePostMissingBody() {

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
    public void updatePostWithInvalidUserId() {

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
    public void updatePostMalformedJson() {

        String payload = PostPayloadBuilder.malformedPostJson();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200)));
            // Nota:
            // JSONPlaceholder ignora errores y puede responder 200
            // API reales deberían devolver 400 o 500
    }

}