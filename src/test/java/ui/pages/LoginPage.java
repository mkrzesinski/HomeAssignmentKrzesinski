package ui.pages;

import config.ConfigProvider;
import lombok.Getter;
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

    @FindBy(xpath = LOGIN_PAGE_DESCRIPTION)
    WebElement loginPageDescription;

    @FindBy(id = USER_NAME_OR_EMAIL)
    WebElement userNameField;

    @FindBy(id = PASSWORD)
    WebElement passwordField;

    @FindBy(name = SIGN_IN_BUTTON)
    WebElement signInButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        super.waitForPageToLoad(loginPageDescription, userNameField, passwordField);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    public void fillCredencialsForDefaultUser(@org.jetbrains.annotations.NotNull JavascriptExecutor javascriptExecutor) {
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", userNameField, ConfigProvider.getProperty("defaultusername"));
        javascriptExecutor.executeScript("arguments[0].value=arguments[1];", passwordField, ConfigProvider.getProperty("defaultpassword"));
    }

    public void selectSignInOption(){
        signInButton.click();
    }
}
