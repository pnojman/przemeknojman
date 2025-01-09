package pl.przemeknojman.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

    private static volatile WebDriverManager instance;
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() {
    }

    private void initDriver(String browser) {
        switch (browser) {
            case "localChrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "localFirefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "localEdge":
                tlDriver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriverManager getInstance(String browser) {
        if (instance == null) {
            synchronized (WebDriverManager.class) {
                if (instance == null) {
                    instance = new WebDriverManager();
                }
            }
        }

        if (tlDriver.get() == null) {
            instance.initDriver(browser);
        }

        return instance;
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quiteBrowser() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}