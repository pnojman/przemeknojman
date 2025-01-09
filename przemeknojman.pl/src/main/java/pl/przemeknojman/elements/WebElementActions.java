package pl.przemeknojman.elements;

import org.openqa.selenium.WebElement;

public class WebElementActions implements WebElementDecorator {


    @Override
    public void click(WebElement element) {
        element.click();
    }

    @Override
    public void sendKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    @Override
    public String getText(WebElement element) {
        return element.getText();
    }
}
