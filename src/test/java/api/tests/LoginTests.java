package api.tests;

import api.base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Feature("GitHub MVP tests - Login")
@Severity(SeverityLevel.CRITICAL)
public class LoginTests extends BaseTest {

    private static final String INVALID_TOKEN = "invalidtoken";

    @Test
    public void successfullLogin() {
        int statusCode = this.apiClient.getLoginToGitHub(GITHUB_USER_TOKEN).execute().getStatusCode();
        assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void unsuccessfullLoginWithInvalidToken() {
        int statusCode = apiClient.getLoginToGitHub(INVALID_TOKEN).execute().getStatusCode();
        assertEquals(HttpStatus.SC_UNAUTHORIZED, statusCode);
    }
}
