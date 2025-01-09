package pl.przemeknojman.pages;

import org.openqa.selenium.WebDriver;
import pl.przemeknojman.elements.LoggingDecorator;
import pl.przemeknojman.elements.WebElementActions;
import pl.przemeknojman.elements.WebElementDecorator;
import pl.przemeknojman.pagecomponents.LoginComponent;

public class LoginPage {

    LoginComponent loginComponent;
    WebElementDecorator webElementDecorator;
    public LoginPage(WebDriver driver) {
        this.loginComponent = new LoginComponent(driver);
        this.webElementDecorator = new LoggingDecorator(new WebElementActions());
    }

    public void login(String login, String password) {
        webElementDecorator.sendKeys(loginComponent.getUserNameInput(), login);
        webElementDecorator.sendKeys(loginComponent.getPasswordInput(), password);
        webElementDecorator.click(loginComponent.getLoginButton());
    }
}
