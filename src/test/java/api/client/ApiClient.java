package api.client;

import api.requests.*;
import io.restassured.builder.RequestSpecBuilder;

import java.util.function.Supplier;

public class ApiClient {
    private final Supplier<RequestSpecBuilder> requestSpecBuilderSupplier;

    public ApiClient(Supplier<RequestSpecBuilder> requestSpecBuilderSupplier) {
        this.requestSpecBuilderSupplier = requestSpecBuilderSupplier;
    }

    public GetLoginToGitHub getLoginToGitHub(String token) {
        return new GetLoginToGitHub(this.requestSpecBuilderSupplier.get(), token);
    }

    public PostNewRepository createNewRepository(String token, String requestBody) {
        return new PostNewRepository(this.requestSpecBuilderSupplier.get(), token, requestBody);
    }

    public DeleteRepository deleteRepository(String token, String userName, String repositoryName) {
        return new DeleteRepository(this.requestSpecBuilderSupplier.get(), token, userName, repositoryName);
    }

    public CloneRepository cloneRepository(String token, String userName, String repositoryName) {
        return new CloneRepository(this.requestSpecBuilderSupplier.get(), token, userName, repositoryName);
    }

    public PostNewIssue addNewIssue(String token, String requestBody, String userName, String repositoryName) {
        return new PostNewIssue(this.requestSpecBuilderSupplier.get(), token, requestBody, userName, repositoryName);
    }

    public PatchIssue editIssue(String token, String requestBody, String userName, String repositoryName, int issueId) {
        return new PatchIssue(this.requestSpecBuilderSupplier.get(), token, requestBody, userName, repositoryName, issueId);
    }
}
