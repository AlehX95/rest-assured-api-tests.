package com.alex.api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.alex.api.base.BaseTest;
import com.alex.api.dataprovider.PutPojoDataProvider;
import com.alex.api.models.PostModel;
import com.alex.api.models.UserModel;
import com.alex.api.utils.AllureUtils;

import io.restassured.response.Response;
import payload.PostPayloadBuilder;

public class PutUserTest extends BaseTest{

    // ============================================================
    //                 POSITIVE TESTS USING POJO (RECOMMENDED)
    // ============================================================
	
	
	@Test(dataProvider = "validPutUsers", dataProviderClass = PutPojoDataProvider.class)
	public void updateUser_WithPojoDataProvider(UserModel user) {

	    Response response = baseRequest.updateUserPut(1, user);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}


	@Test
	public void updatePost_WithPojo_ShouldReturn200() {

	    PostModel updatedPost = new PostModel(
	            "Updated Title",
	            "Updated Body",
	            1
	    );

	    Response response = baseRequest.updatePostPut(1, updatedPost);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
	    Assert.assertEquals(response.jsonPath().getString("body"), "Updated Body");
	    Assert.assertEquals(response.jsonPath().getInt("userId"), 1);
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

	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}


	@Test
	public void updatePost_WithDefaultPayload_ShouldReturn200() {

	    String payload = PostPayloadBuilder.createPostPayload();

	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}




    // ============================================================
    //                       NEGATIVE TESTS (NEG)
    // ============================================================
	
	@Test
	public void updatePost_MissingTitle_ShouldStillReturn200() {

	    String payload = PostPayloadBuilder.postMissingTitle();
	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}

    @Test
    public void updatePost_MissingBody_ShouldStillReturn200_ButFailInRealAPI() {

	    String payload = PostPayloadBuilder.postMissingBody();
	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    public void updatePost_InvalidUserId_ShouldStillReturn200() {

	    String payload = PostPayloadBuilder.postInvalidUserId();
	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    public void updatePost_MalformedJson_ShouldReturnErrorOr200() {

	    String payload = PostPayloadBuilder.malformedPostJson();
	    Response response = baseRequest.updatePostPut(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
    }

}
