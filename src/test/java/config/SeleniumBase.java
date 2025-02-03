package config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URISyntaxException;

public class SeleniumBase {
    protected ChromeDriverManager chromeDriverManager;
    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;

    @BeforeSuite
    public void setUp() throws URISyntaxException, IOException {
        this.chromeDriverManager = new ChromeDriverManager();
        this.driver = this.chromeDriverManager.getDriver();
        this.javascriptExecutor = (JavascriptExecutor) this.driver;
    }

    protected void navigateToMainPage() {
        driver.get(ConfigProvider.getProperty("initpage"));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
