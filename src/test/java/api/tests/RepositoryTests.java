package api.tests;

import api.base.BaseTest;
import api.models.GitHubRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Feature("GitHub MVP - Logging")
@Severity(SeverityLevel.CRITICAL)
public class RepositoryTests extends BaseTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String REPOSITORY_NAME = "TestRespositoryforMVPchecks";
    private static final String REPOSITORY_DESCRIPTION = "description";

    @Test
    public void createAndDeleteRepository() throws JsonProcessingException {
        GitHubRepository gitHubRepository = new GitHubRepository(REPOSITORY_NAME, REPOSITORY_DESCRIPTION, true);
        String requestBody = objectMapper.writeValueAsString(gitHubRepository);
        Response response = this.apiClient.createNewRepository(GITHUB_USER_TOKEN, requestBody).execute();
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        assertEquals(HttpStatus.SC_NO_CONTENT, deleteRepository().getStatusCode());
    }

    @Test
    public void cloneRepository() {
        Response response = this.apiClient.cloneRepository(GITHUB_USER_TOKEN, GITHUB_USER_NAME, INIT_REPOSITORY_NAME).execute();
        assertEquals(INIT_REPOSITORY_NAME, response.jsonPath().getString("name"));
        assertTrue(response.jsonPath().getBoolean("private"));
    }

    private Response deleteRepository() {
        return this.apiClient.deleteRepository(GITHUB_USER_TOKEN, GITHUB_USER_NAME, REPOSITORY_NAME).execute();
    }
}
