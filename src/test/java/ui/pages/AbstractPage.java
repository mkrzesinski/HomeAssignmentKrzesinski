package ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static org.awaitility.Awaitility.await;

public abstract class AbstractPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    public void waitForPageToLoad(final WebElement... elements) {
        Arrays.asList(elements).forEach(element -> await().until(element::isDisplayed));
    }

    public boolean waitUntilElementInvisible(final WebElement element){
        return webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickElement(WebElement webElement){
        webElement.click();
    }

    public void refreshPage(JavascriptExecutor javascriptExecutor){
        javascriptExecutor.executeScript("window.location.reload(true);");
    }
}
