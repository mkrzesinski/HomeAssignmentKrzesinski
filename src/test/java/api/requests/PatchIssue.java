package api.requests;

import api.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PatchIssue implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public PatchIssue(RequestSpecBuilder requestSpecBuilder, String token, String requestBody, String userName, String repositoryName, int issueId) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        this.requestSpecBuilder.addHeader("Authorization", "token " + token);
        this.requestSpecBuilder.setBody(requestBody);
        this.requestSpecBuilder.addPathParam("userName", userName);
        this.requestSpecBuilder.addPathParam("repositoryName", repositoryName);
        this.requestSpecBuilder.addPathParam("issueId", issueId);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .patch("repos/{userName}/{repositoryName}/issues/{issueId}");
    }
}
