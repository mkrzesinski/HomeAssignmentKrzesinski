package api.requests;

import api.client.ExecutableRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetFileSha implements ExecutableRequest {
    private final RequestSpecBuilder requestSpecBuilder;
    public GetFileSha(RequestSpecBuilder requestSpecBuilder, String token, String userName, String repositoryName, String filePath) {
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        this.requestSpecBuilder.addHeader("Authorization", "token " + token);
        this.requestSpecBuilder.addPathParam("userName", userName);
        this.requestSpecBuilder.addPathParam("repositoryName", repositoryName);
        this.requestSpecBuilder.addPathParam("filePath", filePath);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .get("repos/{userName}/{repositoryName}/contents/{filePath}");
    }
}
