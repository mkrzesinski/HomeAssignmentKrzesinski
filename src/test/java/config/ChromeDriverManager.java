package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ChromeDriverManager {
    public WebDriver driver;
    private ChromeDriverService chromeDriverService;

    public void startService() throws URISyntaxException, IOException {
        final Path chromeDriverPath = Paths.get(Objects.requireNonNull(getClass().getResource("/chromedriver/winx64/chromedriver.exe")).toURI());
        chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(chromeDriverPath.toFile())
                .usingAnyFreePort()
                .build();
        chromeDriverService.start();
    }

    public void createDriver() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("ignore-ssl-errors=yes");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--force-device-scale-factor=1");
        chromeOptions.addArguments("--remote-allow-origins=*");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        this.driver = new ChromeDriver(chromeDriverService, chromeOptions);
    }

    public WebDriver getDriver() throws URISyntaxException, IOException {
        if (driver == null) {
            startService();
            createDriver();
        }
        return this.driver;
    }

    public void quitDriver() {
        if (driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }
}
