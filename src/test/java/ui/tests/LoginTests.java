package ui.tests;

import config.SeleniumBase;
import lombok.Getter;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import ui.pages.GitHubMainPage;
import ui.pages.LoginPage;

import static org.testng.Assert.assertTrue;

@Getter
public class LoginTests extends SeleniumBase {

    private GitHubMainPage GitHubMainPage;
    private LoginPage loginPage;

    @SneakyThrows
    @Test()
    public void shouldNotBeAbleToLoginWithInvalidUserName() {
        navigateToMainPage();
        this.GitHubMainPage = new GitHubMainPage(this.webDriver);
        GitHubMainPage.selectSignInOption();
        this.loginPage = new LoginPage(this.webDriver);
        loginPage.fillCredencialsWithGivenData(this.javascriptExecutor, "wrongUserName", System.getenv("GITHUB_USER_NAME"));
        loginPage.clickElement(loginPage.getSignInButton());
        verifyIncorrectLoginDataResults();
        loginPage.refreshPage(this.javascriptExecutor);
    }

    @SneakyThrows
    @Test
    public void shouldNotBeAbleToLoginWithInvalidPassword() {
        navigateToMainPage();
        this.GitHubMainPage = new GitHubMainPage(this.webDriver);
        GitHubMainPage.selectSignInOption();
        this.loginPage = new LoginPage(this.webDriver);
        loginPage.fillCredencialsWithGivenData(this.javascriptExecutor, System.getenv("GITHUB_USER_NAME"), "wrongPassword");
        loginPage.clickElement(loginPage.getSignInButton());
        verifyIncorrectLoginDataResults();
        loginPage.refreshPage(this.javascriptExecutor);
    }

    private void verifyIncorrectLoginDataResults() {
        loginPage.waitForPageToLoad(loginPage.getIncorrectLoginDataLabel());
        assertTrue(loginPage.getIncorrectLoginDataLabel().getText().contains("Incorrect username or password."));
        loginPage.clickElement(loginPage.getCancelAlertButton());
        loginPage.waitUntilElementInvisible(loginPage.getIncorrectLoginDataLabel());
    }
}
