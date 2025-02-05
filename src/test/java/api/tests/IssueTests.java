package api.tests;

import api.base.BaseTest;
import api.models.GitHubIssue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.AssertJUnit.assertEquals;

@Feature("GitHub MVP tests - Issues")
@Severity(SeverityLevel.CRITICAL)
public class IssueTests extends BaseTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String ISSUE_TITLE = "Test Issue Title " + generateRandomPostFix();
    private static final String ISSUE_BODY = "Test Issue Body";
    private static final String ISSUE_STATE_OPEN = "open";
    private static final String ISSUE_STATE_CLOSED = "closed";
    private static final String EDITED_ISSUE_TITLE = "Test Issue Title " + generateRandomPostFix();
    private static final String EDITED_ISSUE_BODY = "Test Issue Body";
    private GitHubIssue gitHubIssue;
    private String requestBody;
    private int issueId;

    @Test(priority = 1)
    public void createAndDeleteIssue() throws JsonProcessingException {
        this.gitHubIssue = new GitHubIssue(ISSUE_TITLE, ISSUE_BODY, ISSUE_STATE_OPEN);
        this.requestBody = objectMapper.writeValueAsString(gitHubIssue);
        Response response = this.apiClient.addNewIssue(GITHUB_USER_TOKEN, this.requestBody, GITHUB_USER_NAME, INIT_REPOSITORY_NAME).execute();
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        this.issueId = response.jsonPath().getInt("number");
    }

    @Test(priority = 2, dependsOnMethods = "createAndDeleteIssue")
    public void editIssue() throws JsonProcessingException {
        this.gitHubIssue = new GitHubIssue(EDITED_ISSUE_TITLE, EDITED_ISSUE_BODY, ISSUE_STATE_OPEN);
        this.requestBody = objectMapper.writeValueAsString(gitHubIssue);
        Response response = this.apiClient.editIssue(GITHUB_USER_TOKEN, this.requestBody, GITHUB_USER_NAME, INIT_REPOSITORY_NAME, this.issueId).execute();
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(EDITED_ISSUE_TITLE, response.jsonPath().getString("title"));
        assertEquals(ISSUE_STATE_OPEN, response.jsonPath().getString("state"));
    }

    @Test(priority = 3, dependsOnMethods = "editIssue")
    public void closeIssue() throws JsonProcessingException {
        this.gitHubIssue = new GitHubIssue(EDITED_ISSUE_TITLE, EDITED_ISSUE_BODY, ISSUE_STATE_CLOSED);
        this.requestBody = objectMapper.writeValueAsString(gitHubIssue);
        Response response = this.apiClient.editIssue(GITHUB_USER_TOKEN, this.requestBody, GITHUB_USER_NAME, INIT_REPOSITORY_NAME, this.issueId).execute();
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(EDITED_ISSUE_TITLE, response.jsonPath().getString("title"));
        assertEquals(ISSUE_STATE_CLOSED, response.jsonPath().getString("state"));
    }
}
