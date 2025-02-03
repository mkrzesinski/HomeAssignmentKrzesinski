package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GitHubMainPage extends AbstractPage {

    private static final String SIGN_IN_BUTTON = "/html/body/div[1]/div[3]/header/div/div[2]/div/div/div/a";
    private static final String SIGN_UP_BUTTON = "/html/body/div[1]/div[3]/header/div/div[2]/div/div/a";

    @FindBy(xpath = SIGN_IN_BUTTON)
    private WebElement signInButton;

    @FindBy(xpath = SIGN_UP_BUTTON)
    private WebElement signUpButton;

    public GitHubMainPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        super.waitForPageToLoad(signInButton, signUpButton);
    }

    public void selectSignInOption() {
        signInButton.click();
    }

    public void selectSignUpOption() {
        signUpButton.click();
    }
}
