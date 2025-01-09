package pl.przemeknojman.elements;

import org.openqa.selenium.WebElement;
import pl.przemeknojman.elements.WebElementDecorator;

public abstract class WebElementDecoratorBase implements WebElementDecorator {

    protected WebElementDecorator webElementDecorator;

    public WebElementDecoratorBase(WebElementDecorator webElementDecorator) {
        this.webElementDecorator = webElementDecorator;
    }

    @Override
    public void click(WebElement element) {
        webElementDecorator.click(element);
    }

    @Override
    public void sendKeys(WebElement element, String keys) {
        webElementDecorator.sendKeys(element, keys);
    }

    @Override
    public String getText(WebElement element) {
        return element.getText();
    }

}
