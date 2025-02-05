package ui.pages;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class LoginPage extends AbstractPage {

    private final static String LOGIN_PAGE_DESCRIPTION = "//*[@id=\"login\"]/div[1]/h1";
    private final static String USER_NAME_OR_EMAIL = "login_field";
    private final static String PASSWORD = "password";
    private final static String SIGN_IN_BUTTON = "commit";
    private final static String INCORECT_LOGIN_DATA_LABEL = "js-flash-alert";
    private final static String CANCEL_ALERT_BUTTON = "//*[@id=\"js-flash-container\"]/div/div/button";

    @FindBy(xpath = LOGIN_PAGE_DESCRIPTION)
    WebElement loginPageDescription;

    @FindBy(id = USER_NAME_OR_EMAIL)
    WebElement userNameField;

    @FindBy(id = PASSWORD)
    WebElement passwordField;

    @FindBy(name = SIGN_IN_BUTTON)
    WebElement signInButton;

    @FindBy(className = INCORECT_LOGIN_DATA_LABEL)
    WebElement incorrectLoginDataLabel;

    @FindBy(xpath = CANCEL_ALERT_BUTTON)
    WebElement cancelAlertButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        super.waitForPageToLoad(loginPageDescription, userNameField, passwordField);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    @SneakyThrows
    public void fillCredencialsForDefaultUser(@org.jetbrains.annotations.NotNull JavascriptExecutor javascriptExecutor) {
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", userNameField, System.getenv("GITHUB_USER_NAME"));
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", passwordField, System.getenv("GITHUB_USER_PASSWORD"));
    }

    public void fillCredencialsWithGivenData(@org.jetbrains.annotations.NotNull JavascriptExecutor javascriptExecutor, final String userName, final String password) {
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", userNameField, userName);
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", passwordField, password);
    }
}
