package com.alex.api.test;


import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.alex.api.base.BaseTest;
import com.alex.api.utils.AllureUtils;

import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	
    @Test
    public void getUser_shouldReturn200() {
    	Response response = baseRequest.getUserById(1);


        AllureUtils.attachStatusCode(response.getStatusCode());
        AllureUtils.attachHeaders(response);
        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    
    @Test
    public void getAllUsers_shouldReturnList() {
        Response response = baseRequest.getAllUsers();

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
    }

	 
    @Test
    public void getAllEmails_shouldReturnEmails() {
        Response response = baseRequest.getAllUsers();
        //equivalente a SELECT email FROM users;
        List<String> emails = response.jsonPath().getList("email");
        AllureUtils.attachResponseBody(response);

        Assert.assertTrue(emails.size() > 0);
    }


	 
    @Test
    public void getUserById_shouldReturnCorrectUser() {
        Response response = baseRequest.getUserById(1);

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertEquals(response.jsonPath().getString("name"), "Leanne Graham");
    }
	 


    
    @Test
    public void getUserNameWithId5_shouldBeCorrect() {
        Response response = baseRequest.getAllUsers();

        AllureUtils.attachResponseBody(response);

        Assert.assertEquals(
                response.jsonPath().getString("find { it.id == 5 }.name"),
                "Chelsey Dietrich"
        );
    }
		 //equivalente a SELECT name FROM users WHERE id = 5;


	             
	 
    @Test
    public void countUsers_shouldBeGreaterThanZero() {
        Response response = baseRequest.getAllUsers();

        int count = response.jsonPath().getList("$").size();
        AllureUtils.attachResponseBody(response);

        Assert.assertTrue(count > 0);
    }

    @Test
    public void validateUserList_shouldBeValid() {
        Response response = baseRequest.getAllUsers();

        AllureUtils.attachResponseBody(response);

        Assert.assertTrue(response.jsonPath().getList("$").size() > 5);
        Assert.assertTrue(response.jsonPath().getList("id").stream()
                .allMatch(id -> (int) id > 0));
        Assert.assertTrue(response.jsonPath().getString("[0].email").contains("@"));
        Assert.assertEquals(
                response.jsonPath().getString("find { it.id == 3 }.name"),
                "Clementine Bauch"
        );
    }

	 //Test negativos////////
	 
    @Test
    public void getUserNotFound_shouldReturn404() {
        Response response = baseRequest.getUserByIdNotFound(999999);

        AllureUtils.attachStatusCode(response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 404);
    }


	
}
