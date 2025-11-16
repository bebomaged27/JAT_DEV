package tests;

import com.google.gson.Gson;
import com.jatdev.qa.API.api.ReqResAPIClient;
import com.jatdev.qa.API.models.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.base.BaseTest;

public class UserCreationTest extends BaseTest {

    private ReqResAPIClient apiClient = new ReqResAPIClient();
    private Gson gson = new Gson();

    @Test(description = "Validate user creation with all assertions")
    public void testCreateUser() {
        // Test data
        User newUser = new User("John Doe", "QA Engineer");
        String requestPayload = gson.toJson(newUser);

        logInfo("Step 1: Creating new user with payload: " + requestPayload);

        ReqResAPIClient.APIResponse apiResponse = apiClient.createUser(newUser);
        Response response = apiResponse.getResponse();
        long responseTime = apiResponse.getResponseTime();

        String responseBody = response.getBody().asString();
        logResponseDetails("/api/users", requestPayload, responseBody, responseTime);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201,
                "Response status code should be 201 Created");
        logPass("✓ Status code validation passed: 201 Created");

        User createdUser = gson.fromJson(responseBody, User.class);

        Assert.assertNotNull(createdUser.getId(), "User ID should not be null");
        logPass("✓ ID generation validation passed");

        Assert.assertEquals(createdUser.getName(), newUser.getName(),
                "User name should match request");
        logPass("✓ Name validation passed: " + newUser.getName());

        Assert.assertEquals(createdUser.getJob(), newUser.getJob(),
                "User job should match request");
        logPass("✓ Job validation passed: " + newUser.getJob());

        Assert.assertTrue(responseTime < 1000,
                "Response time should be less than 1000ms. Actual: " + responseTime + "ms");
        logPass("✓ Response time validation passed: " + responseTime + "ms");

        logInfo("All validations completed successfully!");
    }
}
