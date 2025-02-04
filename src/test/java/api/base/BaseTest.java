package api.base;

import api.client.ApiClient;
import config.ConfigProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static final String GITHUB_USER_NAME = System.getenv("GITHUB_USER_NAME");
    protected static final String GITHUB_USER_TOKEN = System.getenv("GITHUB_USER_TOKEN");
    protected static final String INIT_REPOSITORY_NAME = "InitRepository";
    protected ApiClient apiClient;

    @BeforeSuite
    public void checkSecrets() {
        if (GITHUB_USER_NAME == null) {
            throw new RuntimeException("User name secret is not populated");
        } else if (GITHUB_USER_TOKEN == null) {
            throw new RuntimeException("User token secret is not populated");
        }
    }

    @BeforeMethod
    public void setUpClient() {
        this.apiClient = createApiClient();
    }

    protected ApiClient createApiClient() {
        return new ApiClient(() -> new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri(ConfigProvider.getProperty("baseapiurl")));
    }
}
