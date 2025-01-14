package pl.przemeknojman.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.FileOutputStream;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pl.przemeknojman.util.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverManager.getInstance("").getDriver();
        if (driver != null) {
            //TakesScreenshot ts = (TakesScreenshot) driver;
            //File srcFile = ts.getScreenshotAs(OutputType.FILE);
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);


            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String testName = result.getName();
            createScreenshotsFolder();
            String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(screenshotBytes);
                fos.flush();
                System.out.println("Screenshot saved to: " + filePath);

            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        }
    }

    private void createScreenshotsFolder() {
        String directoryPath = "screenshots";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

}
