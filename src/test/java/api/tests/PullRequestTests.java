package api.tests;

import api.base.BaseTest;
import api.models.GitHubPullRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

@Feature("GitHub MVP tests - Pull requests")
@Severity(SeverityLevel.CRITICAL)
public class PullRequestTests extends BaseTest {


    private static final String DESCRIPTION = "Description " + generateRandomPostFix();
    private static final String NEW_PR_TITLE = "New PR " + generateRandomPostFix();

    private static final String README_FILE_PATH = "README.md";
    private static final String README_FILE_NEW_CONTENT = "New Content " + generateRandomPostFix();
    private static final String COMMIT_MESSAGE = "Update README.md";

    private static final ObjectMapper objectMapper = new ObjectMapper(); // Jackson
    private GitHubPullRequest gitHubPullRequest;
    private String requestBody;
    private int pullRequestId;


    @Test(priority = 1)
    public void testCreatePullRequest() throws JsonProcessingException {
        updateReadmeFile(HEAD_BRANCH, INIT_REPOSITORY_NAME);
        this.gitHubPullRequest = new GitHubPullRequest(NEW_PR_TITLE, HEAD_BRANCH, BASE_BRANCH, DESCRIPTION);
        this.requestBody = objectMapper.writeValueAsString(this.gitHubPullRequest);
        Response response = apiClient.createNewPullRequest(USER_TOKEN, this.requestBody, USER_NAME, INIT_REPOSITORY_NAME).execute();
        this.pullRequestId = response.jsonPath().getInt("number");
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
    }

    @Test(priority = 2, dependsOnMethods = "testCreatePullRequest")
    public void testMergePullRequest() {
        Response response = this.apiClient.mergePullRequest(USER_TOKEN, USER_NAME, INIT_REPOSITORY_NAME, this.pullRequestId).execute();
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("Pull Request successfully merged", response.jsonPath().getString("message"));
    }

    @Test(priority = 3, dependsOnMethods = "testMergePullRequest")
    public void testClosePullRequest() throws JsonProcessingException {
        Response response;
        updateReadmeFile(HEAD_BRANCH, INIT_REPOSITORY_NAME);
        this.gitHubPullRequest = new GitHubPullRequest(NEW_PR_TITLE, HEAD_BRANCH, BASE_BRANCH, DESCRIPTION);
        this.requestBody = objectMapper.writeValueAsString(this.gitHubPullRequest);
        response = apiClient.createNewPullRequest(USER_TOKEN, this.requestBody, USER_NAME, INIT_REPOSITORY_NAME).execute();
        this.pullRequestId = response.jsonPath().getInt("number");

        String requestBody = "{\"state\": \"closed\"}";
        response = this.apiClient.closePullRequest(USER_TOKEN, requestBody, USER_NAME, INIT_REPOSITORY_NAME, this.pullRequestId).execute();
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals("closed", response.jsonPath().getString("state"));
    }

    private Map<String, Object> prepareReadmeUpdateBody(String branch) {
        Response response = apiClient.getFileSha(USER_TOKEN, USER_NAME, INIT_REPOSITORY_NAME, README_FILE_PATH).execute();
        String fileSha = response.jsonPath().getString("sha");
        String encodedContent = Base64.encodeBase64String(README_FILE_NEW_CONTENT.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> body = new HashMap<>();
        body.put("message", COMMIT_MESSAGE);
        body.put("content", encodedContent);
        if (fileSha != null) {
            body.put("sha", fileSha);
        }
        body.put("branch", branch);
        return body;
    }

    private void updateReadmeFile(String branch, String repository) throws JsonProcessingException {
        Response response = apiClient.updateFile(USER_TOKEN, objectMapper.writeValueAsString(prepareReadmeUpdateBody(branch)), USER_NAME, repository, README_FILE_PATH).execute();
    }
}

