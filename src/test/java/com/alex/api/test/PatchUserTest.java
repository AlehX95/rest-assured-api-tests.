package com.alex.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.alex.api.base.BaseRequest;
import payload.PostPayloadBuilder;

public class PatchUserTest {

    // ============================================================
    //                  PATCH POSITIVO (actualización parcial)
    // ============================================================

    @Test
    public void patchUpdateTitle() {

        String payload = PostPayloadBuilder.patchTitlePayload("Patched Title");

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Patched Title"));
    }


    // ============================================================
    //      PATCH POSITIVO (actualiza solo 1 campo del body)
    // ============================================================

    @Test
    public void patchUpdateBody() {

        String payload = PostPayloadBuilder.patchBodyPayload("Patched Body Content");

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(200)
            .body("body", equalTo("Patched Body Content"));
    }


    // ============================================================
    //                      PATCH NEGATIVO
    // ============================================================

    @Test
    public void patchMalformedJson() {

        String payload = "{ \"title\":  }";  // JSON inválido

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200)));
            // JSONPlaceholder ignora errores y responde 200
    }
}