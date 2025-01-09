package pl.przemeknojman.pagecomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginComponent {

    private final WebDriverWait wait;
    By userNameInput = By.name("username");
    By passwordInput = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");

    public LoginComponent(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getUserNameInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput));
    }

    public WebElement getPasswordInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
}
