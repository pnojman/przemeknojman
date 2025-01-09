package pl.przemeknojman.elements;

import org.openqa.selenium.WebElement;

public class LoggingDecorator extends WebElementDecoratorBase {
    public LoggingDecorator(WebElementDecorator webElementDecorator) {
        super(webElementDecorator);
    }

    @Override
    public void click(WebElement element) {
        System.out.println("Clicking on element: " + element);
        super.click(element);
    }

    @Override
    public void sendKeys(WebElement element, String keys) {
        System.out.println("Sending keys: " + keys +  " to element: " + element);
        super.sendKeys(element, keys);
    }

    @Override
    public String getText(WebElement element) {
        System.out.println("GetText from element: " + element);
        return super.getText(element);
    }
}
