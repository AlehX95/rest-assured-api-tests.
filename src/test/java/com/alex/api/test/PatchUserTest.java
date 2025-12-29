package com.alex.api.test;



import org.testng.Assert;
import org.testng.annotations.Test;


import com.alex.api.base.BaseTest;
import com.alex.api.dataprovider.PatchPojoDataProvider;
import com.alex.api.models.PatchPostModel;
import com.alex.api.utils.AllureUtils;

import io.restassured.response.Response;
import payload.PostPayloadBuilder;

public class PatchUserTest extends BaseTest{

    // ============================================================
    //            POSITIVE PATCH USING POJO And DataDriven
    // ============================================================
	
	
	@Test(dataProvider = "validPatchPosts", dataProviderClass = PatchPojoDataProvider.class)
	public void patchPost_WithPojoDataProvider(PatchPostModel patch) {

	    Response response = baseRequest.updatePostPatch(1, patch);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}



	@Test
	public void patchUpdateTitle_WithPojo_ShouldReturn200() {

	    PatchPostModel patch = new PatchPostModel("Patched Title");

	    Response response = baseRequest.updatePostPatch(1, patch);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals(response.jsonPath().getString("title"), "Patched Title");
	}


	@Test
	public void patchUpdateBody_WithPojo_ShouldReturn200() {

	    PatchPostModel patch = new PatchPostModel(null, "Patched Body Content");

	    Response response = baseRequest.updatePostPatch(1, patch);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	    Assert.assertEquals(response.jsonPath().getString("body"), "Patched Body Content");
    }



    // ============================================================
    //       POSITIVE PATCH USING PAYLOADBUILDER (Legacy)
    // ============================================================

	@Test
	public void patchUpdateTitle_WithPayloadBuilder_ShouldReturn200() {

	    String payload = PostPayloadBuilder.patchTitlePayload("Patched Title");
	    Response response = baseRequest.updatePostPatch(1, payload);

	    AllureUtils.attachResponseBody(response);

	    Assert.assertEquals(response.getStatusCode(), 200);
	}


    @Test
    public void patchUpdateBody_WithPayloadBuilder_ShouldReturn2001() {
	
    String payload = PostPayloadBuilder.patchBodyPayload("Patched Body Content");
    Response response = baseRequest.updatePostPatch(1, payload);

    AllureUtils.attachResponseBody(response);

    Assert.assertEquals(response.getStatusCode(), 200);
}
	

    // ============================================================
    //                      NEGATIVE TESTS (NEG)
    // ============================================================

    @Test
    public void patchMalformedJson_ShouldReturnErrorOr200() {

        String payload = "{ \"title\":  }";

        Response response = baseRequest.updatePostPatch(1, payload);

        AllureUtils.attachResponseBody(response);

        Assert.assertTrue(
                response.getStatusCode() == 200 ||
                response.getStatusCode() == 400 ||
                response.getStatusCode() == 500
        );
    }

    
    @Test
    public void patchEmptyBody_ShouldReturn200() {

        String payload = "{}";

        Response response = baseRequest.updatePostPatch(1, payload);

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
