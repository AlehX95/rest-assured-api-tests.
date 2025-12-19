package com.alex.api.test;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.alex.api.base.BaseRequest;

public class DeleteUserTest {


    //                   DELETE POSITIVO
    // ============================================================

    @Test
    public void deletePostSuccessfully() {

        given()
            .spec(BaseRequest.getRequestSpec())
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200); 
            // JSONPlaceholder siempre devuelve 200 OK
    }


    // ============================================================
    //                    DELETE NEGATIVO (ID no existe)
    // ============================================================

    @Test
    public void deleteNonExistingPost() {

        given()
            .spec(BaseRequest.getRequestSpec())
        .when()
            .delete("/posts/99999")
        .then()
            .statusCode(200);
            // JSONPlaceholder igual responde 200 
            // En una API real esto sería 404
    }


    // ============================================================
    //                  DELETE NEGATIVO (ruta incorrecta)
    // ============================================================

    @Test
    public void deleteInvalidEndpoint() {

        given()
            .spec(BaseRequest.getRequestSpec())
        .when()
            .delete("/postsss/1")  // endpoint incorrecto
        .then()
            .statusCode(404);
    }
}
