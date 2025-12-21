package com.alex.api.test;

import org.testng.annotations.Test;

import com.alex.api.base.BaseTest;
import com.alex.api.dataprovider.PostDataProvider;
import com.alex.api.dataprovider.PostPojoDataProvider;
import com.alex.api.dataprovider.UserDataProvider;
import com.alex.api.base.BaseRequest;
import com.alex.api.models.AddressModel;
import com.alex.api.models.PostModel;
import com.alex.api.models.UserModel;

import payload.PostPayloadBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

public class PostUserTest extends BaseTest {
	
	
	  // ============================================================
    //               Data Provider with payloadBuilder and POJO PostModel - UserModel
    // ============================================================

	
	@Test(dataProvider = "validPosts", dataProviderClass = PostDataProvider.class)
	public void createPost_WithDataProvider(String title, String body, int userId) {

	    String payload = PostPayloadBuilder.createPostPayload(title, body, userId);

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(payload)
	    .when()
	        .post("/posts")
	    .then()
	        .statusCode(201)
	        .body("title", equalTo(title))
	        .body("body", equalTo(body))
	        .body("userId", equalTo(userId));
	}
	
	@Test(dataProvider = "validPostPojos", dataProviderClass = PostPojoDataProvider.class)
	public void createPost_WithPojoDataProvider(PostModel post) {

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(post)                      // Serialización automática POJO → JSON
	    .when()
	        .post("/posts")
	    .then()
	        .statusCode(201)
	        .body("title", equalTo(post.getTitle()))
	        .body("body", equalTo(post.getBody()))
	        .body("userId", equalTo(post.getUserId()));
	}

	
	@Test(dataProvider = "validUsers", dataProviderClass = UserDataProvider.class)
	public void createUser_WithPojoDataProvider(UserModel user) {

	    given()
	        .spec(BaseRequest.getRequestSpec())
	        .body(user)               // REST Assured hace la serialización automática
	    .when()
	        .post("/users")
	    .then()
	        .statusCode(201);
	}



    // ============================================================
    //               POSITIVE TESTS USING POJO (RECOMMENDED)
    // ============================================================

    @Test
    public void createPost_WithPojo_ShouldReturn201() {

        PostModel post = new PostModel("Alex", "QA", 1);

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(post)                      // POJO → JSON automático
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Alex"))
            .body("body", equalTo("QA"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }


    @Test
    public void createUser_WithPojo_ShouldReturn201() {

        AddressModel address = new AddressModel("Dublin", "D01");
        List<String> roles = Arrays.asList("admin", "qa");

        UserModel user = new UserModel("Alex", "QA", 30, roles, address);

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(user)
        .when()
            .post("/users")
        .then()
            .statusCode(201);
    }



    // ============================================================
    //           POSITIVE TESTS USING PAYLOADBUILDER (Legacy)
    // ============================================================

    @Test
    public void createPost_WithPayloadBuilder_ShouldReturn201() {

        String payload = PostPayloadBuilder.createPostPayload(
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
            .statusCode(201)
            .body("title", equalTo("Alejandro"))
            .body("body", equalTo("QA"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }


    @Test
    public void createPostAndValidate_WithPayloadBuilder() {

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



    // ============================================================
    //                    NEGATIVE TESTS (NEG)
    // ============================================================

    @Test
    public void createPost_MissingTitle_ShouldStillReturn201_ButFailInRealAPI() {

        String payload = PostPayloadBuilder.postMissingTitle();

        given()
            .spec(BaseRequest.getRequestSpec())
            .body(payload)
        .when()
            .post("/posts")
        .then()
            .statusCode(201);    // JSONPlaceholder ALWAYS returns 201
    }


    @Test
    public void createPost_MissingBody_ShouldReturn201_ButFailInRealAPI() {

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
    public void createPost_InvalidUserId_ShouldReturn201_ButFailInRealAPI() {

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
