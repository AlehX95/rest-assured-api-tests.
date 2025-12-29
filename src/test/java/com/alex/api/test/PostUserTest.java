package com.alex.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alex.api.base.BaseTest;
import com.alex.api.dataprovider.PostDataProvider;
import com.alex.api.dataprovider.PostPojoDataProvider;
import com.alex.api.dataprovider.UserDataProvider;
import com.alex.api.base.BaseRequest;
import com.alex.api.models.AddressModel;
import com.alex.api.models.PostModel;
import com.alex.api.models.UserModel;
import com.alex.api.utils.AllureUtils;

import io.restassured.response.Response;
import payload.PostPayloadBuilder;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

public class PostUserTest extends BaseTest {
	
	
	  // ============================================================
    //               Data Provider with payloadBuilder and POJO PostModel - UserModel
    // ============================================================

	
	@Test(dataProvider = "validPosts", dataProviderClass = PostDataProvider.class)
	public void createPost_WithDataProvider(String title, String body, int userId) {

	    String payload = PostPayloadBuilder.createPostPayload(title, body, userId);
	    Response response = baseRequest.createPost(payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 201);
	    Assert.assertEquals(response.jsonPath().getString("title"), title);
	    Assert.assertEquals(response.jsonPath().getString("body"), body);
	    Assert.assertEquals(response.jsonPath().getInt("userId"), userId);
	}

	
	@Test(dataProvider = "validPostPojos", dataProviderClass = PostPojoDataProvider.class)
	public void createPost_WithPojoDataProvider(PostModel post) {

	    Response response = baseRequest.createPost(post);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 201);
	    Assert.assertEquals(response.jsonPath().getString("title"), post.getTitle());
	    Assert.assertEquals(response.jsonPath().getString("body"), post.getBody());
	    Assert.assertEquals(response.jsonPath().getInt("userId"), post.getUserId());
	}

	
	@Test(dataProvider = "validUsers", dataProviderClass = UserDataProvider.class)
	public void createUser_WithPojoDataProvider(UserModel user) {

	    Response response = baseRequest.createUser(user);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 201);
	}




    // ============================================================
    //               POSITIVE TESTS USING POJO (RECOMMENDED)
    // ============================================================

	@Test
	public void createPost_WithPojo_ShouldReturn201() {

	    PostModel post = new PostModel("Alex", "QA", 1);
	    Response response = baseRequest.createPost(post);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 201);
	    Assert.assertNotNull(response.jsonPath().get("id"));
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

        String payload = PostPayloadBuilder.createPostPayload("Alejandro", "QA", 1);
        Response response = baseRequest.createPost(payload);

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().get("id"));
    }






    // ============================================================
    //                    NEGATIVE TESTS (NEG)
    // ============================================================

    @Test
    public void createPost_MissingTitle_ShouldReturn201() {

        String payload = PostPayloadBuilder.postMissingTitle();
        Response response = baseRequest.createPost(payload);

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 201);
    }


}
