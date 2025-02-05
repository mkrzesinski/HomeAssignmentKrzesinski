package api.tests;

import api.base.BaseTest;
import api.models.GitHubRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


@Feature("GitHub MVP tests - Repository")
@Severity(SeverityLevel.CRITICAL)
public class RepositoryTests extends BaseTest {


    @Test(priority = 1)
    public void createRepository() throws JsonProcessingException {
        GitHubRepository gitHubRepository = new GitHubRepository(REPOSITORY_NAME, REPOSITORY_DESCRIPTION, true, true);
        String requestBody = objectMapper.writeValueAsString(gitHubRepository);
        Response response = this.apiClient.createNewRepository(GITHUB_USER_TOKEN, requestBody).execute();
        assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
    }

    @Test(priority = 2, dependsOnMethods = "createRepository")
    public void cloneRepository() {
        Response response = this.apiClient.cloneRepository(GITHUB_USER_TOKEN, GITHUB_USER_NAME, INIT_REPOSITORY_NAME).execute();
        assertEquals(INIT_REPOSITORY_NAME, response.jsonPath().getString("name"));
        assertTrue(response.jsonPath().getBoolean("private"));
    }

    @Test(priority = 3, dependsOnMethods = "createRepository")
    public void deleteRepository() {
        Response response = this.apiClient.deleteRepository(GITHUB_USER_TOKEN, GITHUB_USER_NAME, REPOSITORY_NAME).execute();
        assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
    }
}
