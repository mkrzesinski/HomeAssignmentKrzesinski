package ui.tests;

import config.ConfigProvider;
import config.SeleniumBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import ui.pages.GitHubMainPage;
import ui.pages.LoginPage;

import static org.testng.Assert.assertTrue;

@Getter
@Feature("GitHub MVP - Logging")
@Severity(SeverityLevel.CRITICAL)
public class LoginTests extends SeleniumBase {

    private GitHubMainPage GitHubMainPage;
    private LoginPage loginPage;

    @SneakyThrows
    @Test()
    public void shouldNotBeAbleToLoginWithInvalidUserName(){
        navigateToMainPage();
        this.GitHubMainPage = new GitHubMainPage(this.webDriver);
        GitHubMainPage.selectSignInOption();
        this.loginPage = new LoginPage(this.webDriver);
        loginPage.fillCredencialsWithGivenData(this.javascriptExecutor, "wrongUserName", ConfigProvider.getProperty("defaultpassword"));
        loginPage.clickElement(loginPage.getSignInButton());
        verifyIncorrectLoginDataResults();
        loginPage.refreshPage(this.javascriptExecutor);
    }

    @SneakyThrows
    @Test
    public void shouldNotBeAbleToLoginWithInvalidPassword(){
        navigateToMainPage();
        this.GitHubMainPage = new GitHubMainPage(this.webDriver);
        GitHubMainPage.selectSignInOption();
        this.loginPage = new LoginPage(this.webDriver);
        loginPage.fillCredencialsWithGivenData(this.javascriptExecutor, ConfigProvider.getProperty("defaultusername"), "wrongPassword");
        loginPage.clickElement(loginPage.getSignInButton());
        verifyIncorrectLoginDataResults();
        loginPage.refreshPage(this.javascriptExecutor);
    }

    private void verifyIncorrectLoginDataResults(){
        loginPage.waitForPageToLoad(loginPage.getIncorrectLoginDataLabel());
        assertTrue(loginPage.getIncorrectLoginDataLabel().getText().contains("Incorrect username or password."));
        loginPage.clickElement(loginPage.getCancelAlertButton());
        loginPage.waitUntilElementInvisible(loginPage.getIncorrectLoginDataLabel());
    }
}
