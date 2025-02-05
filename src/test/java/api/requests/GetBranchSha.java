package api.requests;

import api.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBranchSha implements ExecutableRequest {
    private final RequestSpecBuilder requestSpecBuilder;

    public GetBranchSha(RequestSpecBuilder requestSpecBuilder, String token, String userName, String repositoryName, String branchName) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        this.requestSpecBuilder.addHeader("Authorization", "token " + token);
        this.requestSpecBuilder.addPathParam("userName", userName);
        this.requestSpecBuilder.addPathParam("repositoryName", repositoryName);
        this.requestSpecBuilder.addPathParam("branchName", branchName);

    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .get("repos/{userName}/{repositoryName}/git/refs/heads/{branchName}");
    }
}