package pl.przemeknojman.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

    private final String title;

    public DashboardPage(WebDriver driver) {
        this.title = driver.getTitle();
    }

    public String getTitle() {
        return title;
    }

}
