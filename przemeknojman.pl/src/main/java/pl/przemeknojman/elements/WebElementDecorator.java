package pl.przemeknojman.elements;

import org.openqa.selenium.WebElement;

public interface WebElementDecorator {

    void click(WebElement element);
    void sendKeys(WebElement element, String keys);
    String getText(WebElement element);
}
