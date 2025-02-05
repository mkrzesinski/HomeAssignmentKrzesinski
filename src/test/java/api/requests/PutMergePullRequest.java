package api.requests;

import api.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutMergePullRequest implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public PutMergePullRequest(RequestSpecBuilder requestSpecBuilder, String token, String userName, String repositoryName, int pullRequestId) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        this.requestSpecBuilder.addHeader("Authorization", "token " + token);
        this.requestSpecBuilder.addPathParam("userName", userName);
        this.requestSpecBuilder.addPathParam("repositoryName", repositoryName);
        this.requestSpecBuilder.addPathParam("pullRequestId", pullRequestId);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .put("repos/{userName}/{repositoryName}/pulls/{pullRequestId}/merge");
    }
}
