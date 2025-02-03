package ui.tests;

import static org.junit.Assert.assertTrue;

import config.ConfigProvider;
import config.SeleniumBase;
import lombok.Getter;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import ui.pages.GitHubMainPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;

@Getter
public class LoggingTests extends SeleniumBase {

    private GitHubMainPage GitHubMainPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @SneakyThrows
    @Test
    public void shouldBeAbleToSuccessfullLogin(){
        navigateToMainPage();
        this.GitHubMainPage = new GitHubMainPage(this.driver);
        GitHubMainPage.selectSignInOption();
        this.loginPage = new LoginPage(this.driver);
        loginPage.fillCredencialsForDefaultUser(this.javascriptExecutor);
        loginPage.selectSignInOption();
        this.homePage = new HomePage(this.driver);

        assertTrue("Checked label has different text than expected",homePage.getHomeLabel().getText().contains("Home"));

        homePage.clickAccountLabel();
        homePage.waitForPageToLoad(homePage.getUserName());

        assertTrue("Unable to read User name or User name is different than expected", homePage.getUserName().getText().contains(ConfigProvider.getProperty("defaultusername")));
    }
}
