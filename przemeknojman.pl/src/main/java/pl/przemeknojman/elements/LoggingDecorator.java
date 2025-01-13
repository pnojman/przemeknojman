package pl.przemeknojman.elements;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDecorator extends WebElementDecoratorBase {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDecorator.class);
    public LoggingDecorator(WebElementDecorator webElementDecorator) {
        super(webElementDecorator);
    }

    @Override
    public void click(WebElement element) {
        logger.info("Clicking on element: " + element);
        super.click(element);
    }

    @Override
    public void sendKeys(WebElement element, String keys) {
        logger.info("Sending keys: " + keys +  " to element: " + element);
        super.sendKeys(element, keys);
    }

    @Override
    public String getText(WebElement element) {
        logger.info("GetText from element: " + element);
        return super.getText(element);
    }
}
