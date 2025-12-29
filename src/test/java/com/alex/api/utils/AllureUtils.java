package com.alex.api.utils;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;

public class AllureUtils {

    @Attachment(value = "Response Body", type = "application/json")
    public static String attachResponseBody(Response response) {
        try {
            return response.asPrettyString();
        } catch (Exception e) {
            return "Could not pretty print response: " + e.getMessage();
        }
    }

    @Attachment(value = "Status Code", type = "text/plain")
    public static String attachStatusCode(int statusCode) {
        return String.valueOf(statusCode);
    }

    @Attachment(value = "Headers", type = "text/plain")
    public static String attachHeaders(Response response) {
        try {
            return response.getHeaders().toString();
        } catch (Exception e) {
            return "Could not read headers: " + e.getMessage();
        }
    }
}
