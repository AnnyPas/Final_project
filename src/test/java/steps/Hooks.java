package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.screenshot;

public class Hooks {

    @Before
    public void configureBrowser() {
        Configuration.baseUrl = System.getProperty(
                "test.baseUrl", "https://qa-desk.education-services.ru"
        ).replaceAll("/+$", "");
        Configuration.timeout = 10000;
        Configuration.reportsFolder = "target/selenide-reports";
    }

    @After
    public void closeBrowser(Scenario scenario) {
        try {
            if (scenario.isFailed() && WebDriverRunner.hasWebDriverStarted()) {
                byte[] image = screenshot(OutputType.BYTES);
                if (image != null) {
                    Allure.addAttachment("Страница при падении", "image/png",
                            new ByteArrayInputStream(image), "png");
                }
            }
        } finally {
            closeWebDriver();
        }
    }
}
