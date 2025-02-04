package config;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;

@Listeners({AllureTestNg.class})
public class SeleniumBase {
    protected ChromeDriverManager chromeDriverManager;
    protected WebDriver webDriver;
    protected JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void setUp() throws URISyntaxException, IOException {
        this.chromeDriverManager = new ChromeDriverManager();
        this.webDriver = this.chromeDriverManager.getDriver();
        this.javascriptExecutor = (JavascriptExecutor) this.webDriver;
    }

    protected void navigateToMainPage() {
        webDriver.get(ConfigProvider.getProperty("initpage"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        webDriver.quit();
    }

}
