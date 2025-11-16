package tests;

import com.google.gson.Gson;
import com.jatdev.qa.API.api.ReqResAPIClient;
import com.jatdev.qa.API.models.User;

import io.restassured.response.Response;
import reqres.base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLifecycleTest extends BaseTest {

    private ReqResAPIClient apiClient = new ReqResAPIClient();
    private Gson gson = new Gson();
    private String createdUserId;

    @Test(description = "Complete user lifecycle: Create → Retrieve → Delete")
    public void testUserLifecycle() {
        // Step 1: Create User
        logInfo("Step 1: Creating new user");
        User newUser = new User("John Doe", "QA Engineer");

        ReqResAPIClient.APIResponse createResponse = apiClient.createUser(newUser);
        Response createResp = createResponse.getResponse();

        Assert.assertEquals(createResp.getStatusCode(), 201,
                "User creation should succeed");

        User createdUser = gson.fromJson(createResp.getBody().asString(), User.class);
        createdUserId = createdUser.getId();

        logPass("User created successfully with ID: " + createdUserId);

        // Step 2: Retrieve User
        logInfo("Step 2: Retrieving user by ID");
        ReqResAPIClient.APIResponse getResponse = apiClient.getUserById(createdUserId);
        Response getResp = getResponse.getResponse();

        // Note: reqres.in doesn't persist created users, so we might get 404
        if (getResp.getStatusCode() == 200) {
            User retrievedUser = gson.fromJson(getResp.getBody().asString(), User.class);
            Assert.assertEquals(retrievedUser.getName(), newUser.getName(),
                    "Retrieved user name should match");
            logPass("User retrieval validation passed");
        } else {
            logInfo("User retrieval returned status: " + getResp.getStatusCode() +
                    " (Expected for demo API)");
        }

        // Step 3: Delete User
        logInfo("Step 3: Deleting user");
        ReqResAPIClient.APIResponse deleteResponse = apiClient.deleteUser(createdUserId);
        Response deleteResp = deleteResponse.getResponse();

        Assert.assertEquals(deleteResp.getStatusCode(), 204,
                "User deletion should return 204");
        logPass("User deletion completed successfully");

        logInfo("User lifecycle test completed successfully!");
    }
}
