package com.alex.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.alex.api.base.BaseRequest;
import com.alex.api.dataprovider.PatchPojoDataProvider;
import com.alex.api.models.PatchPostModel;

import payload.PostPayloadBuilder;

public class PatchUserTest {

    // ============================================================
    //            POSITIVE PATCH USING POJO And DataDriven
    // ============================================================
	
	
	@Test(dataProvider = "validPatchPosts", dataProviderClass = PatchPojoDataProvider.class)
	public void patchPost_WithPojoDataProvider(PatchPostModel patch) {

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(patch)         // POJO parcial
	    .when()
	        .patch("/posts/1")
	    .then()
	        .statusCode(200);
	}


    @Test
    public void patchUpdateTitle_WithPojo_ShouldReturn200() {

        PatchPostModel patch = new PatchPostModel("Patched Title");

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(patch)                     //  POJO → JSON parcial
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Patched Title"));
    }


    @Test
    public void patchUpdateBody_WithPojo_ShouldReturn200() {

        PatchPostModel patch = new PatchPostModel(null, "Patched Body Content");

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(patch)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(200)
            .body("body", equalTo("Patched Body Content"));
    }



    // ============================================================
    //       POSITIVE PATCH USING PAYLOADBUILDER (Legacy)
    // ============================================================

    @Test
    public void patchUpdateTitle_WithPayloadBuilder_ShouldReturn200() {

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


    @Test
    public void patchUpdateBody_WithPayloadBuilder_ShouldReturn200() {

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
    //                      NEGATIVE TESTS (NEG)
    // ============================================================

    @Test
    public void patchMalformedJson_ShouldReturnErrorOr200() {

        String payload = "{ \"title\":  }";  // JSON inválido

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(anyOf(is(400), is(500), is(200)));
    }


    @Test
    public void patchEmptyBody_ShouldReturn200_ButFailInRealAPI() {

        String payload = "{}";  // PATCH sin cambios

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .patch("/posts/1")
        .then()
            .statusCode(200);
    }

}
