package api.requests;

import api.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostPullRequest implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public PostPullRequest(RequestSpecBuilder requestSpecBuilder, String token, String requestBody, String userName, String repositoryName) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        this.requestSpecBuilder.addHeader("Authorization", "token " + token);
        this.requestSpecBuilder.setBody(requestBody);
        this.requestSpecBuilder.addPathParam("userName", userName);
        this.requestSpecBuilder.addPathParam("repositoryName", repositoryName);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .post("repos/{userName}/{repositoryName}/pulls");
    }
}
