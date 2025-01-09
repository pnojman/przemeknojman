package pl.przemeknojman;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.przemeknojman.dto.UserDTO;
import pl.przemeknojman.pages.DashboardPage;
import pl.przemeknojman.pages.LoginPage;
import pl.przemeknojman.util.JsonUtils;
import pl.przemeknojman.util.WebDriverManager;

public class WhenTestingUITest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    String browser;

    @BeforeClass
    @Parameters({"url", "browser"})
    public void setUp(String url, String browser) {
        WebDriver driver = WebDriverManager.getInstance(browser).getDriver();
        driver.get(url);
        this.browser = browser;
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quiteBrowser();
    }

    @Test
    public void shouldBePossibleToTestUI() {

        String userJson = JsonUtils.readJsonFromResource("data/user.json");
        UserDTO user = JsonUtils.parseJsonToObject(userJson, UserDTO.class);

        loginPage.login(user.getLogin(), user.getPassword());

        Assert.assertEquals(dashboardPage.getTitle(), "OrangeHRM");
    }

}