package ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class HomePage extends AbstractPage {

    private final static String HOME_LABEL = "//*[@id=\"dashboard\"]/div/feed-container/div[1]/h2";//xpath
    private final static String ACCOUNT_LABEL = "/html/body/div[1]/div[1]/header/div/div[2]/div[4]/deferred-side-panel/include-fragment/react-partial-anchor/button/span/span/img";
    private final static String USER_NAME_LABEL = "//*[@id=\":ra:\"]/div/div/div/div[1]/div";
    private final static String USER_NAME = "//*[@id=\":ra:\"]/div/div/div/div[1]/div";
    private final static String SIGN_OUT_BUTTON = ":r36:--label";//id

    @FindBy(xpath = HOME_LABEL)
    WebElement homeLabel;

    @FindBy(xpath = ACCOUNT_LABEL)
    WebElement accountLabel;

    @FindBy(xpath = USER_NAME_LABEL)
    WebElement userNameLabel;

    @FindBy(xpath = USER_NAME)
    WebElement userName;

    @FindBy(id = SIGN_OUT_BUTTON)
    WebElement signOutButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        waitForPageToLoad();
    }

    private void waitForPageToLoad() {
        super.waitForPageToLoad(homeLabel);
    }

    public void clickAccountLabel() {
        accountLabel.click();
    }

    public void signOut() {
        signOutButton.click();
    }
}
