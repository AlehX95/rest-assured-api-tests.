package com.alex.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alex.api.base.BaseTest;
import com.alex.api.utils.AllureUtils;

import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{


    //                   DELETE POSITIVO
    // ============================================================

	@Test
	public void deletePostSuccessfully() {

	    Response response = baseRequest.deletePost(1);

	    AllureUtils.attachStatusCode(response.getStatusCode());

	    Assert.assertEquals(response.getStatusCode(), 200);
	}

            // JSONPlaceholder siempre devuelve 200 OK
    


    // ============================================================
    //                    DELETE NEGATIVO (ID no existe)
    // ============================================================

	@Test
	public void deleteNonExistingPost() {

	    Response response = baseRequest.deletePost(99999);

	    AllureUtils.attachStatusCode(response.getStatusCode());

	    Assert.assertEquals(response.getStatusCode(), 200);
	}

            // JSONPlaceholder igual responde 200 
            // En una API real esto sería 404
    



    // ============================================================
    //                  DELETE NEGATIVO (ruta incorrecta)
    // ============================================================

	@Test
	public void deleteInvalidEndpoint() {

	    Response response = baseRequest.deleteInvalidEndpoint();

	    AllureUtils.attachStatusCode(response.getStatusCode());

	    Assert.assertEquals(response.getStatusCode(), 404);
	}

}
