package com.jatdev.qa.API.api;

import com.jatdev.qa.API.Config.APIConfig;
import com.jatdev.qa.API.models.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqResAPIClient {

    public ReqResAPIClient() {
        RestAssured.baseURI = APIConfig.BASE_URL;
    }

    public APIResponse createUser(User user) {
        long startTime = System.currentTimeMillis();

        Map<String, String> payload = new HashMap<>();
        payload.put("name", user.getName());
        payload.put("job", user.getJob());

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(APIConfig.CREATE_USER_ENDPOINT);

        long responseTime = System.currentTimeMillis() - startTime;

        return new APIResponse(response, responseTime);
    }

    public APIResponse getUserById(String userId) {
        Response response = given()
                .contentType("application/json")
                .when()
                .get(APIConfig.GET_USER_ENDPOINT + userId);

        return new APIResponse(response, 0);
    }

    public APIResponse deleteUser(String userId) {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete(APIConfig.DELETE_USER_ENDPOINT + userId);

        return new APIResponse(response, 0);
    }

    public static class APIResponse {
        private Response response;
        private long responseTime;

        public APIResponse(Response response, long responseTime) {
            this.response = response;
            this.responseTime = responseTime;
        }

        public Response getResponse() { return response; }
        public long getResponseTime() { return responseTime; }
    }
}
